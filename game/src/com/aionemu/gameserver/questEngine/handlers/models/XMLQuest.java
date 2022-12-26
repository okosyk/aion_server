package com.aionemu.gameserver.questEngine.handlers.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.aionemu.gameserver.questEngine.QuestEngine;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuestScriptData")
@XmlSeeAlso({ReportToData.class, RelicRewardsData.class, CraftingRewardsData.class, ReportToManyData.class, MonsterHuntData.class,
ItemCollectingData.class, WorkOrdersData.class, XmlQuestData.class, MentorMonsterHuntData.class, ItemOrdersData.class,
FountainRewardsData.class, SkillUseData.class})
public abstract class XMLQuest
{
	@XmlAttribute(name = "id", required = true)
	protected int id;
	
	@XmlAttribute(name = "movie", required = false)
	protected int questMovie;
	
	@XmlAttribute(name = "mission", required = false)
	protected boolean mission;
	
	public int getId() {
		return id;
	}
	
	public int getQuestMovie() {
		return questMovie;
	}
	
	public boolean isMission() {
		return mission;
	}
	
	public void setMission(boolean mission) {
		this.mission = mission;
	}
	
	public abstract void register(QuestEngine questEngine);
}