package org.springbootcamp.blog.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("blog")
data class BlogProperties(val title: String, val banner: Banner) {
  data class Banner(val title: String, val content: String)
}