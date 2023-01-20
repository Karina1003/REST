package com.onlinestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinestore.entity.Category;
import com.onlinestore.entity.Product;
import com.onlinestore.repository.CategoryRepository;
import com.onlinestore.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.MOCK,
				 classes = OnlinestoreApplication.class)
@AutoConfigureMockMvc
class OnlinestoreApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testCreateProduct() throws Exception {
		String name = "ProductTest";
		String description = "descr";
		Long categoryId = 1L;
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(
				"/product/create?name="+name+"&description="+description+"&categoryId="+categoryId))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		Product productResult = objectMapper.readValue(response, Product.class);
		Assertions.assertEquals(name, productResult.getName());
		Assertions.assertEquals(description, productResult.getDescription());
	}

	@Test
	public void testGetProduct() {
		String name = "TestProductGet";
		String description = "";
		Category category = categoryRepository.findById(1L)
				.orElseThrow(()->new NoSuchElementException());
		Product productCreated = productRepository.save(new Product(name, description, category));
		Product productExpected = productRepository.findById(productCreated.getId())
				.orElseThrow(()->new NoSuchElementException());
		Assertions.assertEquals(productExpected, productCreated);
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Long idToFind = 10L;
		String name = "UpdatedProduct";
		String description = "descriptionNew";
		Long categoryId = 2L;
		Product productToUpdate = productRepository.findById(idToFind)
				.orElseThrow(()->new NoSuchElementException());
		productToUpdate.setName(name);
		productToUpdate.setDescription(description);
		productToUpdate.setCategory(categoryRepository.findById(categoryId)
						.orElseThrow(()->new NoSuchElementException()));
		mockMvc.perform(MockMvcRequestBuilders.put(
						"/product/update/"+idToFind+"?name="+name+"&description="+description+"&categoryId="+categoryId));
		Product productAfterUpdate = productRepository.findById(idToFind)
				.orElseThrow(()->new NoSuchElementException());
		Assertions.assertEquals(productAfterUpdate, productToUpdate);
	}

	@Test
	public void testDeleteProduct() throws Exception{
		String name = "TestProductDelete";
		String description = "";
		Category category = categoryRepository.findById(1L)
				.orElseThrow(()->new NoSuchElementException());
		Product productToDelete = productRepository.save(new Product(name, description, category));
		Long idToDelete = productToDelete.getId();
		mockMvc.perform(MockMvcRequestBuilders.delete(
				"/product/delete/"+idToDelete));
		Assertions.assertThrows(NoSuchElementException.class,()->productRepository.findById(idToDelete)
				.orElseThrow(()-> new NoSuchElementException()));
	}

	@Test
	public void testFindByFilter() throws Exception {
		int page = 0;
		int size = 2;
		String name = "Table";
		String description = "";
		Long categoryId = 2L;
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
						"/product/list?page="+page+"&size="+size+"&name="+name+"&description="+description))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		String resultArray = jsonObject.getJSONArray("content").toString();
		Assertions.assertTrue(resultArray.contains(name));
	}

	@Test
	public void testCreateCategory() throws Exception {
		String name = "CategoryTest";
		String description = "";
		Long categoryId = 1L;
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(
						"/category/create?name="+name+"&description="+description+"&categoryId="+categoryId))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		Category categoryResult = objectMapper.readValue(response, Category.class);
		Assertions.assertEquals(name, categoryResult.getName());
	}

	@Test
	public void testGetCategory() {
		String name = "CategoryTestGet";
		Category categoryCreated = categoryRepository.save(new Category(name));
		Category categoryExpected = categoryRepository.findById(categoryCreated.getId())
				.orElseThrow(()->new NoSuchElementException());
		Assertions.assertEquals(categoryExpected.getId(), categoryCreated.getId());
	}

	@Test
	public void testUpdateCategory() throws Exception {
		Long idToFind = 5L;
		String name = "UpdatedCategory";
		Category categoryToUpdate = categoryRepository.findById(idToFind)
				.orElseThrow(()->new NoSuchElementException());
		categoryToUpdate.setName(name);
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/category/update/"+idToFind+"?name="+name));
		Category categoryAfterUpdate = categoryRepository.findById(idToFind)
				.orElseThrow(()->new NoSuchElementException());
		Assertions.assertEquals(categoryToUpdate.getName(), categoryAfterUpdate.getName());
	}

	@Test
	public void testProductList() throws Exception {
		Long id = 2L;
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(
						"/category/"+id+"/products"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		System.out.println(response);
	}
}
