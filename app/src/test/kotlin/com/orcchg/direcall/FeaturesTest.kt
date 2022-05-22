package com.orcchg.direcall

import com.orcchg.direcall.apptest.checkFeatures
import org.junit.Test

class FeaturesTest {

    @Test
    fun `check feature impl`() {
        checkFeatures(features())
    }
}