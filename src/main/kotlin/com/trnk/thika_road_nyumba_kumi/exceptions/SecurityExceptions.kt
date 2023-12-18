package com.trnk.thika_road_nyumba_kumi.exceptions

import org.springframework.security.core.AuthenticationException

class AuthExceptions(msg: String?) : AuthenticationException(msg)