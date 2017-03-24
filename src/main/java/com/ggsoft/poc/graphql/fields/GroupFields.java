package com.ggsoft.poc.graphql.fields;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.graphql.dataFetchers.GroupDataFetcher;
import com.merapar.graphql.GraphQlFields;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
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
 * Created by Victor Gil on 3/24/2017.
 */
@Component
public class GroupFields implements GraphQlFields {

    @Autowired
    private GroupDataFetcher groupDataFetcher;

    @Autowired
    private UserFields userFields;
    private GraphQLObjectType groupType;
    private GraphQLInputObjectType filterGroupType;
    private GraphQLInputObjectType addMemberType;
    private GraphQLInputObjectType createGroupInputType;
    private GraphQLFieldDefinition groupsField;
    private GraphQLFieldDefinition addMemberField;
    private GraphQLFieldDefinition createGroupField;

    public GraphQLObjectType getGroupType() {
        return groupType;
    }

    @PostConstruct
    public void init() {
        createTypes();
        createFields();
    }

    private void createFields() {
        groupsField = newFieldDefinition().name("groups").description("provide an overview of all groups")
                .type(new GraphQLList(groupType))
                .argument(newArgument().type(filterGroupType).name(FILTER).build())
                .dataFetcher(environment -> groupDataFetcher.getGroupsByFilter(getFilterMap(environment)))
                .build();

        addMemberField = newFieldDefinition().name("addMember").description("adds a member")
                .type(groupType)
                .argument(newArgument().type(addMemberType).name(INPUT).build())
                .dataFetcher(environment -> groupDataFetcher.addGroupMember(getInputMap(environment)))
                .build();

        createGroupField = newFieldDefinition().name("createGroup").description("create a group of users")
                .type(groupType)
                .argument(newArgument().type(createGroupInputType).name(INPUT).build())
                .dataFetcher(environment -> groupDataFetcher.createGroup(getInputMap(environment)))
                .build();
    }

    private void createTypes() {
        groupType = newObject().name("groups").description("A group of users")
                .field(newFieldDefinition().name("id").description("group id").type(GraphQLString).build())
                .field(newFieldDefinition().name("name").description("group name").type(GraphQLString).build())
                .field(newFieldDefinition().name("members").description("group members").type(new GraphQLList(userFields.getUserType()))
                        .dataFetcher(environment -> groupDataFetcher.getGroupMembers((Group) environment.getSource())).build())
                .build();
        filterGroupType = newInputObject().name("filterGroupInput")
                .field(newInputObjectField().name("id").type(GraphQLString).build())
                .field(newInputObjectField().name("userId").type(GraphQLString).build())
                .build();
        createGroupInputType = newInputObject().name("createGroupInput")
                .field(newInputObjectField().name("name").type(GraphQLString).build())
                .build();
        addMemberType = newInputObject().name("addGroupMemberInput")
                .field(newInputObjectField().name("groupId").type(GraphQLString).build())
                .field(newInputObjectField().name("userId").type(GraphQLString).build())
                .build();
    }

    @Override
    public List<GraphQLFieldDefinition> getQueryFields() {
        return Collections.singletonList(groupsField);
    }

    @Override
    public List<GraphQLFieldDefinition> getMutationFields() {
        return Arrays.asList(createGroupField, addMemberField);
    }
}
