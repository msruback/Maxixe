package com.msruback.maxixe.ui.composables

import com.msruback.maxixe.database.entities.Contact
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.Socials
import com.msruback.maxixe.database.queries.PurchaseWithSeller

val seller = Contact(0, "Seller", "They/Them", "Seller Description", Socials())
val briefPurchase = PurchaseWithSeller(
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
val longPurchase = PurchaseWithSeller(
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
val ellipsePurchase = PurchaseWithSeller(
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