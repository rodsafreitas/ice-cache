# 🧊 Ice Cache 1.0

Ice Cache is a lightweight annotation-based caching library for Spring applications.
The main idea of this project is to allow developers to cache method results using the @Cache annotation, avoiding repeated processing and returning the value stored in memory.

## ⚙️ How It Works

When a method is annotated with @Cache, Ice Cache will:
1. Intercept the method execution
2. Check if a cached value already exists for the given key
3. If a value exists: return the cached value
4. If no value exists: execute the method, store the returned value in memory, and return the value.


## 📦 How to Download

Add Ice Cache to your project using your preferred build tool:

<img src="https://cdn.jsdelivr.net/gh/simple-icons/simple-icons/icons/gradle.svg" width="20"/> Gradle (build.gradle)

```
implementation("io.github.rodsafreitas:ice-cache:1.0.1")
```

<img src="https://cdn.jsdelivr.net/gh/simple-icons/simple-icons/icons/apachemaven.svg" width="20"/> Maven (pom.xml)

```
<dependency>
    <groupId>io.github.rodsafreitas</groupId>
    <artifactId>ice-cache</artifactId>
    <version>1.0.1</version>
</dependency>
```


## 🎯 Features  


### 🔑 Key Parameter

Defines the unique key used to store the method’s return value.
If the same key is used in another method, the cached value associated with that key will be returned.
This allows flexible cache sharing between methods if it's necessary.

### ⏳ Expire Parameter
Defines the cache expiration time.
If the cache is expired: the value is removed, the method is executed again, and a new value is stored.
If the cache is valid: the stored value is returned directly from memory.
