/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package samples.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "data_lineage")
@Data
public class DataLineage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    Long id;

    @Column(name = "source_db_name")
    String sourceDataBaseName;

    @Column(name = "source_tb_name")
    String sourceTableName;

    @Column(name = "source_field_name")
    String sourceFieldName;

    @Column(name = "source_field_type")
    String sourceFieldType;

    @Column(name = "source_field_comment")
    String sourceFieldComment;

    @Column(name = "target_db_name")
    String targetDataBaseName;

    @Column(name = "target_tb_name")
    String targetTableName;

    @Column(name = "target_field_name")
    String targetFieldName;

    @Lob
    @Column(name = "sql_query")
    String sqlQuery;

    public enum SearchScope {
        TABLE, COLUMN, SQL, WORKFLOW
    }

    public enum SqlType {
        QUERY, INSERT, UPDATE, DELETE, CREATETABLE, DROP, TRUNCATETABLE, CREATETABLE_AS_SELECT, INSERT_OVERWRITE, PATH_WRITE
    }
}
