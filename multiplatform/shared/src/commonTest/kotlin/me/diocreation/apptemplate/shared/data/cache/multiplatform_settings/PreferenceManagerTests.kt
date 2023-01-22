package me.diocreation.apptemplate.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class PreferenceManagerTests {
    private val observableSettingsMock = MapSettings()
    private val preferencesManager: PreferencesManager =
        PreferencesManager(observableSettings = observableSettingsMock)

    @Test
    fun `set int save correct value`() = runTest {
        preferencesManager.setInt(key = "test", value = 1)
        val result = preferencesManager.getInt(key = "test").first()

        assertNotNull(result)
        assertEquals(actual = result, expected = 1)
    }

    @Test
    fun `set int save correct multiple times`() = runTest {
        (0..10).forEachIndexed { index, i ->
            preferencesManager.setInt(key = "test $index", value = i)
            val result = preferencesManager.getInt(key = "test $index").first()
            assertNotNull(result)
            assertEquals(actual = result, expected = i)
        }
    }

    @Test
    fun `clear preference clears cache`() = runTest {
        preferencesManager.setInt(key = "test", value = 1)
        val savedValue = preferencesManager.getInt(key = "test").first()

        assertNotNull(savedValue)
        preferencesManager.clearPreferences()
        val result = preferencesManager.getInt(key = "test").first()
        assertNull(result)
    }

    @Test
    fun `clear preferences clears cache for multiple values`() = runTest {
        (0..11).forEachIndexed { index, i ->
            preferencesManager.setInt(key = "test $index", value = i)

            val savedValue = preferencesManager.getInt(key = "test $index").first()

            assertNotNull(savedValue)
        }

        preferencesManager.clearPreferences()

        (0..11).forEachIndexed { index, _ ->
            val result = preferencesManager.getInt(key = "test $index").first()
            assertNull(result)
        }
    }

}