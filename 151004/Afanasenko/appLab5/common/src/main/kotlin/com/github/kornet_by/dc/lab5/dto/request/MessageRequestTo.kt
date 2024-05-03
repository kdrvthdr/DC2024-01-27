package com.github.kornet_by.dc.lab5.dto.request

import com.github.kornet_by.dc.lab5.bean.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageRequestTo(
	private val issueId: Long, val content: String
) {
	fun toBean(id: Long?, country: String?): Message = Message(
		id, country, issueId, content
	)

	init {
		require(content.length in 4..2048)
	}
}