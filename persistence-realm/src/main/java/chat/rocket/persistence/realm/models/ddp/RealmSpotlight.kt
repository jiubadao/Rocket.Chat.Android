package chat.rocket.persistence.realm.models.ddp

import chat.rocket.core.models.Spotlight
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.json.JSONObject

// This class must be annotated with open to work properly with Realm (Kotlin classes are final by default).
open class RealmSpotlight : RealmObject() {
    @PrimaryKey var _id: String? = null
    var name: String? = null
    var t: String? = null

    fun asSpotlight(): Spotlight {
        return Spotlight.builder()
                .setId(_id)
                .setName(name)
                .setType(t)
                .build()
    }

    companion object {
        fun customizeUserJSONObject(userJsonObject: JSONObject) {
            userJsonObject.put(Columns.NAME, userJsonObject.get("username"))
            userJsonObject.put(Columns.TYPE, "d")
            userJsonObject.remove("username")
            userJsonObject.remove("status")
        }
    }

    interface Columns {
        companion object {
            const val ID = "_id"
            const val NAME = "name"
            const val TYPE = "t"
        }
    }
}