package org.jetbrains.kotlin.gradle.plugin.android

import com.android.build.gradle.BasePlugin

class AndroidGradleWrapper {
    static def getRuntimeJars(BasePlugin basePlugin) {
        try {
            return basePlugin.getRuntimeJarList()
        }
        catch (Exception e) {
            return basePlugin.getBootClasspath()
        }
    }
}
