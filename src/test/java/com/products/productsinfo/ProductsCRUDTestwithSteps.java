package com.products.productsinfo;

import com.products.ProductsSteps;
import com.products.testbase.TestBase;
import com.products.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTestwithSteps extends TestBase {

    static String name ="Duracell - D Batteries (4-Pack)51383" + TestUtils.getRandomValue();
    static String type = "HardGood39261" + TestUtils.getRandomValue();
    static int price = 120;
    static String upc = "041333430010";
    static int shipping = 235;
    static String description = "From our expanded online assortment; compatible with select GM vehicles; plastic material";
    static String manufacture = "Metra";
    static String model = "99-4500";

    static int productid;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product")
    @Test
    public void test001()
    {
        ValidatableResponse response = productsSteps.createProduct(name,type,price,upc,shipping,description,manufacture,model);
        response.log().all().statusCode(201);

    }
    @Title("Verify if product is created")
    @Test
    public void test002()
    {
        HashMap<String,Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
        productid = (int) productMap.get("id");
        System.out.println(productid);

    }
    @Title("update the user information")
    @Test
    public void test003()
    {
        name = name+"update";

        productsSteps.updateProduct(productid,name,type,price,upc,shipping,description,manufacture,model);
        HashMap<String,Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));

    }
    @Title("Delete product info by productid and verify its deleted")
    @Test
    public void test004()
    {
        productsSteps.deleteProductInfoById(productid).log().all().statusCode(200);
        productsSteps.getProductInfoByProductid(productid).log().all().statusCode(404);

    }


}
