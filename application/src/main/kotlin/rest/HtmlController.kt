package org.springbootcamp.blog.rest

import org.springbootcamp.blog.configuration.BlogProperties
import org.springbootcamp.blog.persistence.ArticleRepository
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import org.springbootcamp.blog.persistence.Article
import org.springbootcamp.blog.persistence.User
import org.springbootcamp.blog.Extensions.format

@Controller
class HtmlController(private val repository: ArticleRepository,
										 private val properties: BlogProperties) {

	@GetMapping("/")
	fun blog(model: Model): String {
		model["title"] = properties.title
		model["banner"] = properties.banner
		model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
		return "blog"
	}

	@GetMapping("/article/{slug}")
	fun article(@PathVariable slug: String, model: Model): String {
		val article = repository
				.findBySlug(slug)
				?.render()
				?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
		model["title"] = article.title
		model["article"] = article
		return "article"
	}

	fun Article.render() = RenderedArticle(
			slug,
			title,
			headline,
			content,
			author,
			addedAt.format()
	)

	data class RenderedArticle(
			val slug: String,
			val title: String,
			val headline: String,
			val content: String,
			val author: User,
			val addedAt: String)

}
