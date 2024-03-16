postgresinit:
	docker run --name postgresGo -p 5432:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=password -d postgres:15-alpine
postgres:
	docker exec -it postgresGo psql
createdbusers:
	docker exec -it postgresGo createdb --username=root --owner=root users
dropdbusers:
	docker exec -it postgresGo dropdb --username=root --owner=root users
bash:
	docker exec -it postgresGo bash

.PHONY: postgresinit postgres createdbusers