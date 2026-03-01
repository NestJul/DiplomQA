Запуск авто-тестов
1. Условия для запуска авто-тестов
Java JDK 11: Убедитесь, что у вас установлена Java Development Kit версии 11.
Android Studio: Убедитесь, что у вас установлена последняя версия Android Studio с настроенной файловой средой:
Добавлен путь до JAVA_HOME в переменные окружения.
Настроена переменная ANDROID_HOME, указан путь до SDK Android.
Эмулятор Android: Убедитесь, что у вас установлен и настроен эмулятор Android с версией API 31.
2. Клонирование и настройка проекта
Склонируйте репозиторий проекта:
git clone https://github.com/NestJul/DiplomQA
Откройте проект в Android Studio.
Подождите, пока завершится индексация и синхронизация проекта с Gradle.
3. Запуск UI-тестов:
* Через терминал.

  `./gradlew connectedAndroidTest`
* Через Android Studio

  `ru/iteco/fmhandroid/ui/chert/test`

  `Открыть контекстное меню -> Run Tests...`

4. Построение Allure-отчета:

* забрать файлы с устройства из `/data/data/ru.iteco.fmhandroid/files/storage/emulated/0/Android/data/ru.iteco.fmhandroid/files/allure-results`
* сложить их в `allure-reports`
* в терминале выполнить

  `allure serve allure-reports`



