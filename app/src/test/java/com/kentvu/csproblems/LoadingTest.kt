package com.kentvu.csproblems

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoadingTest {
    val title = "Numerical : numberZeroDigits"
    val problem =
        """
Given a natural number n. Find the number of zeros at the end of n! (n! is the product of continuous integers from 1 to n)

For example:

If n = 5, n! = 1*2*3*4*5 = 120, so numberZeroDigits(n) = 1
Input/output:

Input:
A natural number n (1 <= n <= 10^12).
Output:
Number of zeros at the end of n!
Execution time limit: 0.5 seconds""".trimIndent()
    val solutionCs = """
long NumberZeroDigits(long n)
{
    int countFactor2 = 0;
    int countFactor5 = 0;
    for (long i = 1; i <= n; i++)
    {
        if (i % 2 == 0)
        {
            countFactor2 += countFactor(i, 2);
        }

        if (i % 5 == 0)
        {
            countFactor5 += countFactor(i, 5);
        }
    }

    return Math.Min(countFactor2, countFactor5);
}

private int countFactor(long n, long factor)
{
    int count = 0;
    while (n % factor == 0 && n / factor > 0)
    {
        count++;
        n /= factor;
    }

    return count;
}""".trimIndent()

    @Test
    fun loadDataTest() {
        val data =
            """
.title
$title
.problem
$problem
.solution:${Language.CSharp.displayName}
$solutionCs
.endsolution
""".trimIndent()
        val problems = Repo(data).problems()
        assertEquals(problems[0].title, title)
        assertEquals(problems[0].solutions[0].lang, Language.CSharp.displayName)
        assertEquals(problems[0].solutions[0].code, solutionCs)
    }
}
