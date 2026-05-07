## mysql-dialect 增加函数

修改 [MySqlTypeResolver.kt](dialects/mysql/src/main/kotlin/app/cash/sqldelight/dialects/mysql/MySqlTypeResolver.kt) 文件的 SqlFunctionExpr.mySqlFunctionType() 扩展函数。

## 发布

发布的时候需要发布两个 module

### 发布到本地

```shell
./gradlew :dialects:mysql:publishMavenPublicationToInstallLocallyRepository

./gradlew :sqldelight-compiler:dialect:publishMavenPublicationToInstallLocallyRepository
```

### 发布到云端

```shell
./gradlew :dialects:mysql:publishMavenPublicationToInternalRepository

./gradlew :sqldelight-compiler:dialect:publishMavenPublicationToInternalRepository
```
