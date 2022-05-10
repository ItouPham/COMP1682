package com.final_project.chriscosmetic.seed;

import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.entity.Category;
import com.final_project.chriscosmetic.entity.Role;
import com.final_project.chriscosmetic.entity.SubCategory;
import com.final_project.chriscosmetic.repository.AccountRepository;
import com.final_project.chriscosmetic.repository.CategoryRepository;
import com.final_project.chriscosmetic.repository.RoleRepository;
import com.final_project.chriscosmetic.repository.SubCategoryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryRepository categoryRepository;
	private SubCategoryRepository subCategoryRepository;
	private RoleRepository roleRepository;
	private AccountRepository accountRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public DataSeedingListener(CategoryRepository categoryRepository,
							   SubCategoryRepository subCategoryRepository,
							   RoleRepository roleRepository,
							   AccountRepository accountRepository,
							   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.categoryRepository = categoryRepository;
		this.subCategoryRepository = subCategoryRepository;
		this.roleRepository = roleRepository;
		this.accountRepository = accountRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//Category
		if(!categoryRepository.findByCategoryName("Face").isPresent()) {
			categoryRepository.save(new Category("Face"));
		}
		if(!categoryRepository.findByCategoryName("Lips").isPresent()) {
			categoryRepository.save(new Category("Lips"));
		}
		if(!categoryRepository.findByCategoryName("Eyes").isPresent()) {
			categoryRepository.save(new Category("Eyes"));
		}
		if(!categoryRepository.findByCategoryName("Cleansing").isPresent()) {
			categoryRepository.save(new Category("Cleansing"));
		}
		if(!categoryRepository.findByCategoryName("Mask").isPresent()) {
			categoryRepository.save(new Category("Mask"));
		}
		if(!categoryRepository.findByCategoryName("Skin").isPresent()) {
			categoryRepository.save(new Category("Skin"));
		}
		if(!categoryRepository.findByCategoryName("Skin body").isPresent()) {
			categoryRepository.save(new Category("Skin body"));
		}
		if(!categoryRepository.findByCategoryName("Hair").isPresent()) {
			categoryRepository.save(new Category("Hair"));
		}

		//SubCategory
		if(!subCategoryRepository.findBySubCategoryName("Foundation").isPresent()) {
			subCategoryRepository.save(new SubCategory("Foundation", categoryRepository.findByCategoryName("Face").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Highlight").isPresent()) {
			subCategoryRepository.save(new SubCategory("Highlight", categoryRepository.findByCategoryName("Face").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Concealer").isPresent()) {
			subCategoryRepository.save(new SubCategory("Concealer", categoryRepository.findByCategoryName("Face").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Blusher").isPresent()) {
			subCategoryRepository.save(new SubCategory("Blusher", categoryRepository.findByCategoryName("Face").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Lip Balm").isPresent()) {
			subCategoryRepository.save(new SubCategory("Lip Balm", categoryRepository.findByCategoryName("Lips").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Lipstick").isPresent()) {
			subCategoryRepository.save(new SubCategory("Lipstick", categoryRepository.findByCategoryName("Lips").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Lip Gloss").isPresent()) {
			subCategoryRepository.save(new SubCategory("Lip Gloss", categoryRepository.findByCategoryName("Lips").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Eyeliner").isPresent()) {
			subCategoryRepository.save(new SubCategory("Eyeliner", categoryRepository.findByCategoryName("Eyes").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Mascara").isPresent()) {
			subCategoryRepository.save(new SubCategory("Mascara", categoryRepository.findByCategoryName("Eyes").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Eyebrows").isPresent()) {
			subCategoryRepository.save(new SubCategory("Eyebrows", categoryRepository.findByCategoryName("Eyes").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Eye shadow").isPresent()) {
			subCategoryRepository.save(new SubCategory("Eye shadow", categoryRepository.findByCategoryName("Eyes").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Cleanser").isPresent()) {
			subCategoryRepository.save(new SubCategory("Cleanser", categoryRepository.findByCategoryName("Cleansing").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Makeup Remover").isPresent()) {
			subCategoryRepository.save(new SubCategory("Makeup Remover", categoryRepository.findByCategoryName("Cleansing").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Cotton pad").isPresent()) {
			subCategoryRepository.save(new SubCategory("Cotton pad", categoryRepository.findByCategoryName("Cleansing").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Clay Mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Clay Mask", categoryRepository.findByCategoryName("Mask").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Sheet Mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Sheet Mask", categoryRepository.findByCategoryName("Mask").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Eyes Mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Eyes Mask", categoryRepository.findByCategoryName("Mask").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Sleeping Mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Sleeping Mask", categoryRepository.findByCategoryName("Mask").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Lip mask").isPresent()) {
			subCategoryRepository.save(new SubCategory("Lip mask", categoryRepository.findByCategoryName("Mask").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Toner").isPresent()) {
			subCategoryRepository.save(new SubCategory("Toner", categoryRepository.findByCategoryName("Skin").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Serum").isPresent()) {
			subCategoryRepository.save(new SubCategory("Serum", categoryRepository.findByCategoryName("Skin").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Hydrating").isPresent()) {
			subCategoryRepository.save(new SubCategory("Hydrating", categoryRepository.findByCategoryName("Skin").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Sunscreen").isPresent()) {
			subCategoryRepository.save(new SubCategory("Sunscreen", categoryRepository.findByCategoryName("Skin").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Body Lotion").isPresent()) {
			subCategoryRepository.save(new SubCategory("Body Lotion", categoryRepository.findByCategoryName("Skin Body").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Body Scrub").isPresent()) {
			subCategoryRepository.save(new SubCategory("Body Scrub", categoryRepository.findByCategoryName("Skin Body").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Body Wash").isPresent()) {
			subCategoryRepository.save(new SubCategory("Body Wash", categoryRepository.findByCategoryName("Skin Body").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Body Cream").isPresent()) {
			subCategoryRepository.save(new SubCategory("Body Cream", categoryRepository.findByCategoryName("Skin Body").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Shampoo").isPresent()) {
			subCategoryRepository.save(new SubCategory("Shampoo", categoryRepository.findByCategoryName("Hair").get()));
		}
		if(!subCategoryRepository.findBySubCategoryName("Conditioner").isPresent()) {
			subCategoryRepository.save(new SubCategory("Conditioner", categoryRepository.findByCategoryName("Hair").get()));
		}

		//Role
		if(!roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.ADMIN.name()).isPresent()){
			roleRepository.save(new Role(UUID.randomUUID().toString(), "ADMIN"));
		}
		if(!roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.EMPLOYEE.name()).isPresent()){
			roleRepository.save(new Role(UUID.randomUUID().toString(), "EMPLOYEE"));
		}
		if(!roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.CUSTOMER.name()).isPresent()){
			roleRepository.save(new Role(UUID.randomUUID().toString(), "CUSTOMER"));
		}

		//Admin account
		if(!accountRepository.findByEmail("admin@gmail.com").isPresent()) {
			Account admin = new Account();
			admin.setId(UUID.randomUUID().toString());
			admin.setEmail("admin@gmail.com");
			String encodedPassword = bCryptPasswordEncoder.encode("123456");
			admin.setPassword(encodedPassword);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.ADMIN.name()).get());
			admin.setRoles(roles);
			admin.setAddress("Can Tho");
			admin.setFirstName("Cuong");
			admin.setLastName("Pham");
			admin.setTelephone("123456789");
			accountRepository.save(admin);
		}

		//Employee account
		if(!accountRepository.findByEmail("employee@gmail.com").isPresent()) {
			Account admin = new Account();
			admin.setId(UUID.randomUUID().toString());
			admin.setEmail("employee@gmail.com");
			String encodedPassword = bCryptPasswordEncoder.encode("123456");
			admin.setPassword(encodedPassword);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.EMPLOYEE.name()).get());
			admin.setRoles(roles);
			admin.setAddress("Can Tho");
			admin.setFirstName("Cuong");
			admin.setLastName("Pham");
			admin.setTelephone("123456789");
			accountRepository.save(admin);
		}

		//Customer account
		if(!accountRepository.findByEmail("customer@gmail.com").isPresent()) {
			Account admin = new Account();
			admin.setId(UUID.randomUUID().toString());
			admin.setEmail("customer@gmail.com");
			String encodedPassword = bCryptPasswordEncoder.encode("123456");
			admin.setPassword(encodedPassword);
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByRoleName(com.final_project.chriscosmetic.constant.Role.CUSTOMER.name()).get());
			admin.setRoles(roles);
			admin.setAddress("Can Tho");
			admin.setFirstName("Cuong");
			admin.setLastName("Pham");
			admin.setTelephone("123456789");
			accountRepository.save(admin);
		}
	}
}