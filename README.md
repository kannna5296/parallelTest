# parallel


### 動作手順

```
// SQLServer用コンテナ
docker-compose up -d
// DB生成
gradle createDb
// テーブル生成
gradle flywayInfo
gradle flywayMigrate
// データ投入...（そのうち）
```


### 分からん事
・poiのバージョン周りを上手くいかせたい
Caused by: java.lang.NoSuchFieldError: Factory　今これが出てる

java16~17が最新すぎる説？
　java8で試してみる