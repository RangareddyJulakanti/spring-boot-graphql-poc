package com.ggsoft.poc.graphql.fields;

import com.ggsoft.poc.graphql.dataFetchers.UserDataFetcher;
import com.merapar.graphql.GraphQlFields;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

import static com.merapar.graphql.base.GraphQlFieldsHelper.*;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by Victor Gil on 3/23/2017.
 */

@Component
public class UserFields implements GraphQlFields {

    @Autowired
    private UserDataFetcher userDataFetcher;

    private GraphQLObjectType userType;
    private GraphQLInputObjectType filterUserType;

    private GraphQLInputObjectType addUserInputType;

    private GraphQLFieldDefinition usersField;
    private GraphQLFieldDefinition addUserField;

    @Override
    public List<GraphQLFieldDefinition> getQueryFields() {
        return Collections.singletonList(usersField);
    }

    @Override
    public List<GraphQLFieldDefinition> getMutationFields() {
        return Collections.singletonList(addUserField);
    }

    @PostConstruct
    public void init() {
        createTypes();
        createFields();
    }

    private void createFields() {
        usersField = newFieldDefinition().name("users").description("Provide an overview of all users")
                .type(new GraphQLList(userType))
                .argument(newArgument().name(FILTER).type(filterUserType).build())
                .dataFetcher(dataFetchingEnvironment -> userDataFetcher.getUsersByFilter(getFilterMap(dataFetchingEnvironment)))
                .build();

        addUserField = newFieldDefinition()
                .name("addUser").description("Add a new user")
                .type(userType)
                .argument(newArgument().name(INPUT).type(new GraphQLNonNull(addUserInputType)).build())
                .dataFetcher(dataFetchingEnvironment -> userDataFetcher.addUser(getInputMap(dataFetchingEnvironment)))
                .build();
    }

    private void createTypes() {
        userType = newObject().name("user").description("A user")
                .field(newFieldDefinition().name("id").description("user id").type(GraphQLString).build())
                .field(newFieldDefinition().name("email").description("email address").type(GraphQLString).build())
                .field(newFieldDefinition().name("phoneNumber").description("telephone number").type(GraphQLString).build())
                .field(newFieldDefinition().name("streetAddress").description("street address").type(GraphQLString).build())
                .field(newFieldDefinition().name("fullName").description("full name, first and last").type(GraphQLString).build())

                .build();
        filterUserType = newInputObject().name("filterUserInput")
                .field(newInputObjectField().name("id").type(GraphQLString).build())
                .build();

        addUserInputType = newInputObject().name("addUserInput")
                .field(newInputObjectField().name("email").type(GraphQLString).build())
                .field(newInputObjectField().name("phoneNumber").type(GraphQLString).build())
                .field(newInputObjectField().name("streetAddress").type(GraphQLString).build())
                .field(newInputObjectField().name("fullName").type(GraphQLString).build())
                .build();

    }
}
