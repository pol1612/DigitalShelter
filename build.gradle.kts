// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    extra.apply {
        set("lifecycle_version", "2.6.2")
    }
    repositories {
        google()
    }

}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.android.library") version "8.2.2" apply false
}
