package com.enqode.part2.dto.response;

import java.math.BigInteger;
import java.util.List;

public class DetailedInformationResponse {
    private List<Record> records;
    private int status;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class Record {
        private String systemid;
        private String owner;
        private String storageid;
        private boolean instore;
        private int size;
        private int accesslevel;
        private int type;
        private String media;
        private String added;
        private String date;
        private String name;
        private String description;
        private int xscore;
        private BigInteger simhash;
        private String bucket;
        private List<Relation> relations;
        private String accesslevelh;
        private String mediah;
        private String simhashh;
        private String typeh;

        public int getAccesslevel() {
            return accesslevel;
        }

        public void setAccesslevel(int accesslevel) {
            this.accesslevel = accesslevel;
        }

        public String getAccesslevelh() {
            return accesslevelh;
        }

        public void setAccesslevelh(String accesslevelh) {
            this.accesslevelh = accesslevelh;
        }

        public String getAdded() {
            return added;
        }

        public void setAdded(String added) {
            this.added = added;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isInstore() {
            return instore;
        }

        public void setInstore(boolean instore) {
            this.instore = instore;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getMediah() {
            return mediah;
        }

        public void setMediah(String mediah) {
            this.mediah = mediah;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public List<Relation> getRelations() {
            return relations;
        }

        public void setRelations(List<Relation> relations) {
            this.relations = relations;
        }

        public BigInteger getSimhash() {
            return simhash;
        }

        public void setSimhash(BigInteger simhash) {
            this.simhash = simhash;
        }

        public String getSimhashh() {
            return simhashh;
        }

        public void setSimhashh(String simhashh) {
            this.simhashh = simhashh;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getStorageid() {
            return storageid;
        }

        public void setStorageid(String storageid) {
            this.storageid = storageid;
        }

        public String getSystemid() {
            return systemid;
        }

        public void setSystemid(String systemid) {
            this.systemid = systemid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeh() {
            return typeh;
        }

        public void setTypeh(String typeh) {
            this.typeh = typeh;
        }

        public int getXscore() {
            return xscore;
        }

        public void setXscore(int xscore) {
            this.xscore = xscore;
        }

        public static class Relation {
            private String target;
            private int relation;

            public int getRelation() {
                return relation;
            }

            public void setRelation(int relation) {
                this.relation = relation;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }
        }
    }
}
