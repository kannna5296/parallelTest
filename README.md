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