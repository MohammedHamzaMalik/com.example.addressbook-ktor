import org.jetbrains.exposed.sql.Database

data class AppContext(
    val db: Database
)

data class CommandContext(
    val db: Database
)

fun AppContext.toCommandContext(): CommandContext = CommandContext(db=this@toCommandContext.db)