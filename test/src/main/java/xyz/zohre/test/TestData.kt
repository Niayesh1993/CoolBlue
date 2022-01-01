package xyz.zohre.test

import xyz.zohre.data.model.CoolBlueResponse
import xyz.zohre.data.model.Products
import xyz.zohre.data.model.ReviewInformation
import xyz.zohre.data.model.ReviewSummary

object TestData {
    private val reviewInfo = ReviewInformation(
       reviews = mutableListOf ("review1","review2"), ReviewSummary(9.2, 8)
    )

    private val list = mutableListOf<Products>(
        Products(101, "p1", reviewInfo, mutableListOf("usp1", "usp2"), 2, 333.25, "img", true),
        Products(102, "p2", reviewInfo, mutableListOf("usp3", "usp4"), 2, 1090.0, "img", false),
        Products(103, "p3", reviewInfo, mutableListOf("usp5", "usp6"), 2, 200.21, "img", true)
        )

    val testResponse = CoolBlueResponse(list,1, 2, 2, 1)
}