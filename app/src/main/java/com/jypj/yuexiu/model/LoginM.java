package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 * 登陸接口
 */

public class LoginM extends HttpResult {

    public class DataEntity {
        private String token;
        private String name;
        private String schoolName;
        private String imagePath;
        private DefaultRoleEntity defaultRole;
        private List<RolesEntity> roles;

        private List<ModulesEntity> modules;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public DefaultRoleEntity getDefaultRole() {
            return defaultRole;
        }

        public void setDefaultRole(DefaultRoleEntity defaultRole) {
            this.defaultRole = defaultRole;
        }

        public List<RolesEntity> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesEntity> roles) {
            this.roles = roles;
        }

        public List<ModulesEntity> getModules() {
            return modules;
        }

        public void setModules(List<ModulesEntity> modules) {
            this.modules = modules;
        }

        public class DefaultRoleEntity {
            private String role_name;
            private String role_type;
            private String role_id;

            public String getRole_name() {
                return role_name;
            }

            public void setRole_name(String role_name) {
                this.role_name = role_name;
            }

            public String getRole_type() {
                return role_type;
            }

            public void setRole_type(String role_type) {
                this.role_type = role_type;
            }

            public String getRole_id() {
                return role_id;
            }

            public void setRole_id(String role_id) {
                this.role_id = role_id;
            }
        }

        public class RolesEntity {
            private String role_name;
            private String role_type;
            private String role_id;

            public String getRole_name() {
                return role_name;
            }

            public void setRole_name(String role_name) {
                this.role_name = role_name;
            }

            public String getRole_type() {
                return role_type;
            }

            public void setRole_type(String role_type) {
                this.role_type = role_type;
            }

            public String getRole_id() {
                return role_id;
            }

            public void setRole_id(String role_id) {
                this.role_id = role_id;
            }
        }

        public class ModulesEntity extends Griditem {

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
