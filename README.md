# -SensorsAndMeasurementsRestApi-

Это REST API сервис,он работает с базой данных которая хранит в себе  метеорологические датчики (Далее Сенсоры) и их измерения,связь между приложением и Сенсором реализована посредством отправки JSON файлов. 

Используя его человек может зарегистрировать сенсор, посмотреть список сенсоров, отправить измерение и посмотреть список измерений. 

Так как реального сенсора у нас нет, в качестве сенсора будет
выступать наш собственный компьютер.
То есть на нашем компьютере будет работать сервер со Spring 
REST API приложением, и наш же компьютер будет слать HTTP 
запросы к Spring приложению так, как будто он и есть "сенсор".

Для удобного взаимодействия был сделан телеграм бот, для того чтобы все работало нужно запустить это приложение, а потом запустить бот. 

Вы можете делать post\get запросы и вручную через постман, но через бота будет гораздо удобнее :)

This is a REST API service, it works with a database that stores meteorological sensors (hereinafter Sensors) and their measurements, communication between the application and the Sensor is implemented by sending JSON files.

Using it, a person can register a sensor, view a list of sensors, send a measurement, and view a list of measurements.

Since we do not have a real sensor, the sensor will be
act our own computer.
That is, a server with Spring will work on our computer
REST API application, and our computer will send HTTP
requests to the Spring application as if it were the "sensor".

For convenient interaction, a telegram bot was made, in order for everything to work, you need to run this application, and then run the bot.

You can also make post\get requests manually through postman, but it will be much more convenient through a bot :)
