# Multiple Datasource Spring Boot PoC

This project demonstrates how to configure and use multiple datasources (PostgreSQL and SQL Server) within a single Spring Boot application. It showcases dynamic datasource routing using AOP (Aspect-Oriented Programming) to switch between different databases at runtime.

## Features

- **Multiple Database Support**: Seamlessly connect to both PostgreSQL and SQL Server databases
- **Dynamic Datasource Routing**: Switch between datasources at runtime using annotations
- **AOP-Based Implementation**: Clean separation of concerns using aspects
- **RESTful API**: Simple endpoints to demonstrate data retrieval from different databases
- **Database Initialization**: SQL scripts for initializing both databases with sample data

## Project Structure

```
multiple-datasource/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/multipledatasource/
│   │   │       ├── aspect/                  # AOP components for datasource switching
│   │   │       ├── config/                  # Datasource configurations
│   │   │       ├── controller/              # REST controllers
│   │   │       ├── entity/                  # Entity classes for both databases
│   │   │       │   ├── postgresql/          # PostgreSQL entities
│   │   │       │   └── sqlserver/           # SQL Server entities
│   │   │       ├── repository/              # Spring Data repositories
│   │   │       │   ├── postgresql/          # PostgreSQL repositories
│   │   │       │   └── sqlserver/           # SQL Server repositories
│   │   │       ├── service/                 # Service layer with datasource annotations
│   │   │       └── util/                    # Utility classes for datasource routing
│   │   └── resources/                       # Application properties and config files
│   └── test/                                # Test classes
├── db/                                      # Database initialization scripts
│   ├── initial-data-postgresql.sql          # PostgreSQL setup script
│   └── initial-data-sqlserver.sql           # SQL Server setup script
├── http/                                    # HTTP request samples
│   └── sample.http                          # Sample API requests
└── pom.xml                                  # Maven configuration
```

## Tech Stack

- **Java 21**: Latest Java version for modern language features
- **Spring Boot 3.5.5**: Framework for building Java applications
- **Spring Data JPA**: Simplified data access layer
- **PostgreSQL**: Open-source relational database
- **Microsoft SQL Server**: Enterprise relational database
- **Lombok**: Reduces boilerplate code
- **AspectJ**: For aspect-oriented programming
- **Maven**: Dependency management and build tool

## Getting Started

### Prerequisites

- JDK 21
- Maven 3.6+
- PostgreSQL database server
- SQL Server database instance
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Database Setup

1. **PostgreSQL Setup**:
   - Create a database named `multiple_datasource_postgresql`
   - Run the script at `db/initial-data-postgresql.sql` to initialize tables and data

2. **SQL Server Setup**:
   - Create a database named `multiple_datasource_sqlserver`
   - Run the script at `db/initial-data-sqlserver.sql` to initialize tables and data

### Configuration

Update the `application.properties` or `application.yml` file with your database connection details:

```yaml
# PostgreSQL Configuration
spring.datasource.postgresql.url=jdbc:postgresql://localhost:5432/multiple_datasource_postgresql
spring.datasource.postgresql.username=your_postgresql_username
spring.datasource.postgresql.password=your_postgresql_password
spring.datasource.postgresql.driver-class-name=org.postgresql.Driver

# SQL Server Configuration
spring.datasource.sqlserver.url=jdbc:sqlserver://localhost:1433;databaseName=multiple_datasource_sqlserver
spring.datasource.sqlserver.username=your_sqlserver_username
spring.datasource.sqlserver.password=your_sqlserver_password
spring.datasource.sqlserver.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### Building and Running

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

### Testing the API

You can use the provided HTTP request samples in the `http/sample.http` file or test with curl:

```bash
# Get bugs from PostgreSQL
curl http://localhost:8080/api/bugs

# Get flowers from SQL Server
curl http://localhost:8080/api/flowers

# Get seasons from PostgreSQL
curl http://localhost:8080/api/seasons
```

## License

This project is licensed under the MIT License – see the LICENSE file for details.

## Contributing

Contributions are welcome! Please fork this repo and submit a pull request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
