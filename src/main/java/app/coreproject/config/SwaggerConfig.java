package app.coreproject.config;

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    private static final String clientId = "school_bus";
//    private static final String clientSecret = "school_bus";
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                // @formatter:off
//                .select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
////				.securitySchemes(Collections.singletonList(securitySchema()))
//                .securityContexts(Collections.singletonList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()))
//                ;
//        // @formatter:on
//
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant("/**"))
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//
//        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[4];
//        authorizationScopes[0] = new AuthorizationScope("read", "read all");
//        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
//        authorizationScopes[2] = new AuthorizationScope("write", "write all");
//        authorizationScopes[3] = new AuthorizationScope("global", "accessEverything");
//        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
//    }
//
//    @Bean
//    public SecurityConfiguration securityInfo() {
//        return SecurityConfigurationBuilder.builder().clientId(clientId).clientSecret(clientSecret).scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true).build();
//    }
//
//}
