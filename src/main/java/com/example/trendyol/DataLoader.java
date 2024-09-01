package com.example.trendyol;

import com.example.trendyol.model.roles.RoleModel;
import com.example.trendyol.model.rolesprivileges.PrivilegeModel;
import com.example.trendyol.repository.PrivilegeRepository;
import com.example.trendyol.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (privilegeRepository.findAll().isEmpty()) {

            // Sub Category yetkileri
            PrivilegeModel subCategoryAddPrivilege = new PrivilegeModel();
            subCategoryAddPrivilege.setName("SUBCATEGORY_ADD_PRIVILEGE");
            privilegeRepository.save(subCategoryAddPrivilege);

            PrivilegeModel subCategoryDeletePrivilege = new PrivilegeModel();
            subCategoryDeletePrivilege.setName("SUBCATEGORY_DELETE_PRIVILEGE");
            privilegeRepository.save(subCategoryDeletePrivilege);

            PrivilegeModel subCategoryGetPrivilege = new PrivilegeModel();
            subCategoryGetPrivilege.setName("SUBCATEGORY_GET_ALL_PRIVILEGE");
            privilegeRepository.save(subCategoryGetPrivilege);

            PrivilegeModel subCategoryUpdatePrivilege = new PrivilegeModel();
            subCategoryUpdatePrivilege.setName("SUBCATEGORY_UPDATE_PRIVILEGE");
            privilegeRepository.save(subCategoryUpdatePrivilege);

            PrivilegeModel subCategoryAssignProductPrivilege = new PrivilegeModel();
            subCategoryAssignProductPrivilege.setName("GET_ALL_PRODUCTS_OF_SUB_CATEGORY_PRIVILEGE");
            privilegeRepository.save(subCategoryAssignProductPrivilege);

            // Product yetkileri
            PrivilegeModel productAddPrivilege = new PrivilegeModel();
            productAddPrivilege.setName("PRODUCT_ADD_PRIVILEGE");
            privilegeRepository.save(productAddPrivilege);

            PrivilegeModel productDeletePrivilege = new PrivilegeModel();
            productDeletePrivilege.setName("PRODUCT_DELETE_PRIVILEGE");
            privilegeRepository.save(productDeletePrivilege);

            PrivilegeModel productGetPrivilege = new PrivilegeModel();
            productGetPrivilege.setName("PRODUCT_GET_ALL_PRIVILEGE");
            privilegeRepository.save(productGetPrivilege);

            PrivilegeModel productUpdatePrivilege = new PrivilegeModel();
            productUpdatePrivilege.setName("PRODUCT_UPDATE_PRIVILEGE");
            privilegeRepository.save(productUpdatePrivilege);

            PrivilegeModel assignSubCategoryToProductPrivilege = new PrivilegeModel();
            assignSubCategoryToProductPrivilege.setName("PRODUCT_ASSIGN_SUBCATEGORY_PRIVILEGE");
            privilegeRepository.save(assignSubCategoryToProductPrivilege);

            // Basket yetkileri
            PrivilegeModel removeProductFromUserPrivilege = new PrivilegeModel();
            removeProductFromUserPrivilege.setName("PRODUCT_REMOVE_FROM_USER_PRIVILEGE");
            privilegeRepository.save(removeProductFromUserPrivilege);

            PrivilegeModel assignBasketToUserPrivilege = new PrivilegeModel();
            assignBasketToUserPrivilege.setName("PRODUCT_ASSIGN_TO_USER_PRIVILEGE");
            privilegeRepository.save(assignBasketToUserPrivilege);

            PrivilegeModel getBasketPrivilege = new PrivilegeModel();
            getBasketPrivilege.setName("BASKET_GET_PRIVILEGE");
            privilegeRepository.save(getBasketPrivilege);

            PrivilegeModel getBasketByIdPrivilege = new PrivilegeModel();
            getBasketByIdPrivilege.setName("BASKET_GET_BY_ID_PRIVILEGE");
            privilegeRepository.save(getBasketByIdPrivilege);

            // Base Category yetkileri
            PrivilegeModel baseCategoryAddPrivilege = new PrivilegeModel();
            baseCategoryAddPrivilege.setName("BASE_CATEGORY_ADD_PRIVILEGE");
            privilegeRepository.save(baseCategoryAddPrivilege);

            PrivilegeModel baseCategoryDeletePrivilege = new PrivilegeModel();
            baseCategoryDeletePrivilege.setName("BASE_CATEGORY_DELETE_PRIVILEGE");
            privilegeRepository.save(baseCategoryDeletePrivilege);

            PrivilegeModel baseCategoryGetPrivilege = new PrivilegeModel();
            baseCategoryGetPrivilege.setName("BASE_CATEGORY_GET_ALL_PRIVILEGE");
            privilegeRepository.save(baseCategoryGetPrivilege);

            PrivilegeModel baseCategoryUpdatePrivilege = new PrivilegeModel();
            baseCategoryUpdatePrivilege.setName("BASE_CATEGORY_UPDATE_PRIVILEGE");
            privilegeRepository.save(baseCategoryUpdatePrivilege);

            PrivilegeModel assignSubCategoryToBaseCategoryPrivilege = new PrivilegeModel();
            assignSubCategoryToBaseCategoryPrivilege.setName("BASE_CATEGORY_ASSIGN_SUBCATEGORY_PRIVILEGE");
            privilegeRepository.save(assignSubCategoryToBaseCategoryPrivilege);

            PrivilegeModel removeSubCategoryFromBaseCategoryPrivilege = new PrivilegeModel();
            removeSubCategoryFromBaseCategoryPrivilege.setName("BASE_CATEGORY_REMOVE_SUBCATEGORY_PRIVILEGE");
            privilegeRepository.save(removeSubCategoryFromBaseCategoryPrivilege);

            // User yetkileri
            PrivilegeModel userGetPrivilege = new PrivilegeModel();
            userGetPrivilege.setName("USER_GET_PRIVILEGE");
            privilegeRepository.save(userGetPrivilege);

            PrivilegeModel userGetAllPrivilege = new PrivilegeModel();
            userGetAllPrivilege.setName("USER_GET_ALL_PRIVILEGE");
            privilegeRepository.save(userGetAllPrivilege);

            PrivilegeModel userDeletePrivilege = new PrivilegeModel();
            userDeletePrivilege.setName("USER_DELETE_PRIVILEGE");
            privilegeRepository.save(userDeletePrivilege);

            PrivilegeModel userUpdatePrivilege = new PrivilegeModel();
            userUpdatePrivilege.setName("USER_UPDATE_PRIVILEGE");
            privilegeRepository.save(userUpdatePrivilege);

            PrivilegeModel getUserProductsPrivilege = new PrivilegeModel();
            getUserProductsPrivilege.setName("USER_GET_PRODUCTS_PRIVILEGE");
            privilegeRepository.save(getUserProductsPrivilege);

            // Authentication yetkileri
            PrivilegeModel loginPrivilege = new PrivilegeModel();
            loginPrivilege.setName("LOGIN_PRIVILEGE");
            privilegeRepository.save(loginPrivilege);

            PrivilegeModel registerPrivilege = new PrivilegeModel();
            registerPrivilege.setName("REGISTER_PRIVILEGE");
            privilegeRepository.save(registerPrivilege);


            PrivilegeModel refreshPrivilege = new PrivilegeModel();
            refreshPrivilege.setName("REFRESH_PRIVILEGE");
            privilegeRepository.save(refreshPrivilege);

            //Role yetkiler

            PrivilegeModel assignRoleToUserPrivilege = new PrivilegeModel();
            assignRoleToUserPrivilege.setName("ASSIGN_ROLE_TO_USER_PRIVILEGE");
            privilegeRepository.save(assignRoleToUserPrivilege);

            PrivilegeModel assignPrivilegeToRolePrivilege = new PrivilegeModel();
            assignPrivilegeToRolePrivilege.setName("ASSIGN_PRIVILEGE_TO_ROLE_PRIVILEGE");
            privilegeRepository.save(assignPrivilegeToRolePrivilege);

            PrivilegeModel getAllRoles=new PrivilegeModel();
            getAllRoles.setName("GET_ALL_ROLES_PRIVILEGE");
            privilegeRepository.save(getAllRoles);

            PrivilegeModel getAllPrivileges=new PrivilegeModel();
            getAllPrivileges.setName("GET_ALL_PRIVILEGES_PRIVILEGE");
            privilegeRepository.save(getAllPrivileges);


        }



        if (roleRepository.findAll().isEmpty()) {
            RoleModel userRole = new RoleModel();
            userRole.setName("USER");
            roleRepository.save(userRole);

            RoleModel adminRole = new RoleModel();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            RoleModel superUserRole = new RoleModel();
            superUserRole.setName("SUPER_USER");
            roleRepository.save(superUserRole);
        }
    }
}
