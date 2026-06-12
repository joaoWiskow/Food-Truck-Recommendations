#Food-Truck-Recommendations
API REST de recomendação de food trucks por localização, desenvolvida com Spring Boot 4 e integração com AWS GeoPlaces.
Tecnologias
Java 17
Spring Boot 4
Spring Web MVC
Spring Validation
AWS GeoPlaces
Clean Architecture

Funcionalidades

Busca de food trucks por localização via AWS GeoPlaces
Recomendação de estabelecimentos próximos ao usuário
Arquitetura limpa com separação de responsabilidades em camadas

Como executar
bash# Clonar o repositório
git clone https://github.com/joaoWiskow/Food-Truck-Recommendations

No AwsGcpConfig.java, injete em 
minhaKey a chave 
e em minhaSecret o segredo


bash# Rodar o projeto
./mvnw spring-boot:run
