package com.sabgil.contena.entitiy

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity(name = "report")
class ReportEntity(
        @EmbeddedId
        var reportKey: ReportKey? = null,

        @Column(name = "contents")
        var contents: String = ""
) {
    @Embeddable
    class ReportKey(
            var user_id: String = "",
            var post_id: Long = 0L
    ) : Serializable
}