FROM eclipse-temurin:17

WORKDIR /app

# Download PostgreSQL JDBC driver
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar /app/postgresql.jar

# Copy Java source code
COPY Main.java .

# Compile Java inside container
RUN javac -cp postgresql.jar Main.java

# Run the program
CMD ["java", "-cp", ".:postgresql.jar", "Main"]