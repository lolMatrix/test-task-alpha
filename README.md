# Валюта в gif
Сервис, который выдает gif картинку по разнице курса валюты нынешнего дня и предыдущего
## Настройка сервиса
Для этого в конфигурационном файле (/src/main/resource/application.properties) нужно прописать конфигурацию. Либо при запуске указать эти параметры в формате --{param_name}={value} указать при запуске.
- com.matrix.currency.url - url сервиса для получения курса валют
- com.matrix.gif.url - url сервиса получения gif изображений
- com.matrix.currency.apiKey - api ключ для сервиса получения курса валют
- com.matrix.gif.apiKey - api ключ получения gif изображений
- com.matrix.currency.base - валюта по отношении к которой сравнивается курс (по умолчанию: USD) 
- com.matrix.gif.rate.up - поисковая строка для выбора gif изображений (для повышения курса) 
- com.matrix.gif.rate.down - поиспоисковая строка для выбора gif изображений (для уменьшения курса)
- com.matrix.gif.limit - лимит gif картинок для запроса
Все параметры, кроме параметров api ключей инициализированы по умолчанию.
## Сборка проекта
`.\gradlew build`
## Запуск в докере
    docker build -t alpha/currencytogif .
    docker run -p 8080:8080 alpha/currencytogif --com.matrix.gif.apiKey={currency_service_api_key} --com.matrix.currency.apiKey={gif_service_api_key}