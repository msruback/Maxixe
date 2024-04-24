package com.msruback.maxixe.database.exampledata

import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.Tag
import com.msruback.maxixe.database.queries.EverythingPurchase
import com.msruback.maxixe.database.queries.PurchaseSellerEvent

val basicPurchase = Purchase(
    0,
    "Brief Description of Purchase",
    123.50,
    10.00,
    22.50,
    1712792805
)

val purchaseWithSeller = PurchaseSellerEvent(
    Purchase(
        0,
        "Brief Description of Purchase",
        123.50,
        10.00,
        22.50,
        1712792805,
        0
    ), seller
)

val purchaseWithEvent = PurchaseSellerEvent(
    Purchase(
        0,
        "Brief Description of Purchase",
        123.50,
        10.00,
        22.50,
        1712792805
    ), event = basicEvent
)

val briefPurchase = PurchaseSellerEvent(
    Purchase(
        0,
        "Brief Description of Purchase",
        123.50,
        10.00,
        22.50,
        1712792805,
        0
    ), seller
)
val longPurchase = PurchaseSellerEvent(
    Purchase(
        0,
        "Longer Description of Purchase with more detail that goes onto three lines",
        123.50,
        10.00,
        22.50,
        1712792805,
        0
    ), seller
)
val ellipsePurchase = PurchaseSellerEvent(
    Purchase(
        0,
        "Really Long Description of Purchase that goes on for so long it gets cut off with ellipses instead of showing the entire thing",
        123.50,
        10.00,
        22.50,
        1712792805,
        0
    ), seller
)

val detailedPurchase = EverythingPurchase(
    Purchase(
        0,
        "Description of Purchase",
        123.50,
        10.00,
        30.00,
        1712792805,
        0
    ), seller,
    listOf(
        basicCharacter
    ),
    listOf(Tag(0,"test",0), Tag(0,"testing",0), Tag(0,"testing tag",1))
)