package com.msruback.maxixe

import com.msruback.maxixe.database.CharacterTests
import com.msruback.maxixe.database.ContactTests
import com.msruback.maxixe.database.EventTests
import com.msruback.maxixe.database.PurchaseTests
import com.msruback.maxixe.database.TagTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CharacterTests::class,
    ContactTests::class,
    EventTests::class,
    PurchaseTests::class,
    TagTests::class
)
class DatabaseTestSuite