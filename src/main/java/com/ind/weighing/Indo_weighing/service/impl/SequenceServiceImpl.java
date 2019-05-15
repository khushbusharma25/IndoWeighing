package com.ind.weighing.Indo_weighing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import com.ind.weighing.Indo_weighing.domain.CustomSequences;
import com.ind.weighing.Indo_weighing.service.SequenceService;

@Service
public class SequenceServiceImpl implements SequenceService{ 
	
	@Autowired private MongoOperations mongo;
@Override
public int getNextSequence(String seqName)
{
    CustomSequences counter = mongo.findAndModify(
        query(where("_id").is(seqName)),
        new Update().inc("seq",1),
        options().returnNew(true).upsert(true),
        CustomSequences.class);
    return counter.getSeq();
}}
