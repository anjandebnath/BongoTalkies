import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.bongotalkies.launchFragmentInHiltContainer
import com.assignment.bongotalkies.views.top_movie.MovieListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class FragmentTestSuite {

    @Test
    fun testMovieListFragment() {
        launchFragmentInHiltContainer<MovieListFragment> {
            // The "fragmentArgs" argument is optional.
            val fragmentArgs = bundleOf("numElements" to 0)
            val scenario = launchFragmentInContainer<MovieListFragment>(fragmentArgs)
            scenario.recreate() // recreate the fragment
        }

    }
}