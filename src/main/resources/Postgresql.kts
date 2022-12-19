@file:DependsOn("org.postgresql:postgresql:42.5.1")

import java.sql.Connection
import java.sql.DriverManager

val connection: Connection = DriverManager.getConnection(
    "jdbc:postgresql://localhost:5433/postgres",
    "postgres",
    "password"
)

println("Connected: ${connection.isValid(0)}")

connection.prepareStatement("select * from versions").use { stmt ->
    stmt.executeQuery().use { rs ->
        while (rs.next()) {
            val id = rs.getString(1)
            val version = rs.getString(2)
            println("version(id=$id, version=$version)")
        }
    }
}