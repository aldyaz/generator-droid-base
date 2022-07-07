object TestLib {

    private const val junitVersion = "4.12"
    private const val espressoVersion = "3.1.0"
    private const val androidTestSupportVersion = "1.1.0"
    private const val mockitoKotlinVersion = "2.2.0"
    private const val dexmakerMockitoVersion = "2.19.1"
    private const val robolectricVersion = "4.0.1"

    const val junit = "junit:junit:$junitVersion"
    const val androidRunner = "androidx.test:runner:$androidTestSupportVersion"
    const val androidRules = "androidx.test:rules:$androidTestSupportVersion"
    const val dexmakerMockito = "com.linkedin.dexmaker:dexmaker-mockito:$dexmakerMockitoVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
    const val espressoIntents =
        "com.android.support.test.espresso:espresso-intents:$espressoVersion"
    const val espressoContrib =
        "com.android.support.test.espresso:espresso-contrib:$espressoVersion"
    const val coreTesting = "androidx.arch.core:core-testing:${AndroidX.archVersion}"
    const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${KotlinLib.version}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion"
    const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

}