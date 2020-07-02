import com.kentvu.csproblems.Playground
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class AlgorithmTests {
    val arr = arrayOf(1, 4, 3, 6, 7, 2)
    @Test
    fun insertionSortTest() {
        assertThat(Playground().insertionSortDec(arr), CoreMatchers.equalTo(10))
    }
    @Test
    fun insertionSortAscTest() {
        assertThat(Playground().insertionSortAsc(arr), CoreMatchers.equalTo(5))
        assertThat(Playground().insertionSortAsc(arrayOf(1,1,1,2,2)), CoreMatchers.equalTo(0))
    }
}