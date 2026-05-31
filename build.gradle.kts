// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
}
tasks.withType<Test> {
    // Способ А: Разрешить динамическую загрузку (самый простой путь для тестов)
    jvmArgs("-XX:+EnableDynamicAgentLoading")

    // ИЛИ Способ Б: Отключить предупреждения о byte-buddy, если они мешают в логах
    // jvmArgs("-XX:+EnableDynamicAgentLoading", "-Dnet.bytebuddy.agent.attacher.silent=true")
}
