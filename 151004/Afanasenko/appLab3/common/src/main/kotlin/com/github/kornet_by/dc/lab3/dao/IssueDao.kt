package com.github.kornet_by.dc.lab3.dao

import com.github.kornet_by.dc.lab3.bean.Issue

interface IssueDao {
	suspend fun create(item: Issue): Long

	suspend fun deleteById(id: Long): Int

	suspend fun getAll(): List<Issue?>

	suspend fun getById(id: Long): Issue

	suspend fun update(item: Issue): Int
}