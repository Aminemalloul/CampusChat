FROM thyrlian/android-sdk

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build -x lint || true  

EXPOSE 5037

CMD ["adb", "-a", "nodaemon", "server"]
