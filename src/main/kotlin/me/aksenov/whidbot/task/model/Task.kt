package me.aksenov.whidbot.task.model

import java.sql.Timestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "user_task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column
    val started: Timestamp = Timestamp.from(Instant.now()),
    @Column(name = "spent_minutes")
    val spentMinutes: Long = 0,
    @Column
    val message: String? = null,
    @Column(name = "telegram_id")
    val telegramId: String? = null,
    @Column
    @Enumerated(EnumType.STRING)
    val status: TaskStatus = TaskStatus.IN_PROGRESS,
    @Column
    val updated: Timestamp = started
) {

    fun toMessageBody(): String = message!!.substringBefore("\n[minutes spent ")
        .let { "$it\n\n[minutes spent ${spentMinutes}]" }
}

enum class TaskStatus(val command: String, val text: String) {
    IN_PROGRESS("/in_progress", "Continue..."), STOPPED("/stop", "Stop")
}