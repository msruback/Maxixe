package com.msruback.maxixe

import com.msruback.maxixe.ui.CharactersListScreenTests
import com.msruback.maxixe.ui.ContactsListScreenTests
import com.msruback.maxixe.ui.EventsListScreenTests
import com.msruback.maxixe.ui.NavDrawerTests
import com.msruback.maxixe.ui.PurchaseListScreenTests
import com.msruback.maxixe.ui.TagsListScreenTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    NavDrawerTests::class,
    PurchaseListScreenTests::class,
    CharactersListScreenTests::class,
    ContactsListScreenTests::class,
    EventsListScreenTests::class,
    TagsListScreenTests::class
)
class UITestSuite