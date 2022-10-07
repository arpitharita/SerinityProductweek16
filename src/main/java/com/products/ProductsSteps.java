package com.products;

import com.products.constants.EndPoints;
import com.products.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {

    @Step("Creating product with name :{0},type: {1},price: {2},upc: {3],shipping: {4],description: {5},manufacture: {6}")
    public ValidatableResponse createProduct(String name, String type, int price, String upc, int shipping, String description, String manufacture, String model)
    {
        ProductsPojo productsPojo =new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacture);
        productsPojo.setModel(model);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productsPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);

    }
    @Step("getting product info by name:{0}")
    public HashMap<String,Object> getProductInfoByName(String name)
    {
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1+name+p2);

    }
    @Step("Update with name :{0},type: {1},price: {2},upc: {3],shipping: {4],description: {5},manufacture: {6}")
    public ValidatableResponse updateProduct(int productid, String name, String type, int price, String upc, int shipping, String description, String manufacture, String model)
    {
        ProductsPojo productsPojo =new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacture);
        productsPojo.setModel(model);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("productid",productid)
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();


    }
    @Step("deleteing product information with productid:{0}")
    public ValidatableResponse deleteProductInfoById(int productid)
    {
        return SerenityRest.given()
                .pathParam("productid",productid)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();

    }
    @Step("getting product info by productid:{0}")
    public ValidatableResponse getProductInfoByProductid(int productid)
    {
        return SerenityRest.given().log().all()
                .pathParam("productid",productid)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }



}
