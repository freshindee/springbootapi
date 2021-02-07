package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import com.fitscorp.j2eemobileapi.restservices.restservices.dto.ProductDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.dto.PromotionDTO;
import com.fitscorp.j2eemobileapi.restservices.restservices.entities.*;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.NotFoundException;
import com.fitscorp.j2eemobileapi.restservices.restservices.exceptions.UserNotActivatedException;
import com.fitscorp.j2eemobileapi.restservices.restservices.handlers.RestResponse;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.CategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.FavProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.ProductRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.repository.SubCategoryRepository;
import com.fitscorp.j2eemobileapi.restservices.restservices.request.FavoriteRequest;
import com.fitscorp.j2eemobileapi.restservices.restservices.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FavProductRepository favProductRepository;

	@Autowired
	private UserService userService;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		Optional<Product> prod = productRepository.findById(id);
		if (prod.isPresent()) {
			List<String> images = findAllImages(prod.get().getId());
			prod.get().setImages(images);

			return prod.get();
		}
		return null;
	}

	public ProductResponse getProductsBySubCategoryId(Long subCatId) throws NotFoundException {
		List<ProductDTO> productDtos = new ArrayList<>();
		SubCategory subCat;
		Category cat;
		try {
			subCat =  subCategoryRepository.findById(subCatId).get();
			cat =  categoryRepository.findById(subCatId).get();
		} catch (Exception no) {
			throw new NotFoundException("Not found");
		}
		List<Product> products = productRepository.findProductsBySubCategoryId(subCatId);
		
		if (products == null || products.size() == 0) {
			return new ProductResponse(new ArrayList<>());
		}
		
		for (Product product : products) {
			List<String> images = findAllImages(product.getId());
			product.setImages(images);

			Boolean isFavorite = favProductRepository
					.findIfProductIsFavoriteByProductId(product.getId()) != null;

			ProductDTO prod = getProductDTO(subCat, cat, product, isFavorite);
			productDtos.add(prod);
		}
		return new ProductResponse(productDtos);
	}

//	public List<Product> getAllPromotions() {
//		return productRepository.findAllPromotions();
//	}

	public List<PromotionDTO> getAllPromotions() throws Exception {
		List<SubCategory> subCategories =  subCategoryRepository.findAllPromotionSubCategories();
		List<PromotionDTO> promos = new ArrayList<>();
		
		for (SubCategory subCat : subCategories) {
			List<Product> products = productRepository.findProductsBySubCategoryId(subCat.getId());
			
			for (Product product : products) {
				List<String> images = findAllImages(product.getId());
				product.setImages(images);

				Boolean isFavorite = favProductRepository
						.findIfProductIsFavoriteByProductId(product.getId()) != null;

				PromotionDTO promo = new PromotionDTO(
					subCat.getCategoryId(), 
					subCat.getStoreId(), 
					product.getId(), 
					product.getSubCategoryId(), 
					subCat.getName(),
					subCat.getPromotionStartDate(), 
					subCat.getPromotionEndDate(), 
					subCat.getPromotionDescription(), 
					product.getName(), 
					product.getDescription(),
					product.getPrice(), 
					product.getDiscountedPrice(), 
					product.getUnit(),
					isFavorite,
					product.getImages()
				);
				promos.add(promo);
			}
			
			List<String> images = findAllImages(subCat.getId());
			subCat.setImages(images);
		}
		return promos;
	}

	public ProductResponse findProductsByName(String criteria, @PageableDefault(page = 0, value = Integer.MAX_VALUE) Pageable pageable) throws NotFoundException {
		Slice<Product> products = productRepository.findProductsByName(criteria, pageable);
		List<ProductDTO> productDtos = new ArrayList<>();
		SubCategory subCat;
		Category cat;
		
		if (products == null || products.isEmpty()) {
			return new ProductResponse(new ArrayList<>());
		}
		
		for (Product product : products) {
			if (product == null) {
				break;
			}
			
			try {
				subCat =  subCategoryRepository.findById(product.getSubCategoryId()).get();
				cat =  categoryRepository.findById(product.getSubCategoryId()).get();
			} catch (Exception no) {
				throw new NotFoundException("Not found");
			}
			
			List<String> images = findAllImages(product.getId());
			product.setImages(images);

			ProductDTO prod = getProductDTO(subCat, cat, product, true);
			productDtos.add(prod);
		}
		return new ProductResponse(productDtos);
	}

	public Object getFavoriteProducts(Long userId) {
		try {userService.checkAccountActivation(userId);

			List<FavProduct> favProducts = favProductRepository.findFavoriteProductsByUserId(userId);
			List<ProductDTO> productDtos = new ArrayList<>();
			SubCategory subCat;
			Category cat;

			if (favProducts == null || favProducts.size() == 0) {
				return new ProductResponse(new ArrayList<>());
			}

			for (FavProduct favProd : favProducts) {
				Optional<Product> product = productRepository.findById(favProd.getProductId().longValue());

				if (!product.isPresent()) {
					break;
				}

				try {
					subCat = subCategoryRepository.findById(product.get().getSubCategoryId()).get();
					cat = categoryRepository.findById(product.get().getSubCategoryId()).get();
				} catch (Exception no) {
					throw new NotFoundException("Not found");
				}

				List<String> images = findAllImages(product.get().getId());
				product.get().setImages(images);

				ProductDTO prod = getProductDTO(subCat, cat, product.get(), true);
				productDtos.add(prod);
			}
			return new ProductResponse(productDtos);
		} catch (UserNotActivatedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("Please activate your account!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("User not exists!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ProductResponse(new ArrayList<>());
		}
	}

	public Object addFavoriteProducts(Long userId, FavoriteRequest request) {
		try {
			userService.checkAccountActivation(userId);
			List<FavProduct> favProducts = new ArrayList<>();
			Date date = new Date();
			for (Long productId : request.getProductIds()) {
				Product product = productRepository.findById(productId).get();
				favProducts.add(new FavProduct(
						BigInteger.valueOf(product.getId()),
						BigInteger.valueOf(userId),
						userId.toString(),
						date,
						false,
						userId.toString(),
						date
				));
			}
			return favProductRepository.saveAll(favProducts);
		} catch (UserNotActivatedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("Please activate your account!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("User not exists!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object deleteFavoriteProducts(Long userId, FavoriteRequest request) {
		try {
			userService.checkAccountActivation(userId);
			List<FavProduct> favProducts = new ArrayList<>();
			for (Long productId : request.getProductIds()) {
				List<FavProduct> prods = favProductRepository.findFavoriteProductsProductId(productId, userId);
				favProductRepository.deleteAll(prods);
				favProducts.addAll(prods);
			}
			return favProducts;
		} catch (UserNotActivatedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new RestResponse<String>(401, Arrays.asList("Please activate your account!"), null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private ProductDTO getProductDTO(SubCategory subCat, Category cat, Product product, boolean b) {
		return new ProductDTO(
				subCat.getCategoryId(),
				subCat.getStoreId(),
				product.getId(),
				product.getSubCategoryId(),
				cat.getCategoryName(),
				subCat.getName(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getDiscountedPrice(),
				product.getUnit(),
				b,
				product.getImages()
		);
	}

	public List<String> findAllImages(Long catId) {
		return subCategoryRepository.findAllImages(catId);
	}

}
