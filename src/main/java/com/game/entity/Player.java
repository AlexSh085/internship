package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false, length = 12)
    private String name;

    @Column(name="title", nullable = false, length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name="race", nullable = false)
    private Race race;

    @Enumerated(EnumType.STRING)
    @Column(name="profession", nullable = false)
    private Profession profession;

    @Column(name="experience", nullable = false)
    private Integer experience;

    @Column(name="level", nullable = false)
    private Integer level;

    @Column(name="untilNextLevel", nullable = false)
    private Integer untilNextLevel;

    @Column(name="birthday", nullable = false)
    private Date birthday;

    @Column(name="banned", nullable = false)
    private Boolean banned;

    public Player(String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;
    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public boolean areAllFieldsFilled() {
        if (this.name == null
                || this.title == null
                || this.race == null
                || this.profession == null
                || this.birthday == null
                || this.experience == null) return false;
        return true;
    }

    public void ignoringUnnecessaryData() {
        this.id = null;
        this.untilNextLevel = null;
        this.level = null;
    }

    public void updatingWith(Player updatedFields) {
        updatedFields.id = null;
        updatedFields.level = null;
        updatedFields.untilNextLevel = null;
        if (updatedFields.name != null) this.name = updatedFields.name;
        if (updatedFields.title != null) this.title = updatedFields.title;
        if (updatedFields.race != null) this.race = updatedFields.race;
        if (updatedFields.profession != null) this.profession = updatedFields.profession;
        if (updatedFields.birthday != null) this.birthday = updatedFields.birthday;
        if (updatedFields.banned != null) this.banned = updatedFields.banned;
        if (updatedFields.experience != null) {
            this.experience = updatedFields.experience;
            setLevelAndUntilNext(this.experience);
        }
    }

    public void setLevelAndUntilNext(Integer experience) {
        Integer currentLevel = ((int)Math.sqrt(2500+200*experience) - 50)/100;
        Integer expUntil = 50*(currentLevel+1)*(currentLevel+2)- experience;
        this.level = currentLevel;
        this.untilNextLevel = expUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                Objects.equals(title, player.title) &&
                race == player.race &&
                profession == player.profession &&
                Objects.equals(experience, player.experience) &&
                Objects.equals(level, player.level) &&
                Objects.equals(untilNextLevel, player.untilNextLevel) &&
                Objects.equals(birthday, player.birthday) &&
                Objects.equals(banned, player.banned);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, title, race, profession, experience, level, untilNextLevel, birthday, banned);
    }
}
