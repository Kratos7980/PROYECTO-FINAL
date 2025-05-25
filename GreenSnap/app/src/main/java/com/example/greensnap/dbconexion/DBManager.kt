package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.ContextCompat.getString
import com.example.greensnap.R

class DBManager(private val context:Context, dbName:String, factory:SQLiteDatabase.CursorFactory?, dbVersion:Int): SQLiteOpenHelper(context,dbName,factory,dbVersion) {

    companion object {
        val DB_NAME = "greensnap.db3"
        val DB_VERSION = 1
    }

    constructor(context:Context):this(context, DB_NAME, null, DB_VERSION){}
    //Creacion de las tablas
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE TIPO_CUIDADOS (ID_TIPO_CUIDADO INTEGER PRIMARY KEY NOT NULL, NOMBRE_TIPO_CUIDADO TEXT, IMAGEN TEXT)")
        db.execSQL("CREATE TABLE CUIDADOS (ID_CUIDADO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, COD_CUIDADO INTEGER, DESCRIPCION TEXT, ID_TIPO_CUIDADO INTEGER, FOREIGN KEY (ID_TIPO_CUIDADO) REFERENCES TIPO_CUIDADOS(ID_TIPO_CUIDADO))")
        db.execSQL("CREATE TABLE TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD INTEGER PRIMARY KEY NOT NULL, NOMBRE_TIPO_ENFERMEDAD TEXT,IMAGEN TEXT)")
        db.execSQL("CREATE TABLE ENFERMEDADES (ID_ENFERMEDAD INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, COD_ENFERMEDAD INTEGER, DESCRIPCION TEXT, SOLUCION TEXT, ID_TIPO_ENFERMEDAD INTEGER, FOREIGN KEY (ID_TIPO_ENFERMEDAD) REFERENCES TIPO_ENFERMEDADES(ID_TIPO_ENFERMEDAD))")
        db.execSQL("CREATE TABLE CATEGORIAS (ID_CATEGORIA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMBRE_CATEGORIA TEXT, IMAGEN TEXT)")
        db.execSQL("CREATE TABLE PLANTAS (ID_PLANTA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMBRE_PLANTA TEXT, IMAGEN TEXT, DESCRIPCION TEXT, ID_CATEGORIA INTEGER, COD_CUIDADO INTEGER, COD_ENFERMEDAD INTEGER, FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIAS(ID_CATEGORIA), FOREIGN KEY (COD_CUIDADO) REFERENCES CUIDADOS(COD_CUIDADO), FOREIGN KEY (COD_ENFERMEDAD) REFERENCES ENFERMEDADES(COD_ENFERMEDAD))")
        db.execSQL("CREATE TABLE JARDIN (ID_PLANTA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMBRE_PLANTA TEXT UNIQUE, NOMBRE_CIENTIFICO TEXT, IMAGEN BLOB, ID_CATEGORIA INTEGER)")
        db.execSQL("CREATE TABLE UBICACIONES (ID_UBI INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMBRE_PLANTA TEXT, ID_CATEGORIA INTEGER, IMAGEN BLOB, LONGITUD REAL, LATITUD REAL, FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIAS(ID_CATEGORIA))")

        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (1,'ILUMINACIÓN', 'iluminacion')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (2,'TEMPERATURA', 'temperatura')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (3,'SUSTRATO', 'sustrato')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (4,'RIEGO', 'riego')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (5,'ABONO', 'abono')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (6,'PODA', 'poda')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (7,'TRASPLANTE', 'trasplante')")
        db.execSQL("INSERT INTO TIPO_CUIDADOS (ID_TIPO_CUIDADO, NOMBRE_TIPO_CUIDADO, IMAGEN) VALUES (8,'PROPAGACIÓN', 'propagacion')")

        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Azalea de fuego prospera en sol parcial, donde recibe luz solar filtrada que imita su hábitat natural en el sotobosque. Es esencial para garantizar un crecimiento vigoroso y una floración abundante. Azalea de fuego puede tolerar pleno sol, pero prefiere la luz fresca y filtrada de la mañana o tarde, protegiéndose de los rayos duros del mediodía. Demasiado sol puede causar quemaduras en las hojas, mientras que la luz insuficiente puede resultar en menos flores. Al aire libre, azalea de fuego florece en pendientes orientadas al norte o debajo de árboles de alto dosel, logrando el equilibrio perfecto de luz y sombra. En interiores, azalea de fuego prefiere luz brillante e indirecta cerca de una ventana con protección de la luz solar directa y fuerte.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Azalea de fuego puede soportar variaciones significativas de temperatura, mostrando resistencia tanto a extremos de frío como de calor. Tolerando el frío hasta -20℃ (-4℉) y el calor hasta 35℃ (95℉), este amplio rango permite que azalea de fuego prospere en climas diversos, aunque se recomiendan medidas de protección durante climas severos. En condiciones de frío excesivo, azalea de fuego puede mostrar marchitez, hojas marrones o muerte de tallos; durante el calor extremo, los síntomas incluyen quemaduras en las hojas y marchitez. Para mitigar estos problemas, proporciona aislamiento o acolchado en períodos fríos, y asegúrate de un riego adecuado y sombra parcial durante el calor excesivo. La robusta tolerancia a la temperatura de azalea de fuego lo convierte en una opción versátil para muchos entornos de jardín.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Azalea de fuego prospera en suelos bien drenados para evitar el encharcamiento. Opta por suelo ácido con buena aireación, como una mezcla de tierra de jardín, corteza de pino y turba. Si la corteza de pino no está disponible, utiliza arena gruesa como alternativa. Para mejorar el crecimiento, incorpora un fertilizante de liberación lenta específicamente formulado para plantas acidófilas cada primavera. Asegurar un drenaje adecuado es vital; utiliza camas elevadas o contenedores con agujeros de drenaje para lograr esto.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Originario de hábitats húmedos y bien drenados, azalea de fuego prospera con humedad constante pero también está adaptado a períodos de sequedad. Esta especie muestra preferencia por condiciones de agua equilibradas, lo que indica la variabilidad de humedad de su entorno nativo. El riego debe hacerse una vez cada 1-2 semanas, asegurando que el suelo permanezca hidratado sin llegar a encharcarse. Comúnmente cultivado al aire libre debido a sus vibrantes flores y ciclos de crecimiento estacional, azalea de fuego requiere atención cuidadosa al riego durante su temporada de crecimiento activa para promover una floración saludable.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Para un crecimiento óptimo, azalea de fuego se beneficia de fertilizantes de nutrición equilibrada, con un enfoque en variedades que forman ácido para adaptarse a su preferencia por el suelo ácido. La primavera y principios del otoño son momentos clave para la alimentación, limitada a aplicaciones semestrales para prevenir la sobre-fertilización. Es crucial ser moderado; aproximadamente media taza al año para plantas jóvenes, aumentando con el tamaño. Fertilizar a azalea de fuego aumenta la vitalidad de la floración y la planta, pero las cantidades excesivas pueden provocar quemaduras en las raíces y deben evitarse. El acolchado con materia orgánica puede complementar la alimentación, facilitando la absorción de nutrientes y mejorando la estructura del suelo. Siempre riegue abundantemente a azalea de fuego después de la fertilización para ayudar en la dispersión de nutrientes.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'El azalea de fuego, conocido por sus vibrantes flores, se beneficia de la poda después de la floración a finales de primavera. Retira las flores marchitas y adelgaza las ramas muertas o superpobladas para mejorar la circulación del aire y la penetración de la luz. Cortar hasta brotes saludables promueve el crecimiento vigoroso. Evita la poda intensa, ya que puede reducir la floración del próximo año. Una poda adecuada ayuda a mantener la forma, fomenta la floración y previene enfermedades. Usa herramientas desinfectadas para minimizar el riesgo de infección.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'El trasplante de azalea de fuego da resultados espectaculares cuando se realiza a finales de otoño o principios de invierno (S1-S2), ya que así tiene tiempo suficiente para enraizar antes del crecimiento primaveral. Lo ideal es elegir un lugar a media sombra con un suelo bien drenado y ácido para que azalea de fuego prospere. La clave es manipular las raíces con cuidado durante el trasplante.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (10, 'Azalea de fuego se reproduce principalmente mediante esquejes y acodo durante otoño e invierno. La propagación puede ser moderadamente difícil, con un crecimiento exitoso señalado por el desarrollo de hojas nuevas. Asegure un enraizamiento óptimo proporcionando suficiente humedad y suelo bien drenado.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Azalea occidental prospera mejor en condiciones de sol parcial, recibiendo luz solar filtrada o moteada a lo largo del día. Esta configuración de iluminación estimula un crecimiento óptimo, un desarrollo saludable del follaje y una abundancia de flores. Aunque azalea occidental se adapta naturalmente bien a diferentes niveles de luz, la exposición prolongada a la luz solar directa puede estresar a la planta, lo que podría llevar a escaldado de hojas o flores descoloridas. Idealmente, azalea occidental debería ubicarse en entornos de jardín al aire libre donde la luz de la mañana sea abundante pero resguardada del intenso sol de la tarde. Sus hojas podrían mostrar ajustes, como un ligero enrollamiento o un aumento en la pigmentación, para adaptarse a intensidades de luz más altas.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Azalea occidental demuestra una notable resistencia a las variaciones de temperatura, soportando frío hasta -10℃ (14°F) y calor hasta 38℃ (100°F). Esta adaptabilidad permite que azalea occidental prospere en diversos entornos, aunque son esenciales medidas protectoras durante condiciones extremas. En frío extremo, la planta puede mostrar quemaduras en las hojas o retroceso, mientras que el calor excesivo puede causar marchitez o escaldado de hojas. Para mitigar el sobreenfriamiento, aplique mantillo y use cobertores contra heladas; durante olas de calor, asegúrese de un riego adecuado y proporcione sombra. Así, comprender y manejar estos límites de temperatura es crucial para mantener la salud y apariencia vibrante de azalea occidental.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Composición del suelo: Arcilla, Arena, Marga\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 4.5-6\n" +
                "\n" +
                "Azalea occidental prospera en un suelo bien aireado y de drenaje rápido para evitar el encharcamiento. Una mezcla adecuada incluye tierra de jardín, turba y corteza de pino en partes iguales. Si no hay turba disponible, use fibra de coco como sustituto. Asegure un buen drenaje añadiendo perlita o arena gruesa. Potencie el crecimiento con un fertilizante ácido de liberación lenta en primavera y otoño. Evite el exceso de riego y riegue solo cuando la capa superior del suelo esté seca.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Horario de riego: Cada 1-2 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Originaria de regiones húmedas y templadas, azalea occidental prospera en ambientes que imitan los niveles de humedad constantes de su hábitat natural. Esta especie prefiere un suelo consistentemente húmedo, evitando tanto condiciones encharcadas como sequedad prolongada. El riego debe ajustarse a una frecuencia de una vez cada 1-2 semanas, garantizando que el suelo permanezca hidratado pero no saturado. Comúnmente cultivada en exteriores debido a su atractivo ornamental, azalea occidental es particularmente sensible al exceso de riego durante su período de dormancia en los meses más fríos, requiriendo un monitoreo cuidadoso de la humedad del suelo.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Para azalea occidental, aplique un fertilizante equilibrado que forme ácido a principios de primavera para apoyar flores vibrantes y un crecimiento robusto. Las dosis deben seguir las instrucciones del producto, generalmente una aplicación ligera para prevenir quemaduras en las raíces. La alimentación mensual promueve la salud, disminuyendo a finales del verano para preparar a azalea occidental para la dormancia. Utilice formas solubles en agua para una distribución uniforme y absorción radicular. Los ajustes estacionales son clave; cese la fertilización en otoño para evitar estimular un crecimiento tierno vulnerable a las heladas. Siempre riegue azalea occidental antes y después de fertilizar para protegerlo contra el estrés químico.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Época de poda: Finales de primavera, Principios de verano\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Resistencia a plagas y enfermedades\n" +
                "\n" +
                "Azalea occidental es conocido por sus flores vibrantes y su follaje atractivo. Las técnicas clave de poda incluyen la eliminación de madera muerta o dañada, el adelgazamiento de ramas superpobladas y la conformación de la planta para un atractivo estético. El momento óptimo para podar azalea occidental es a finales de primavera, después de que haya terminado de florecer. Este momento ayuda a evitar el corte de los brotes florales del próximo año. La poda mejora la circulación del aire, fomenta un crecimiento saludable y realza la floración.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Época de trasplante: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "El momento óptimo para trasplantar azalea occidental es entre finales de otoño y principios de primavera (S2-S3), ya que es la época de letargo de la planta, lo que minimiza el choque del trasplante. Elija un lugar fresco y parcialmente sombreado para mantener los vibrantes colores de azalea occidental. Durante el trasplante, manipule el cepellón con delicadeza para que se establezca correctamente.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (20, 'Época de propagación: Mediados de otoño, Finales de otoño, Invierno\n" +
                "\n" +
                "Tipo de propagación: Corte, Injertos, Estratificación, Siembra, Cultivo de tejidos\n" +
                "\n" +
                "Azalea occidental se propaga mejor mediante esquejes y acodo durante las estaciones más frescas de otoño e invierno. Este método tiene una dificultad moderada, pero se puede observar una propagación exitosa cuando aparece un nuevo crecimiento. Asegure la humedad adecuada y el calor para obtener resultados ideales.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol\n" +
                "\n" +
                "Azalea Chino prefiere ambientes con sombra parcial y puede ser plantado en lugares parcialmente cubiertos de sombra. También pueden crecer en abundante luz solar, pero necesitan protección del sol intenso. Se recomienda sombrear el 75% de la luz solar en primavera para prevenir que las nuevas hojas se quemen por el sol.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Temperatura ideal: 10 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -15 - 38 ℃\n" +
                "\n" +
                "Azalea Chino presenta una notable resistencia a las fluctuaciones de temperatura, tolerando climas fríos hasta -15℃ (5℉) y calor de hasta 38℃ (100℉). Esta amplia tolerancia a la temperatura hace que azalea Chino sea adaptable a varios entornos, aunque pueden ser necesarias medidas de protección durante el clima extremo. En condiciones frías, pueden aparecer síntomas como daño foliar o decoloración, mientras que el sobrecalentamiento puede causar marchitez o quemaduras en las hojas. Para mitigar estos efectos, utiliza telas de protección contra heladas o mueve las plantas al interior durante las heladas, y asegúrate de regar adecuadamente y proporcionar sombra en calor extremo. Comprender estos parámetros ayuda a los jardineros a proporcionar un cuidado óptimo para un crecimiento robusto.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Composición del suelo: Arena, Marga, Calcáreo, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 4.5-5.5\n" +
                "\n" +
                "En su hábitat natural, azalea Chino prospera en suelos ácidos a ligeramente ácidos con un pH de 5.0-6.5. Este rango de pH es crucial para la absorción de nutrientes y la salud general de la planta. Azalea Chino prefiere un suelo bien aireado y ricamente orgánico con componentes como arena, marga y materia vegetal descompuesta. Para una mezcla óptima, combina 50% de materia orgánica (compost o turba), 30% de arena gruesa y 20% de marga. Asegúrate de un buen drenaje utilizando camas elevadas o agregando perlita. Enmienda regularmente el suelo con fertilizantes específicos para azaleas y evita el exceso de riego para prevenir la pudrición de las raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Horario de riego: Cada 1-2 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Prosperando en sus entornos húmedos nativos, azalea Chino se ha adaptado para requerir niveles moderados de humedad. Es resistente a breves períodos de sequía pero florece cuando el suelo permanece uniformemente húmedo sin encharcamiento. Para una salud óptima, regar una vez cada 1-2 semanas es suficiente, alineándose con su preferencia por una hidratación constante pero no excesiva. Comúnmente cultivado al aire libre debido a sus vibrantes flores, azalea Chino se beneficia del acolchado que ayuda a retener la humedad del suelo y simula la hojarasca de su entorno natural, reduciendo así la necesidad de riego frecuente.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Ofrezca a azalea Chino fertilizantes de nutrición equilibrada para un crecimiento robusto y flores vibrantes. Aplique una fórmula de liberación lenta durante principios de primavera y finales de verano, a media potencia, para satisfacer los aumentos de crecimiento estacional. La cantidad adecuada depende del tamaño de la planta, con las instrucciones en el envase como la mejor guía. La sobre-fertilización puede dañar a azalea Chino, así que adhiérase estrictamente a las tasas recomendadas. Ajuste las alimentaciones si aparecen hojas amarillentas o flores apagadas, señales de que azalea Chino puede necesitar un impulso nutricional o una reducción.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Época de poda: Finales de primavera, Principios de verano\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Resistencia a plagas y enfermedades\n" +
                "\n" +
                "Conocido por sus vibrantes flores y su exuberante follaje, azalea Chino se beneficia de la poda para mantener su forma y fomentar un crecimiento saludable. El mejor momento para podar es a finales de la primavera, después de la floración. Remueve la madera muerta, enferma o dañada, y aclare las ramas apiñadas para mejorar la circulación del aire. Evita la poda excesiva, ya que azalea Chino florece en madera vieja. Un recorte ligero y regular promueve una abundante floración y previene un crecimiento desgarbado, asegurando una planta robusta y atractiva.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Época de trasplante: Mediados de primavera, Finales de primavera, Principios de verano\n" +
                "\n" +
                "El momento ideal para trasplantar azalea Chino es durante las estaciones más frías S2-S4, lo que proporciona a la planta unas condiciones óptimas para el establecimiento de las raíces antes del vigoroso crecimiento primaveral. Azalea Chino se desarrolla mejor en lugares con sombra parcial. Recuerde que un suelo bien drenado es clave para el éxito del proceso de trasplante.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (30, 'Época de propagación: Mediados de otoño, Finales de otoño, Invierno\n" +
                "\n" +
                "Tipo de propagación: Corte, Estratificación, Siembra\n" +
                "\n" +
                "Azalea Chino es popular por sus brillantes y llamativas flores y se puede propagar eficazmente mediante esquejes. Para asegurar un enraizamiento exitoso, seleccione material saludable semi-leñoso del crecimiento del año actual. Los esquejes deben tomarse con una herramienta limpia y afilada para minimizar el daño y el riesgo de enfermedades. Sumergir el extremo cortado en hormona de enraizamiento puede mejorar el desarrollo de raíces. Plante los esquejes en un medio bien drenante, manteniendo una humedad y calor consistentes para fomentar el crecimiento de raíces sin provocar pudrición. Un invernadero o un ambiente interior puede ofrecer las condiciones ideales para que los esquejes prosperen.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol\n" +
                "\n" +
                "Azalea temprana prospera mejor a pleno sol, donde recibe una intensidad de luz moderada, evitando los rayos del mediodía. La exposición recomendada es de sol filtrado o sombra ligera de la tarde, lo que respalda un crecimiento robusto y flores vibrantes. Aunque azalea temprana tolera pleno sol, la luz excesiva puede provocar quemaduras en las hojas, flores descoloridas y estrés, especialmente en climas más cálidos. La planta muestra adaptabilidad a través de su follaje, donde las hojas pueden volverse más compactas y gruesas en intensidades lumínicas más altas para mitigar el daño. Idealmente, azalea temprana debe plantarse en áreas exteriores bien iluminadas que reciban luz solar matutina con protección del fuerte sol de la tarde, promoviendo su salud y longevidad.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Temperatura ideal: 5 - 32 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -25 - 35 ℃\n" +
                "\n" +
                "Azalea temprana muestra una gran resistencia a las fluctuaciones de temperatura. Tolera extremos fríos de hasta -25℃ (-13℉) y puede soportar calor de hasta 35℃ (95℉). Este amplio rango requiere mínimas medidas de protección durante los típicos frentes fríos o olas de calor, lo que lo hace adecuado para diversos climas. Sin embargo, en condiciones extremadamente frías, la planta puede mostrar marchitamiento o follaje marrón, mientras que el calor excesivo puede causar quemaduras en las hojas o deshidratación. Las contramedidas incluyen acolchado y envoltura para la protección contra el frío, así como riego regular y sombra para el alivio del calor. La combinación de estas prácticas asegurará la salud y vitalidad de azalea temprana a través de extremos de temperatura variables.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Composición del suelo: Arena, Marga, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 4.5-6\n" +
                "\n" +
                "En su hábitat natural, azalea temprana prospera en suelos ácidos y bien drenados con un rango de pH de 4.5-6.0. Los componentes clave del suelo incluyen arena, limo y arcilla, proporcionando un ambiente rico en nutrientes. La mezcla de suelo ideal para azalea temprana debería consistir en partes iguales de tierra de jardín, musgo de turba y arena gruesa. Un buen drenaje es crucial para prevenir el encharcamiento, lo que se puede lograr añadiendo una capa de grava en el fondo de la maceta. Usar un fertilizante a base de ácido regularmente cada 4-6 semanas durante la temporada de crecimiento mejora el crecimiento. Para obtener mejores resultados, asegúrese de que haya una humedad constante sin exceso de riego.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Horario de riego: Cada 1-2 semanas\n" +
                "\n" +
                "Adaptado a las condiciones húmedas de sus hábitats nativos boscosos, azalea temprana prospera con humedad constante pero también es capaz de soportar breves períodos de sequedad. Sus hábitos de riego se describen mejor como moderadamente tolerante a la sequía, requiriendo un equilibrio entre hidratación y drenaje. Para una salud óptima, azalea temprana debe regarse una vez cada 1-2 semanas. Como planta de exterior que a menudo adorna jardines templados, el potencial de floración de azalea temprana se maximiza cuando la humedad del suelo se gestiona cuidadosamente para imitar los ambientes frescos y húmedos que prefiere naturalmente.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Para azalea temprana, aplique un fertilizante equilibrado, como una mezcla 10-10-10, a principios de primavera para promover un crecimiento vigoroso y la floración. Reaplique un fertilizante alto en nitrógeno antes de un nuevo crecimiento a fines de la primavera para mejorar aún más la salud del follaje. Fertilice cada 2-4 semanas durante la temporada de crecimiento, usando aproximadamente 1 cucharada por pie de altura de la planta, evitando la sobre-fertilización que puede dañar las raíces. Reduzca la frecuencia en otoño y evite fertilizar en invierno, ya que azalea temprana entra en letargo. Siempre riegue abundantemente azalea temprana después de aplicar fertilizantes para evitar quemaduras en las raíces y garantizar la absorción de nutrientes.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Azalea temprana es un arbusto caducifolio conocido por sus fragantes flores rosas y su vibrante follaje otoñal. La poda óptima debe realizarse a principios de la primavera antes de que comience el nuevo crecimiento. Las técnicas incluyen la eliminación de ramas muertas o débiles para estimular un desarrollo saludable y dar forma a la planta mediante el recorte de tallos crecidos en exceso. Aclare las áreas densas para mejorar la circulación del aire y la penetración de la luz. La poda regular mejora la floración y mantiene el tamaño y la forma de la planta, asegurando una exhibición robusta y hermosa.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Época de trasplante: Principios de primavera\n" +
                "\n" +
                "Azalea temprana prospera cuando se trasplanta en la refrescante aura del otoño (S1), ya que el frío en el aire estimula el crecimiento de las raíces. Prefiera un lugar que ofrezca sombra parcial y un suelo ácido bien drenado. Cuidado con el calor del verano durante el trasplante, apreciará su delicadeza.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (40, 'Época de propagación: Mediados de otoño, Finales de otoño, Invierno\n" +
                "\n" +
                "Tipo de propagación: Corte, Estratificación, Siembra, Injertos, Cultivo de tejidos\n" +
                "\n" +
                "Azalea temprana puede propagarse efectivamente mediante el método de esquejes. Para un enraizamiento exitoso, los esquejes de madera semidura son óptimos. Estos deben tomarse de plantas progenitoras sanas, asegurando que cada esqueje tenga varios nodos foliares. El uso de hormonas enraizantes puede aumentar las posibilidades de éxito. Después de sumergir el extremo cortado en la hormona, plante los esquejes en un medio de cultivo bien drenante. Proporcionar un ambiente húmedo y una humedad constante sin encharcamiento es crucial para el desarrollo de raíces. La luz indirecta y el calor apoyarán el crecimiento mientras se evita el estrés de la luz solar directa.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Azalea alpina prospera mejor en condiciones de pleno sol donde pueda recibir luz solar directa e ininterrumpida durante al menos seis horas al día. Estas condiciones ayudan a maximizar su crecimiento y salud, lo que resulta en una floración y follaje robustos. Aunque azalea alpina puede tolerar sol parcial, es decir, puede seguir creciendo con menos luz solar directa o sombra dappled, períodos prolongados lejos de la luz óptima pueden resultar en una reducción de flores y una forma más alargada. El plantado al aire libre debe asegurar una ubicación con una amplia exposición al sol, evitando áreas sombreadas que puedan obstaculizar el desarrollo de azalea alpina. Azalea alpina no posee adaptaciones únicas conocidas para ajustarse a niveles de luz variables, por lo que es crucial adherirse a sus condiciones de luz ideales para mantener su vigor.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Temperatura ideal: 0 - 25 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -40 - 30 ℃\n" +
                "\n" +
                "Azalea alpina es notablemente tolerante al frío, soportando temperaturas tan bajas como -40°C (-40°F), pero también puede resistir el calor de hasta 30°C (86°F). Esta resiliencia lo hace adecuado para climas diversos, aunque se requiere cuidado durante extremos de temperatura. En condiciones de congelación, pueden ocurrir quemaduras en las hojas y daños en los tallos; en calor excesivo, el marchitamiento y la decoloración de las hojas son comunes. Medidas de protección como el acolchado en invierno y proporcionar sombra durante las olas de calor son cruciales. Tal tolerancia significa que azalea alpina prospera en varios entornos, pero es esencial monitorear los síntomas de estrés para garantizar su salud y longevidad.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'pH del suelo: 4.5-6\n" +
                "\n" +
                "El azalea alpina prefiere suelos ácidos o estériles con buen drenaje. Algunas variedades, como Mountain azalea alpina, son sensibles a arcillas pesadas, aunque Bog azalea alpina solo crecerá en sitios húmedos o mal drenados. Mountain azalea alpina a menudo se desempeñará mejor en una cama elevada que permita suelos adicionales para proporcionar un mejor drenaje. La mayoría de las variedades se desarrollan mejor en suelos ricos en nutrientes y materia orgánica.\n" +
                "La acidez del suelo es muy importante para el azalea alpina, especialmente cuando la planta es joven, con un pH ideal del suelo entre 4.5 y 7.5. Las pruebas de pH mostrarán la acidez del suelo; éstas se pueden comprar en tiendas especializadas, o un kit casero se puede hacer usando repollo morado. Si el suelo muestra insuficiente acidez, se debe agregar una capa de virutas de madera o mantillo de corteza de hoja perenne. En primavera, se debe utilizar alimento para plantas formulado para arbustos que aman la acidez, como el alimento para rododendros. Sin embargo, se debe tener cuidado al usar alimento para plantas cuando las plantas son jóvenes, para evitar quemar las raíces de la planta.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Horario de riego: Cada 3 semanas\n" +
                "\n" +
                "Azalea alpina prospera en ambientes que imitan sus hábitats nativos húmedos y periódicamente húmedos. Exhibe una preferencia por la humedad constante pero también es capaz de tolerar períodos cortos de sequía. El riego debe realizarse una vez cada tres semanas, asegurando que el suelo permanezca húmedo pero no encharcado. Como planta perenne, azalea alpina mantiene la hidratación en sus hojas durante todo el año, lo que la hace adecuada para el cultivo tanto en interiores como en exteriores, dependiendo de las zonas climáticas.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Para un crecimiento saludable y flores vibrantes, azalea alpina se beneficia de fertilizantes equilibrados (por ej., 10-10-10) aplicados a principios de la primavera y fertilizantes con alto contenido de nitrógeno más tarde en la temporada de crecimiento. Fertiliza con moderación, ya que la sobre-fertilización puede dañar a azalea alpina. Utiliza el fertilizante a la mitad de la fuerza recomendada, cada 4-6 semanas durante el crecimiento activo. No se necesita fertilización durante la dormancia. Asegúrate de que el suelo esté húmedo antes de aplicar para prevenir quemaduras en las raíces. Reduce gradualmente la alimentación a medida que se acerca el otoño. Tanto los jardineros novatos como los experimentados deben ajustar los horarios y cantidades según la respuesta de azalea alpina.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Azalea alpina, conocido por sus flores espectaculares y su crecimiento compacto, prospera con una poda estratégica. Para dar forma a azalea alpina y fomentar un crecimiento más arbustivo, poda a principios de la primavera antes de que comience el nuevo crecimiento. Retira ramas muertas o dañadas para mantener la salud de la planta. Un ligero recorte después del período de floración ayuda a promover futuras flores. Evita la poda excesiva ya que puede obstaculizar la floración. La poda regular mejora la circulación del aire y previene enfermedades fúngicas. Consulta guías hortícolas de renombre para asegurar las mejores prácticas.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Época de trasplante: Primavera\n" +
                "\n" +
                "Traslade azalea alpina desde el despertar de la primavera hasta su pico, asegurando el éxito con condiciones más frescas y húmedas. Colóquelas en un lugar parcialmente sombreado con suelo bien drenado. Consejo amistoso: ¡el cuidado suave después del traslado fomenta un crecimiento próspero!', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (50, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: División, Estratificación\n" +
                "\n" +
                "Miembro de la familia Ericaceae, azalea alpina se propaga con éxito a través de la división, un proceso idealmente realizado durante su período de dormancia. Los jardineros deben separar cuidadosamente el cepellón para crear nuevas plantas, asegurando que cada división tenga raíces y brotes suficientes para un crecimiento óptimo. Es importante mantener la humedad del suelo y proporcionar sombra adecuada después del transplante, ya que azalea alpina prospera en suelos ácidos y bien drenados, característicos de su hábitat natural en el bosque. Con atención a estos detalles, la propagación de azalea alpina puede producir resultados florecientes.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol\n" +
                "\n" +
                "Rododendro prospera mejor en semisombra, necesitando luz indirecta o moteada para optimizar su crecimiento, salud y capacidad de floración. Aunque rododendro puede tolerar sol pleno, la exposición excesiva puede causar estrés, impactando el color de las hojas y potencialmente reduciendo la vitalidad y calidad de las flores. En su hábitat natural, rododendro está acostumbrado al dosel protector que filtra la luz solar. Al plantar al aire libre, se recomienda ubicar rododendro en lugares donde reciba sol por la mañana y sombra por la tarde, asegurando que la planta esté protegida durante el calor máximo del día.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Temperatura ideal: 15 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -10 - 38 ℃\n" +
                "\n" +
                "Rododendro presenta una resistencia moderada a las fluctuaciones de temperatura, tolerando temperaturas tan bajas como -10°C (14°F) y tan altas como 38°C (100°F). A pesar de este rango, requiere cuidados específicos para prosperar. Durante el frío extremo, pueden presentarse síntomas como la caída de hojas y el marchitamiento, lo que necesita protección contra las heladas, como acolchado o cobertura. En el calor intenso, se puede observar quemado de hojas y un crecimiento reducido, lo que exige sombra y un riego adecuado. Reconocer estas señales y aplicar las medidas de protección adecuadas garantiza la salud óptima de rododendro. Su tolerancia a la temperatura lo hace adecuado para regiones templadas, pero requiere un cuidado atento en climas con extremos severos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Composición del suelo: Arena, Marga, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 4.5-5.5\n" +
                "\n" +
                "Rododendro prospera en suelo bien aireado con buen drenaje. Prepara una mezcla con tierra de jardín, musgo de turba y perlita o arena en partes iguales para mejorar la aireación y prevenir el encharcamiento. Si no hay musgo de turba disponible, se puede usar compost. Utiliza un fertilizante balanceado de liberación lenta a principios de primavera para estimular el crecimiento.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Horario de riego: Cada 1-2 semanas\n" +
                "\n" +
                "Rododendro prospera en sus entornos nativos húmedos, mostrando una preferencia por la humedad consistente sin encharcamiento. Sus hábitos de riego incluyen una tolerancia moderada a la sequía y una inclinación hacia niveles más altos de humedad. Para una salud óptima, rododendro debe ser regado una vez cada 1-2 semanas, asegurando que el suelo permanezca uniformemente húmedo pero no saturado. Cultivada más a menudo al aire libre que en interiores, rododendro es una planta perenne que requiere una atención cuidadosa al riego durante sus ciclos de crecimiento activo para mantener un follaje exuberante y flores vibrantes.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Para rododendro, fertilice con abonos de nutrición equilibrada en primavera temprana y finales de verano para favorecer un crecimiento vigoroso y flores vibrantes. Aplique los fertilizantes a la tasa recomendada por el fabricante, evitando el uso excesivo que puede dañar las raíces y el follaje. Las etapas de crecimiento estacional requieren ajustar la frecuencia; menos en los meses invernales de dormancia, más durante el crecimiento activo. Siempre riegue abundantemente rododendro después de la fertilización para favorecer la absorción y prevenir quemaduras en las raíces. Tanto jardineros novatos como expertos deben usar guantes como precaución y asegurar una distribución uniforme del fertilizante alrededor de la base, no directamente sobre tallos o hojas.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Época de poda: Finales de primavera, Principios de verano\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Resistencia a plagas y enfermedades\n" +
                "\n" +
                "Caracterizado por su follaje perenne y flores vibrantes, rododendro se beneficia de una poda anual a finales de primavera, después de la floración. Primero, elimina ramas muertas, enfermas o dañadas. Despeja el crecimiento denso para mejorar la circulación del aire y la penetración de la luz, recortando hasta un brote o unión de ramas saludable. Esto fomenta un crecimiento más saludable y una floración más prolífica. Podar justo después de que las flores se desvanecen asegura una recuperación óptima, alineándose con el ciclo de crecimiento natural de la planta.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Época de trasplante: Primavera\n" +
                "\n" +
                "Para obtener los mejores resultados, trasplante rododendro durante S1-S3, su estación de letargo, para reducir el estrés posterior al trasplante. Elija un lugar sombreado y bien drenado que refleje su hábitat natural en el bosque. Recuerde que el éxito del trasplante depende de abundante agua y paciencia. Una pista: ¡la división de las raíces facilita la multiplicación!', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (60, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: Corte, Estratificación, Injertos, Siembra, Cultivo de tejidos\n" +
                "\n" +
                "Rododendro es una especie ampliamente cultivada conocida por sus flores vibrantes y hojas perennes anchas. Para propagar rododendro, los esquejes de madera blanda son típicamente el método más exitoso. Idealmente, los esquejes deben tomarse de plantas sanas y libres de enfermedades. Utilice una cuchilla afilada y esterilizada para minimizar daños e infecciones potenciales. Los esquejes deben incluir varios nodos de hojas y sumergirse en hormonas de enraizamiento antes de plantarse en una mezcla de suelo bien drenante. Mantenga niveles de humedad altos y humedad constante sin regar en exceso para fomentar el desarrollo de raíces.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Begonia rex prospera en luz brillante e indirecta, preferiblemente en condiciones de sol parcial. La exposición prolongada a la luz solar directa puede causar quemaduras en las hojas, mientras que la sombra completa podría resultar en un crecimiento largo y una reducción de la vitalidad. Con su tolerancia a niveles bajos de luz, begonia rex puede adaptarse bien a entornos sombreados pero puede mostrar una coloración menos intensa en el follaje. La ubicación en interiores debe aprovechar la luz natural sin sol directo, como cerca de ventanas orientadas al este, mientras que la ubicación al aire libre se beneficia de la luz dappled bajo plantas más grandes o estructuras. Los rasgos adaptativos a la luz de la planta, incluidos los ajustes de las hojas, ayudan a mantener un crecimiento y salud óptimos.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Begonia rex exhibe una resistencia moderada a las fluctuaciones de temperatura. Puede soportar temperaturas frías hasta 5℃ (41℉) y calor de hasta 43℃ (109℉). A pesar de este rango, begonia rex es sensible a los extremos; la exposición prolongada más allá de estos límites puede causar la caída de hojas en frío o escaldado de hojas en calor. Para cuidar begonia rex, es crucial protegerlo de las heladas con cubiertas o llevarlo adentro durante las olas de frío, y proporcionar sombra o aumentar la humedad durante las olas de calor. Esta adaptabilidad permite que begonia rex prospere en varios entornos, pero requiere un cuidado atento para mitigar los síntomas de estrés en condiciones extremas.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Composición del suelo: Arena, Marga, Calcáreo\n" +
                "\n" +
                "pH del suelo: 5.5-6.5\n" +
                "\n" +
                "Begonia rex crece bien en una mezcla bien drenada de suelo arenoso, arcilla y limo, con un rango de pH adecuado de 5.5-6.2. Sin embargo, begonia rex crecerá mejor en entornos sin suelo que en suelo convencional. Los jardineros pueden comprar medios de cultivo sin suelo listos para usar, o mezclar 2/3 turba o musgo de turba con 1/3 de otros medios de cultivo sin suelo como vermiculita, corteza y perlita para proporcionar un medio ligeramente ácido y bien drenado para la planta.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad alta\n" +
                "\n" +
                "Begonia rex prospera en un ambiente húmedo, imitando su hábitat nativo de la selva tropical. Tiene un requerimiento de agua equilibrado, ni muy seco ni muy húmedo. La planta prefiere ser regada cada semana para mantener niveles óptimos de humedad. Siendo una especie perenne, begonia rex puede retener agua bien durante todo el año, lo que la hace ideal para el cultivo en interiores donde se pueda controlar la humedad.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Para begonia rex, utiliza fertilizantes ricos en nitrógeno para promover un follaje exuberante. La alimentación mensual durante las temporadas de crecimiento (primavera-verano) con una solución equilibrada y soluble en agua a mitad de fuerza es óptima. Redúcela a cada dos meses en otoño-invierno. La sobre-fertilización puede dañar las raíces de begonia rex; sigue siempre las cantidades recomendadas. Los fertilizantes líquidos son preferibles para begonia rex para prevenir quemaduras por fertilizantes y asegurar una distribución uniforme. Los ajustes estacionales en la alimentación apoyan el ciclo de crecimiento de begonia rex.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Begonia rex, conocido por su folaje vibrante, prospera con una poda cuidadosa. Los momentos óptimos para podar son de principio a finales de primavera, alineándose con su ciclo de crecimiento. Retire las hojas muertas o dañadas y recorte los tallos alargados para fomentar un crecimiento frondoso y mejorar la circulación del aire. Evite recortes excesivos para prevenir el estrés. La poda constante no solo mantiene el atractivo estético de la planta, sino que también promueve un crecimiento más saludable. Referirse a las pautas hortícolas asegura que estas técnicas sean efectivas y beneficiosas.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Época de trasplante: Finales de primavera, Principios de verano\n" +
                "\n" +
                "Para obtener el mejor crecimiento posible, trasplante begonia rex idealmente entre finales de primavera y principios de verano, cuando las temperaturas son cálidas de forma constante. Elija un lugar con suelo que drene bien y luz solar brillante e indirecta. Recuerde manipular con cuidado sus delicadas raíces durante el trasplante para evitar daños.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (70, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: Corte\n" +
                "\n" +
                "Begonia rex es conocida por su llamativo follaje, lo que la convierte en una preciada planta ornamental de interior. La propagación efectiva implica el uso de esquejes de hojas saludables, idealmente con una vena prominente. Corta la hoja en cuñas, asegurándote de que cada sección tenga una porción de vena. Deja que los esquejes se sequen ligeramente, luego introdúcelos en una mezcla de turba y perlita humedecida, con la vena hacia abajo. Mantén una humedad y temperatura consistentes para fomentar el enraizamiento. Una vez que aparezcan raíces y nuevos brotes, trasplántalos suavemente a macetas individuales llenas de suelo que drena bien.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Begonia alas de ángel prospera en ambientes con sol parcial, que proporcionan el equilibrio óptimo de luz para el crecimiento y la floración. Aunque tolera la sombra total, tales condiciones de poca luz pueden resultar en una floración reducida y una posible etiolación. La exposición constante a la luz solar tamizada fomenta un follaje vibrante y flores robustas, mostrando su adaptabilidad. Cuando se cultiva en interiores, las ventanas orientadas al este u oeste ofrecen el entorno de luz ideal. En exteriores, begonia alas de ángel prospera bajo la protección de plantas más altas o estructuras que ofrecen alivio del intenso sol del mediodía. Una posición adecuada de la luz mejora la salud de begonia alas de ángel, permitiéndole adaptarse ajustando la orientación de las hojas para optimizar la absorción de luz.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Begonia alas de ángel demuestra una resistencia moderada a las variaciones de temperatura, tolerando el frío hasta aproximadamente 5 ℃ (41 ℉) y el calor hasta 43 ℃ (109 ℉). Aunque puede soportar estos rangos, la exposición prolongada a los extremos puede estresar la planta. Los síntomas de estrés por frío incluyen marchitamiento, caída de hojas y oscurecimiento del follaje, mientras que el sobrecalentamiento puede causar quemaduras en las hojas y marchitez. Para mitigar estos efectos, proteja begonia alas de ángel de las corrientes de aire frío y proporcione sombra durante el calor excesivo. Esta tolerancia a la temperatura sugiere que begonia alas de ángel es adecuada para entornos con fluctuaciones suaves, pero requiere medidas de protección durante el clima severo.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Composición del suelo: Arena, Marga, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 5.5-6.5\n" +
                "\n" +
                "Begonia alas de ángel prospera en un suelo bien aireado y con buen drenaje. Para una mezcla adecuada, combine partes iguales de tierra para macetas, turba y perlita. Esto asegura un drenaje adecuado para evitar el encharcamiento. Si no hay turba disponible, opte por fibra de coco. Para estimular el crecimiento, incorpore un fertilizante equilibrado de liberación lenta al momento de la siembra. La adición periódica de fertilizante líquido también puede beneficiar a begonia alas de ángel, pero evite la sobre-fertilización para prevenir daños en las raíces. Mantenga una humedad uniforme sin que el suelo se vuelva empapado.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad alta\n" +
                "\n" +
                "Apreciando los entornos húmedos, begonia alas de ángel muestra una preferencia por la humedad constante similar a sus orígenes tropicales. Sus hábitos de riego revelan una inclinación hacia una hidratación regular sin encharcamiento, manteniendo un delicado equilibrio. Regar cada semana es ideal para imitar el ritmo natural de lluvias al que está acostumbrado. Como planta de interior, begonia alas de ángel se beneficia de niveles más altos de humedad que apoyan su florecimiento vibrante, siendo esencial monitorear la humedad ambiental especialmente durante la temporada de crecimiento activa.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Para un crecimiento óptimo, fertilice begonia alas de ángel quincenalmente en las temporadas de crecimiento con fertilizantes altos en fósforo para impulsar la floración. Use la mitad de la dosis recomendada para evitar quemaduras de raíz y exceso de nutrientes. Durante los meses invernales de reposo, reduzca a mensual. Este régimen mejora la vitalidad, impulsa las flores y fortalece a begonia alas de ángel. Siempre riegue begonia alas de ángel antes de fertilizar para proteger las raíces.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Época de poda: Durante todo el año\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Begonia alas de ángel cuenta con un follaje vibrante y exuberante y flores llamativas. La poda óptima ocurre a principios de primavera hasta finales de invierno, coincidiendo con los períodos de inactividad. Recorte los tallos alargados para fomentar un crecimiento más arbustivo y elimine ramas muertas o enfermas para promover la salud general. Recortar regularmente mejorará la circulación de aire, reducirá el riesgo de enfermedades y fomentará una floración más vigorosa. Siempre utilice herramientas limpias y afiladas para evitar daños. Para begonia alas de ángel, una poda constante da como resultado una planta más saludable y estéticamente agradable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Época de trasplante: Finales de primavera, Principios de verano, Mediados de verano\n" +
                "\n" +
                "El momento ideal para trasplantar begonia alas de ángel es durante la primavera hasta principios del verano (S3-S5), cuando las plantas están en crecimiento vigoroso. Begonia alas de ángel prefiere un suelo bien drenado pero húmedo y un lugar con sombra parcial. Asegúrese de manipularla con cuidado para no dañar sus delicadas raíces.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (80, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División, Siembra\n" +
                "\n" +
                "Begonia alas de ángel, una planta de flores vibrantes, se propaga fácilmente mediante esquejes. Para un crecimiento exitoso, use una cuchilla afilada y esterilizada para tomar esquejes de tallo que incluyan un nudo de hoja, ya que esta área es rica en potencial de crecimiento. Plante los esquejes en una mezcla de suelo bien drenado, asegurando alta humedad y constante hidratación sin encharcamiento. La hormona enraizante puede ayudar en el desarrollo de un sistema de raíces fuerte. Después de enraizar, aclimate gradualmente a begonia alas de ángel a condiciones menos húmedas para fortalecer su adaptabilidad.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Las condiciones de luz óptimas para begonia tuberosa consisten en sol parcial, donde prospera con luz brillante pero indirecta durante todo el día. Evitar los rayos del sol directo e intenso del mediodía ayuda a prevenir quemaduras en las hojas y promueve un crecimiento exuberante y una floración vibrante. Aunque tolera la sombra completa, begonia tuberosa puede mostrar una menor floración y vigor en dichos ambientes. Adaptativamente, begonia tuberosa puede ajustar la orientación de las hojas para maximizar la absorción de la luz sin sufrir daños. En interiores, begonia tuberosa prefiere ventanas orientadas al este o al oeste. En exteriores, florece bajo luz dappled proporcionada por el dosel abierto de los árboles o patios sombreados. Desviaciones de estas condiciones pueden obstaculizar el rendimiento general y la presentación de begonia tuberosa.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Begonia tuberosa es relativamente sensible a las fluctuaciones de temperatura. Puede tolerar temperaturas frías hasta 0°C (32°F) y calor hasta 43°C (109°F). A pesar de este rango, las temperaturas extremas pueden estresar a begonia tuberosa. En condiciones frías, puede presentar hojas rizadas o marrones, mientras que un calor excesivo puede causar marchitez y quemaduras. Para proteger a begonia tuberosa, asegúrese de proporcionar un aislamiento adecuado durante el clima frío y una sombra o humedad adecuada durante las olas de calor. Esta tolerancia a la temperatura afecta su cuidado, haciéndola apta para climas templados, pero requiere medidas de protección en entornos propensos a extremos de temperatura.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-6.5\n" +
                "\n" +
                "Para un crecimiento óptimo, begonia tuberosa requiere un suelo bien drenado para evitar el encharcamiento, que puede dañar sus raíces. Utilice una mezcla para macetas combinada con partes iguales de perlita o arena para mejorar la aireación. Asegúrese de que el suelo se mantenga uniformemente húmedo pero no empapado. Agregar un fertilizante de liberación lenta durante la siembra puede mejorar el crecimiento. Revise regularmente la humedad del suelo para mantener un ambiente saludable para begonia tuberosa.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad alta\n" +
                "\n" +
                "Originario de ambientes húmedos y sombreados, begonia tuberosa prospera con una humedad constante, reflejando las condiciones de su hábitat natural. Prefiere la alta humedad y un suelo uniformemente húmedo, pero es resistente a breves períodos de sequía. El riego regular cada semana mantiene su crecimiento exuberante. Como una planta perenne herbácea comúnmente cultivada en macetas, begonia tuberosa se beneficia significativamente de una mezcla para macetas bien drenante que imita los suelos arcillosos de los bosques nativos.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Para potenciar la producción de flores en begonia tuberosa, utilice fertilizantes ricos en fósforo cada dos semanas durante el crecimiento activo, con una dilución al 50%. La fertilización promueve un crecimiento vigoroso y flores radiantes. En la temporada de dormancia, reduzca la frecuencia de alimentación a mensual. Tenga cuidado de evitar la sobre-fertilización, que puede dañar el delicado sistema radicular de begonia tuberosa. Un enfoque equilibrado es clave para las variaciones estacionales, garantizando que begonia tuberosa reciba los nutrientes adecuados para florecer.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Begonia tuberosa presenta flores vibrantes y llamativas, además de un follaje exuberante. Las técnicas clave de poda incluyen la eliminación de tallos muertos o dañados para mantener la salud de la planta y fomentar un crecimiento más frondoso. La poda se realiza mejor a principios o finales de la primavera, cuando la planta comienza a crecer activamente. Asegúrese de pellizcar el nuevo crecimiento para fomentar una apariencia más completa. La poda regular ayuda a mejorar la circulación del aire, promueve la floración y mantiene una forma atractiva. Siempre use herramientas esterilizadas para prevenir enfermedades.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Época de trasplante: Finales de primavera, Principios de verano\n" +
                "\n" +
                "El período óptimo para trasplantar begonia tuberosa es desde finales de primavera hasta principios de verano (S3-S4), ya que el clima templado favorece el establecimiento de las raíces. Elija un lugar con buen drenaje y luz solar indirecta. Para garantizar el éxito del trasplante, considere la posibilidad de regar regularmente sin saturar en exceso.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (90, 'Época de propagación: Principios de primavera\n" +
                "\n" +
                "Tipo de propagación: Uso de tubérculos\n" +
                "\n" +
                "Begonia tuberosa es apreciado por sus flores vibrantes y su capacidad para prosperar en una variedad de ambientes, con el cuidado adecuado. Su propagación se logra de forma distintiva mediante la división de tubérculos que se separan suavemente de la planta madre. Asegúrese de que cada sección tenga al menos un brote. Las áreas de separación deben tratarse con fungicida y dejarse secar antes de plantar para prevenir enfermedades. La manipulación precisa es crucial ya que estos segmentos son responsables del desarrollo de un nuevo crecimiento. Los recortes también pueden enraizar con éxito bajo condiciones favorables, asegurando el éxito de la propagación.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Begonia flaviflora prospera mejor bajo condiciones de sol parcial, que incluyen exposición a luz brillante e indirecta durante varias horas. Evite la luz solar directa por la tarde, ya que puede quemar las hojas. La planta tolera bien la sombra completa, sin embargo, demasiada poca luz puede provocar un crecimiento largo y una floración apagada. Begonia flaviflora puede adaptarse a la luz más baja intensificando la pigmentación de las hojas para maximizar la absorción de luz. En interiores, los alféizares de las ventanas orientadas al este o al oeste son lugares ideales, mientras que en exteriores, se recomienda plantarlas a la sombra bajo vegetación más alta. Una iluminación constante y adecuada es esencial para el crecimiento robusto y las flores vibrantes de begonia flaviflora.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Begonia flaviflora es conocido por su capacidad para soportar una amplia gama de temperaturas. Esta planta puede tolerar el frío hasta aproximadamente 0°C (32°F) y el calor hasta aproximadamente 43°C (109°F). Sin embargo, begonia flaviflora puede mostrar síntomas como el amarillamiento o marchitamiento de las hojas en condiciones extremas de frío y calor. En condiciones más frescas, medidas de protección como cubrir la planta o moverla al interior pueden ser beneficiosas. Durante el calor intenso, proporcionar suficiente agua y sombra ayudará a mantener su salud. Estas tolerancias de temperatura hacen que begonia flaviflora sea adecuada para climas diversos, aunque se necesita un cuidado adicional para asegurar su bienestar durante el clima extremo.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-6.5\n" +
                "\n" +
                "Begonia flaviflora prefiere un suelo bien drenado para evitar el encharcamiento. Se puede preparar una mezcla adecuada usando una parte de tierra para macetas y una parte de perlita o arena gruesa. Si la perlita no está disponible, se pueden usar puzolana o grava fina como alternativas. Mejora el crecimiento incorporando ocasionalmente un fertilizante líquido equilibrado mensualmente durante la temporada de crecimiento. El drenaje adecuado es crucial, así que asegúrate de que los contenedores tengan suficientes orificios de drenaje.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Originario de entornos húmedos, begonia flaviflora prospera con humedad constante pero está bien adaptado para evitar el encharcamiento. Esta especie prefiere la alta humedad y tiene tolerancia moderada a la sequía. Regar semanalmente mantendrá su crecimiento exuberante. Como favorito de interiores, begonia flaviflora se beneficia significativamente de rociar periódicamente para emular sus condiciones tropicales nativas, mejorando tanto la salud de las hojas como el potencial de floración.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'La fertilización de begonia flaviflora promueve un crecimiento robusto y flores vibrantes. Use fertilizante alto en fósforo cada 2-4 semanas durante las temporadas de crecimiento, de primavera a verano, siguiendo la dosis recomendada en el paquete. Durante el otoño y el invierno, reduzca la frecuencia a una vez al mes para evitar la sobre-fertilización. Aplique después de regar para evitar quemaduras en las raíces. Una alimentación más ligera es adecuada para begonia flaviflora joven o recién plantado para evitar la sobrecarga de nutrientes. Siempre asegúrese de que el suelo esté húmedo antes de fertilizar begonia flaviflora para una absorción óptima.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Begonia flaviflora es reconocida por su follaje vibrante y patrones de crecimiento únicos. La poda debe realizarse de principios a finales de primavera para fomentar un crecimiento saludable y una floración óptima. Las técnicas clave incluyen eliminar tallos muertos o enfermos, aclarar áreas abarrotadas y recortar para dar forma. Evita la poda excesiva, ya que begonia flaviflora es sensible a cortes drásticos. Una poda efectiva mejora la circulación del aire, la penetración de luz y el vigor general de la planta, lo que lleva a una exhibición más robusta.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Época de trasplante: Principios de primavera, Mediados de primavera\n" +
                "\n" +
                "El momento ideal para trasplantar begonia flaviflora es entre las temporadas S1-S2. Este momento favorece el desarrollo vigoroso de las raíces y reduce el impacto del trasplante. La planta prospera en lugares con luz solar moderada y suelo bien drenado. Recuerde que una manipulación suave durante el trasplante es clave para garantizar la prosperidad de la planta.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (100, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: Esquejes de hojas\n" +
                "\n" +
                "Begonia flaviflora es una especie que prospera en entornos con la humedad y la iluminación adecuadas. Para la propagación, el método de corte de hojas es bastante eficiente para esta planta. Los jardineros deben seleccionar una hoja saludable y cortarla en secciones, cada una con una vena. Posteriormente, estas secciones se pueden colocar en una mezcla de suelo bien drenante, asegurando que el borde cortado esté ligeramente enterrado. La humedad es crucial, por lo que un rociado rutinario ayuda a estimular el crecimiento de las raíces. La paciencia es clave, ya que las raíces pueden tardar varias semanas en desarrollarse.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Flor de nácar prospera en condiciones de sol parcial donde recibe luz brillante e indirecta durante la mayor parte del día. Esto garantiza un crecimiento robusto y una floración abundante. Si bien flor de nácar puede tolerar la sombra total, una sombra excesiva puede disminuir su vitalidad, lo que lleva a tallos largos y una floración reducida. Flor de nácar muestra rasgos adaptativos como ajustar la orientación de las hojas para optimizar la absorción de luz, y su color puede variar con la intensidad de la luz. En interiores, es mejor colocar a flor de nácar cerca de ventanas orientadas al este o sur. En exteriores, florece bajo luz solar filtrada, lo que lo convierte en un candidato ideal para plantaciones en el sotobosque o bordes de áreas sombreadas en el jardín.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Temperatura ideal: 15 - 41 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -5 - 45 ℃\n" +
                "\n" +
                "Flor de nácar puede soportar un amplio rango de temperaturas, desde tan bajas como -5℃ (23℉) hasta tan altas como 45℃ (113℉). Esto lo hace bastante adaptable a las extremas de frío y calor. Sin embargo, durante olas de frío intenso, flor de nácar puede presentar daños por heladas a través de marchitamiento o hojas ennegrecidas, mientras que el calor excesivo puede provocar hojas quemadas o enrolladas. En climas fríos, medidas protectoras como el acolchado o cubiertas temporales pueden proteger a flor de nácar de las heladas. Por el contrario, durante las olas de calor, proporcionar sombra y un riego adecuado ayuda a prevenir el sobrecalentamiento. Estas tolerancias hacen que flor de nácar sea adecuado para varios entornos, pero requieren un cuidado especial bajo condiciones extremas.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "Flor de nácar crece bien en una mezcla bien drenada de suelo arenoso, arcilloso y limo, con un rango de pH adecuado de 5.5-6.2. Sin embargo, flor de nácar crecerá mejor en ambientes sin suelo que en suelo convencional. Los jardineros pueden comprar medios de cultivo sin suelo premezclados, o mezclar 2/3 de turba o musgo de turba con 1/3 de otros medios de cultivo sin suelo como vermiculita, corteza y perlita para proporcionar un medio ligeramente ácido y bien drenado para la planta.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Flor de nácar prospera en suelos consistentemente húmedos, reflejando sus orígenes en la húmeda parte inferior del dosel de regiones tropicales. Esta especie muestra una preferencia por la hidratación regular sin encharcamiento, manteniendo un equilibrio que apoya su exuberante follaje. Para un crecimiento óptimo, flor de nácar debe ser regado cada semana. En interiores, flor de nácar se beneficia de niveles más altos de humedad que se pueden lograr colocando la maceta sobre una bandeja de guijarros mojados para emular su amor natural por el aire rico en humedad.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Para flor de nácar, utilice fertilizante alto en fósforo para impulsar la floración y la salud. Aplique cada 2 semanas durante la temporada de crecimiento a un cuarto de la fuerza. Reduzca la frecuencia a mensual en invierno. La sobre-fertilización puede dañar a flor de nácar, así que siga la dosis cuidadosamente. La incorporación de fertilizantes de liberación lenta puede proporcionar un suministro constante de nutrientes. Monitoree la respuesta de flor de nácar y ajuste según sea necesario para un crecimiento óptimo y vibrante.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Flor de nácar es una perenne resistente con un follaje exuberante y flores vibrantes. Pode a principios de primavera para promover un crecimiento saludable y floración. Retire tallos muertos o dañados y recorte ramas crecidas en exceso para mantener su forma. Evite la poda intensa durante la floración para preservar las flores. La poda mejora la circulación de aire, reduce el riesgo de enfermedades y fomenta un crecimiento robusto. Para obtener los mejores resultados, utilice herramientas desinfectadas y evite cortar demasiado cerca del tallo principal, lo que puede causar daños.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Época de trasplante: Primavera\n" +
                "\n" +
                "La flor de nácar prospera cuando se replanta durante los meses más frescos (S1-S3), ya que ayuda a reducir el choque del trasplante. Elija lugares soleados para que el trasplante tenga éxito. Si la tierra es pesada, es aconsejable mejorar el drenaje con compost.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (110, 'Época de propagación: Primavera, Principios de verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División\n" +
                "\n" +
                "Flor de nácar se propaga principalmente a través de esquejes, un método favorito para mantener la integridad de sus características notables. Al tomar esquejes, elija tallos sanos y corte debajo del nudo de la hoja donde es más probable que se formen raíces. Use una hormona de enraizamiento para fomentar un establecimiento rápido y plante los esquejes en una mezcla de suelo bien drenado, asegurando alta humedad y humedad constante sin encharcamiento. Proporcionar luz brillante e indirecta apoyará el desarrollo de un nuevo crecimiento.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Begonia robusta prospera en sol parcial, donde recibe una mezcla suave de luz y sombra, evitando el estrés de la intensidad del mediodía. Este equilibrio promueve un follaje exuberante y flores vibrantes. Aunque begonia robusta puede tolerar la sombra completa, su potencial de floración se reduce en condiciones de poca luz, ya que la energía para florecer se obtiene de una exposición adecuada a la luz. Al aire libre, begonia robusta se beneficia de jardines orientados al este que la protegen de la luz dura de la tarde, mientras que en interiores, una habitación brillante con luz filtrada le queda mejor. Adaptativamente, begonia robusta puede mostrar una pigmentación más profunda en lugares más luminosos, una especie de protector solar natural.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Temperatura ideal: 10 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -15 - 38 ℃\n" +
                "\n" +
                "Begonia robusta demuestra una impresionante resistencia a las variaciones de temperatura. Puede soportar temperaturas frías de hasta -15℃ (5°F) y resistir calor de hasta 38℃ (100°F). Este amplio rango hace que begonia robusta sea adecuado para diversos entornos, aunque se debe tener cuidado durante los extremos de temperatura. En condiciones frías, puede presentar síntomas como la decoloración de las hojas o marchitez. Proteja a begonia robusta de las heladas aplicando una capa de mulching o utilizando cobertores. Durante las olas de calor, asegúrese de un riego adecuado y proporcione sombra para evitar la quemadura de las hojas. Monitorear estos factores asegura que begonia robusta prospere a pesar de los desafíos térmicos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Composición del suelo: Marga, Arena, Arcilla, Calcáreo\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-7\n" +
                "\n" +
                "Begonia robusta prospera en suelos bien aireados y drenados. Para una mezcla adecuada, combina dos partes de turba, una parte de perlita o arena gruesa para mejorar el drenaje. Si la turba no está disponible, utiliza una mezcla de tierra para macetas y corteza de pino. Asegúrate de que las macetas tengan agujeros de drenaje para evitar el encharcamiento. Incorpora fertilizante de liberación lenta al principio de la temporada de crecimiento para mejorar el crecimiento.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Originaria de los entornos sombreados y húmedos del Este de Asia, begonia robusta prospera en condiciones que imitan la humedad de su hábitat nativo. Esta especie muestra una preferencia por la humedad constante pero puede tolerar períodos cortos de sequía. El riego regular cada semana es esencial para mantener su exuberante follaje y apoyar la floración. Como una planta perenne herbácea a menudo cultivada al aire libre, begonia robusta se beneficia significativamente del acolchado, que ayuda a retener la humedad del suelo y reduce la necesidad de riego frecuente.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Para un crecimiento óptimo, begonia robusta se beneficia de la fertilización con abonos de nutrición equilibrada, promoviendo un follaje y floración vibrantes. Aplica una solución a media potencia mensualmente durante la temporada de crecimiento; reduce en invierno. El exceso puede dañar las raíces, por lo que las medidas precisas son vitales. Durante la fase de reposo de la planta, evita la fertilización para prevenir el estrés innecesario. Tanto los jardineros novatos como veteranos deben asegurar una distribución uniforme para evitar desequilibrios nutricionales, observando la respuesta de begonia robusta para ajustar el régimen. Siempre sigue las instrucciones del paquete para un begonia robusta saludable y próspero.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Época de poda: Primavera, Verano\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Conocido por sus hojas en forma de corazón y delicadas flores rosas, begonia robusta se beneficia de la poda a principios y finales de primavera. Retire los tallos muertos o dañados para promover un crecimiento saludable. Recorte la planta para mantener su forma y fomentar un follaje más denso. Evite la poda pesada durante la temporada de floración para prevenir el estrés. La poda no solo mejora la apariencia, sino que también mejora la circulación del aire, reduciendo el riesgo de enfermedades.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Época de trasplante: Verano, Principios del otoño\n" +
                "\n" +
                "Para un crecimiento floreciente begonia robusta, trasplántelos desde principios de verano hasta principios de otoño. Elija un lugar parcialmente sombreado para mantener su vibrante follaje. No olvide plantarlos en un suelo que drene bien, a una distancia de unos 45 cm entre sí, para un éxito óptimo.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (120, 'Época de propagación: Principios de primavera\n" +
                "\n" +
                "Tipo de propagación: Corte, División\n" +
                "\n" +
                "Una planta perenne amante de la sombra, begonia robusta puede propagarse mediante esquejes sin necesidad de intervención excesiva. Los jardineros pueden cortar una hoja con un trozo del tallo, asegurando que haya un nodo presente. Esta porción se coloca luego en un medio de crecimiento adecuado, típicamente una mezcla de turba y perlita para un enraizamiento óptimo. Mientras se mantiene el suelo consistentemente húmedo pero no empapado, las raíces generalmente se establecen en unas pocas semanas, listas para ser trasplantadas en macetas individuales o en áreas del jardín con suelos bien drenados.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: Sombra total\n" +
                "\n" +
                "Cactus de navidad prospera a la luz parcial, requiriendo luz brillante pero indirecta para optimizar su crecimiento y potencial de floración. Aunque cactus de navidad puede tolerar la sombra total, tales condiciones pueden llevar a una reducción de vigor y menos flores. Idealmente, cactus de navidad prefiere exposición a la luz por hasta 12-14 horas al día para imitar su hábitat natural. La desviación de estas condiciones puede resultar en un crecimiento alargado, hojas pálidas y salud disminuida. Si se cultiva en interiores, las ventanas orientadas al este o al norte son ubicaciones ideales, mientras que en exteriores se desempeñan mejor a la sombra parcial. Su follaje puede oscurecerse al adaptarse a intensidades de luz más bajas, como un rasgo único para maximizar la absorción de luz.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Cactus de navidad demuestra una admirable resistencia a las variaciones de temperatura, tolerando desde 0℃ (32℉) hasta 43℃ (109℉). A pesar de esta adaptabilidad, es sensible a los extremos, lo que requiere medidas de protección durante el clima severo. El sobrecalentamiento puede provocar amarillamiento y marchitez de las hojas, mientras que el enfriamiento excesivo puede causar crecimiento atrofiado y caída de hojas. Asegúrese de proporcionar suficiente sombra y circulación de aire en condiciones de calor, y mueva la planta a un lugar más cálido o use mantas de escarcha durante los períodos de frío. Esta adaptabilidad hace que cactus de navidad sea adecuada para una variedad de entornos, pero requiere un cuidado atento para manejar los extremos de temperatura.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Composición del suelo: Arena, Marga, Franco-arenoso\n" +
                "\n" +
                "Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 5.5-6.5\n" +
                "\n" +
                "Cactus de navidad prospera en un suelo que drene bien para prevenir la descomposición de las raíces. Una mezcla adecuada incluye partes iguales de suelo para suculentas y cactus, perlita y corteza de orquídeas, proporcionando aireación y drenaje. En ausencia de suelo especializado, mezcle tierra de maceta regular con pómice o arena gruesa. Para impulsar el crecimiento, incorpore fertilizante de liberación lenta al comienzo de la temporada de crecimiento. Asegúrese de que las macetas tengan agujeros de drenaje para evitar el encharcamiento.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Horario de riego: Cada 2-3 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Cactus de navidad proviene de las selvas tropicales, donde prospera en alta humedad. Tiene un requerimiento equilibrado de agua, ni demasiado ni muy poco. Esta planta debe regarse una vez cada 2-3 semanas para mantener su salud. Como planta perenne, cactus de navidad conserva sus hojas durante todo el año, por lo que es crucial controlar su consumo de agua incluso durante los meses más fríos.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Fertilice cactus de navidad con fertilizante para cactus a media fuerza cada 2-4 semanas durante el crecimiento activo en primavera y verano. Esto aumenta la floración y la salud. Evite la sobre-fertilización para prevenir quemaduras en las raíces. En otoño e invierno, reduzca la alimentación a medida que cactus de navidad entra en un período de descanso. Utilice cantidades precisas según las instrucciones del paquete de fertilizante para seguridad y efectividad.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Corte la planta en primavera para fomentar un crecimiento más completo. Se ramificará y las flores crecerán en el corte. Las tijeras de podar limpias y afiladas son los mejores instrumentos para esto para evitar romper el tallo.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Época de trasplante: Mediados de primavera, Finales de primavera, Mediados de otoño, Finales de otoño\n" +
                "\n" +
                "La estación ideal para trasplantar cactus de navidad es de mediados a finales de primavera o de mediados a finales de otoño, ya que la planta se beneficia de temperaturas más frescas y condiciones de suelo más estables. Asegúrese de elegir un lugar con suelo que drene bien, luz solar adecuada y protección contra vientos fuertes.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (130, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, Injertos, Siembra\n" +
                "\n" +
                "Cactus de navidad puede propagarse eficazmente mediante esquejes. Para asegurar el éxito, es crucial usar un implemento limpio y afilado al tomar un segmento de una planta madre sana. Los esquejes deben incluir uno o dos segmentos y dejarse secar durante unos días antes de plantar en un suelo bien drenado. Al regar, es necesario tener un toque ligero para prevenir la pudrición. El enraizamiento generalmente ocurre dentro de unas pocas semanas, después de lo cual puede seguir las pautas regulares de cuidado para cactus de navidad.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Flores de nopal prospera mejor a plena luz solar, lo que apoya óptimamente su crecimiento, salud y capacidad de florecimiento. Esta intensidad de luz estimula un desarrollo robusto, intensificando la fotosíntesis que es crucial para su supervivencia y floración. Aunque flores de nopal generalmente prefiere la luz solar directa, muestra buena tolerancia a la exposición parcial al sol, aunque puede haber un ligero impacto en su tasa de crecimiento y vigor. Se recomienda el crecimiento al aire libre en áreas que reciben abundante luz solar durante la mayor parte del día. La adaptabilidad de flores de nopal a la luz puede variar con ligeros cambios en su coloración para protegerse de la exposición intensa al sol.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Flores de nopal se distribuye en regiones tropicales, subtropicales y semi desérticas con lluvias escasas, luz solar intensa y altas temperaturas. La temperatura óptima para su crecimiento es de 68-95℉. Por debajo de 50℉ o por encima de 95℉, el crecimiento se ralentiza y comienza la dormancia. La humedad del aire no debe ser muy alta y el ambiente debe estar bien ventilado. Evite la alta humedad y el agua estancada.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Composición del suelo: Arena, Marga, Calcáreo, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 5.5-7.5\n" +
                "\n" +
                "Flores de nopal prospera en una mezcla de suelo que drene bien como la de cactus o suculentas. Para mejorar el drenaje, inserte perlita o pómice. Si no están disponibles, la arena gruesa puede ser un sustituto. Asegúrese de que las macetas tengan agujeros de drenaje. Durante el crecimiento, agregue fertilizante equilibrado a media fuerza mensualmente. Evite el encharcamiento para prevenir la pudrición de las raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Horario de riego: Cada 3 semanas\n" +
                "\n" +
                "Flores de nopal prospera en entornos áridos, exhibiendo una alta tolerancia a la sequía típica de su hábitat nativo del desierto. Se ha adaptado para retener agua de manera eficiente y requiere un riego mínimo adicional, prefiriendo condiciones secas sobre la humedad. Se debe tener cuidado de regar flores de nopal con moderación, aproximadamente una vez cada tres semanas, asegurando que el suelo se seque entre riegos para evitar la pudrición de las raíces. Por lo general, se cultiva al aire libre debido a su necesidad de exposición total al sol y al calor. Flores de nopal se beneficia de la lluvia ocasional pero está bien adaptado para resistir largos períodos sin precipitaciones.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Fertilice flores de nopal durante su temporada de crecimiento (de primavera a principios de otoño) con un alimento equilibrado para suculentas y cactus, diluido a la mitad de su fuerza. Aplique mensualmente para un crecimiento y floración robustos. Evite la sobre-fertilización para prevenir quemaduras en las raíces. En invierno, deje de alimentar a flores de nopal para que descanse. Tenga cuidado de evitar que el fertilizante entre en contacto con los tallos y espinas, ya que esto puede causar daños. Con una alimentación balanceada, flores de nopal prosperará con mayor vigor y resistencia.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Flores de nopal no requiere mucho poda. Elimine las flores y ramas marchitas para que la planta utilice los nutrientes en un crecimiento saludable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Época de trasplante: Mediados de primavera\n" +
                "\n" +
                "La época perfecta para trasladar flores de nopal es el clima templado de /S2/, ya que favorece un crecimiento robusto. Elija un lugar soleado con buen drenaje para que prospere. Tenga cuidado al manipular sus frágiles almohadillas durante el trasplante para evitar daños. Es un proceso sencillo basado en hechos fiables.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (140, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, Siembra, Estratificación\n" +
                "\n" +
                "Este cactus prospera cuando sus almohadillas se utilizan para la propagación. Los jardineros deben manipular las almohadillas con cuidado debido a su naturaleza frágil. Para propagar flores de nopal, separe una almohadilla sana y permita que el extremo cortado se forme una costra durante unos días. Una vez formada la costra, plante la almohadilla en una mezcla de cactus que drene bien, asegurando que el suelo esté ligeramente húmedo. Coloque la almohadilla erguida, parcialmente enterrada y proporcione luz solar brillante e indirecta. Con un mínimo riego y buena circulación de aire, las raíces deberían comenzar a formarse, estableciendo una nueva planta.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol\n" +
                "\n" +
                "Cactus colgante prospera bajo condiciones de luz parcial, necesitando luz brillante pero indirecta para mantener un crecimiento y salud óptimos. Aunque cactus colgante puede tolerar pleno sol, la exposición prolongada puede causar estrés, que puede manifestarse como decoloración o crecimiento limitado. En su hábitat natural, cactus colgante se beneficia de la luz tamizada que imita la cobertura del dosel de plantas más grandes. Para aquellos que cultivan cactus colgante en interiores, las ventanas orientadas al este o norte son adecuadas, proporcionando la luz requerida sin la dureza del sol del mediodía. Cuando se planta en exteriores, un lugar que recibe luz solar filtrada la mayor parte del día es ideal para prevenir daños por el sol mientras aseguras una ingesta de luz adecuada.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Cactus colgante puede soportar una variedad de temperaturas que van de 0℃ a 43℃ (32°F a 109°F). Esta resistencia lo hace adaptable tanto a extremos de frío como de calor, aunque son esenciales medidas de protección durante el clima extremo. En condiciones frías, cactus colgante puede presentar síntomas como decoloración del tallo o ablandamiento, mientras que el calor excesivo puede causar marchitamiento o quemaduras por el sol. Para mitigar estos problemas, asegúrese de aislar la planta durante olas de frío y proporcionar sombra y riego adecuado durante las olas de calor. Esta tolerancia a la temperatura permite que cactus colgante prospere en diversos entornos, pero el cuidado constante es crucial para prevenir el estrés por temperatura.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 5.5-7\n" +
                "\n" +
                "Para cactus colgante, la adecuada aireación del suelo es crucial. Use una mezcla para suculentas y cactus que garantice un buen drenaje. Si no está disponible, mezcle partes iguales de tierra para macetas, arena y perlita. Esto evitará el encharcamiento, al que cactus colgante es sensible. Potencie el crecimiento agregando un fertilizante de liberación lenta durante la temporada de crecimiento. Inspeccione regularmente el nivel de humedad del suelo para evitar agua estancada alrededor de las raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Horario de riego: Cada 2 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad baja\n" +
                "\n" +
                "Originaria del sotobosque de bosques tropicales, cactus colgante se ha adaptado a entornos con alta humedad y luz indirecta. Prospera cuando la humedad del suelo se mantiene de manera constante, sin encharcamientos. Esta especie muestra una preferencia por la hidratación regular pero puede tolerar breves periodos de sequía debido a su naturaleza suculenta, necesitando riego una vez cada dos semanas. Idealmente cultivada en interiores debido a su necesidad de condiciones estables, cactus colgante florece cuando la humedad del aire imita su hábitat nativo, mejorando tanto el crecimiento como la vitalidad.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Fertiliza a cactus colgante usando (Alimento para Suculentas y Cactus) trimestralmente para mantener el crecimiento y la floración. Diluye a la mitad la dosis recomendada para prevenir quemaduras en las raíces. Las plántulas se benefician de la alimentación mensual. En invierno, reduce la fertilización para adaptarte a la dormancia de cactus colgante. Aplica durante el riego para una distribución uniforme, evitando el contacto directo con la planta para prevenir daños. Una fertilización adecuada garantiza un follaje saludable y robustez, crucial para la vitalidad de cactus colgante.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Cactus colgante no requiere poda extensa. Elimina las flores y ramas marchitas para que la planta use los nutrientes en un crecimiento saludable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Época de trasplante: Finales de primavera, Verano\n" +
                "\n" +
                "Trasplante cactus colgante idealmente entre finales de primavera y finales de verano, ya que el clima cálido favorece el establecimiento de las raíces. Elija un lugar con luz brillante e indirecta y buen drenaje. Para que el trasplante tenga éxito, es fundamental manipular con cuidado las frágiles raíces.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (150, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, Siembra\n" +
                "\n" +
                "Cactus colgante prospera cuando se propaga a través del método de esquejes. Para obtener mejores resultados, toma esquejes de tallos saludables y déjalos secar ligeramente antes de insertarlos en una mezcla de suelo bien drenada. Asegúrate de mantener el suelo húmedo, pero no encharcado para prevenir la pudrición. El desarrollo de raíces suele ser robusto, requiriendo una intervención mínima después del trasplante. La luz consistente pero indirecta ayuda en un crecimiento y establecimiento de raíces eficientes.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Cactus catedral prospera a pleno sol, donde la intensidad de la luz alimenta un crecimiento robusto y mantiene la vibrante coloración de sus hojas. Aunque cactus catedral puede adaptarse a sol parcial, la exposición prolongada a condiciones más tenues podría conducir a un crecimiento subóptimo y una posible pérdida de su característico color. Los tallos carnoso y verticales de cactus catedral son resistentes, pero prefieren un entorno soleado típicamente encontrado en exteriores en posiciones soleadas. Cuando se ubica adecuadamente, cactus catedral muestra un desarrollo saludable y estética llamativa, encarnando la resistencia y adaptabilidad en escenarios de luz variados.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Cactus catedral demuestra una notable resistencia a las variaciones de temperatura, tolerando el frío hasta 5℃ (41℉) y el calor hasta 43℃ (110℉). Este rango lo hace adaptable a una variedad de entornos, pero se deben tomar precauciones durante el clima extremo. En condiciones frías, cactus catedral puede mostrar marchitez o tallos ennegrecidos, mientras que el sobrecalentamiento puede llevar a hojas quemadas por el sol y deshidratación. Las medidas de protección, como la reubicación en interiores o la sombra, son esenciales cuando las temperaturas se acercan a estos extremos. Reconocer estos signos y ajustar el cuidado de manera oportuna ayudará a mantener la salud y longevidad de cactus catedral.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'pH del suelo: 6-7.5\n" +
                "\n" +
                "Las euforbias no son exigentes en cuanto al tipo de suelo. Pueden crecer en una variedad de suelos. Algunas especies de Euphorbia incluso pueden prosperar en suelos pobres en nutrientes, requiriendo solamente que el suelo esté bien drenado. Si el suelo está lleno de humedad y propenso a retener agua, es muy probable que cause podredumbre de raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Horario de riego: Cada 3 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad baja\n" +
                "\n" +
                "Cactus catedral prospera en entornos que imitan sus hábitats nativos semiáridos, donde las lluvias poco frecuentes dictan la necesidad de conservación del agua. Esta especie exhibe una fuerte tolerancia a la sequía y prefiere condiciones secas sobre humedad en exceso. Su régimen de riego se alinea con su naturaleza resistente, requiriendo hidratación una vez cada tres semanas para mantener un equilibrio óptimo. Como cactus catedral suele cultivarse en interiores debido a su llamativa apariencia y bajo mantenimiento, es importante considerar los niveles de humedad en interiores que pueden afectar la absorción de agua de la planta y su salud general.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Para un crecimiento óptimo, fertiliza cactus catedral con alimentos para suculentas y cactus durante la temporada de crecimiento activa, de primavera a principios de otoño. Las aplicaciones trimestrales son ideales, siguiendo la dilución recomendada en la etiqueta, que suele ser de 1/4 a 1/2 de la fuerza. Esto promueve un crecimiento vigoroso y la robustez de la planta. Evita la sobre-fertilización y detén la alimentación en invierno, cuando el crecimiento de cactus catedral se ralentiza. Siempre riega abundantemente después de fertilizar para distribuir los nutrientes y prevenir quemaduras en las raíces.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'No es necesario la poda.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Época de trasplante: Principios de verano, Mediados de verano, Finales de otoño, Invierno\n" +
                "\n" +
                "La temporada ideal para trasplantar cactus catedral es de principios a mediados de verano o de finales de otoño a finales de invierno, ya que estas estaciones ofrecen unas condiciones de crecimiento óptimas. Cuando la trasplante, elija un lugar con suelo que drene bien y sol parcial. Asegúrese de que las raíces se establezcan y crezcan sanas regando cactus catedral con regularidad y proporcionándole el apoyo necesario.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (160, 'Tipo de propagación: Corte\n" +
                "\n" +
                "Cactus catedral es una suculenta resistente que prospera con el cuidado adecuado. Para su propagación, corta un tallo sano, permite que la superficie se forme una costra y plántalo en un suelo bien drenado. Una hormona enraizante puede facilitar el crecimiento, pero cactus catedral suele enraizar con éxito sin ella. Asegura una luminosidad y calor adecuados, y mantiene una humedad ligera sin regar en exceso para fomentar el enraizamiento y el nuevo crecimiento.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Cactus cacahuete prospera mejor cuando se expone a pleno sol, lo cual es crucial para un crecimiento robusto y la prosperidad de sus vibrantes flores. Una iluminación adecuada también garantiza una forma saludable y compacta al prevenir la etiolación, donde los tallos se alargan debido a la luz insuficiente. Aunque cactus cacahuete tolera el sol parcial, los niveles de luz más bajos pueden reducir su potencial de floración y causar una forma de crecimiento más alargada. En interiores, cactus cacahuete debe colocarse en lugares donde pueda recibir abundante luz solar, como ventanas orientadas al sur, y si se cultiva al aire libre, prefiere lugares soleados posiblemente con algo de sombra por la tarde en climas más calurosos. Cactus cacahuete muestra resiliencia pero brilla en su hábitat luminoso preferido.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Temperatura ideal: 15 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: -10 - 43 ℃\n" +
                "\n" +
                "Cactus cacahuete es notablemente resistente a una variedad de temperaturas, tolerando el frío hasta -10℃ (14℉) y el calor hasta 43℃ (109℉). A pesar de esta durabilidad, cactus cacahuete necesita cuidados específicos para prosperar; la protección contra las heladas es crucial, mientras que la sombra adecuada y el riego son útiles durante las olas de calor. Los síntomas de estrés incluyen marchitamiento o decoloración en calor y tallos blandos y acuosos en frío. Para mitigar el enfriamiento excesivo, utilice telas contra heladas o reubicación en interiores; para el sobrecalentamiento, asegúrese de proporcionar suficiente agua y sombra. Su adaptabilidad hace que cactus cacahuete sea adecuado para varios climas, siempre que se sigan estas medidas de cuidado.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Composición del suelo: Arena, Marga\n" +
                "\n" +
                "Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 6-7.5\n" +
                "\n" +
                "El suelo adecuado para cactus cacahuete requiere un excelente drenaje para prevenir la pudrición de raíces. Una mezcla comercial de sustrato para cactus es suficiente, que incluye arena, perlita y compost estéril. Si no está disponible, combine partes iguales de arena gruesa y tierra de maceta, agregando perlita para aumentar la aireación. Anualmente, mezcle un fertilizante de liberación lenta en el suelo para fomentar el crecimiento. Recuerde, el exceso de riego es peligroso; asegúrese de que las macetas tengan agujeros de drenaje.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Horario de riego: Cada 3 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad baja\n" +
                "\n" +
                "Originaria de regiones áridas, cactus cacahuete ha evolucionado para prosperar con una humedad mínima, presumiendo de una alta tolerancia a la sequía. Los patrones de lluvia escasos en su hábitat natural han condicionado a cactus cacahuete para almacenar agua eficientemente, lo que solo requiere riego ocasional una vez cada tres semanas. Como planta de exterior comúnmente cultivada en contenedores, la naturaleza suculenta de cactus cacahuete le permite resistir períodos secos conservando agua en sus tejidos carnosos, una característica particularmente beneficiosa durante la temporada de crecimiento calurosa.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Para un cuidado óptimo, fertilice cactus cacahuete con un fertilizante balanceado para suculentas durante su temporada de crecimiento (primavera y verano). Aplique una dosis al 25% quincenalmente, observando una frecuencia reducida a mensual en otoño, y absténgase en invierno. Estos nutrientes impulsan el crecimiento, el potencial de floración y la resistencia, asegurando vigor. Al aplicar, diluya correctamente y evite el contacto directo con cactus cacahuete para prevenir quemaduras de raíces. Los ajustes estacionales en el régimen son cruciales para la salud de cactus cacahuete.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Cactus cacahuete no requiere mucho poda. Retire las flores y ramas marchitas para que la planta use los nutrientes en un crecimiento saludable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Época de trasplante: Mediados de primavera, Finales de primavera, Principios de verano\n" +
                "\n" +
                "El momento perfecto para trasplantar cactus cacahuete es entre mediados de primavera y principios de verano, ya que ofrece unas condiciones de crecimiento óptimas. Elija un lugar soleado con suelo que drene bien para el trasplante. No olvide manipular suavemente sus delicadas raíces durante el trasplante, para garantizar un crecimiento satisfactorio.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (170, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División, Siembra\n" +
                "\n" +
                "Cactus cacahuete es un tipo de cactus que prefiere luz indirecta y suelo bien drenante para un crecimiento óptimo. La propagación se logra comúnmente mediante esquejes. Para propagar, seleccione un segmento sano y permita que se cale para evitar la pudrición cuando se plante el esqueje. Una vez que el esqueje esté colocado en el sustrato adecuado, mantenga un equilibrio de humedad sin encharcamiento, asegurando una tasa de éxito más alta para el nuevo crecimiento. El éxito en la propagación depende de condiciones estables con mínimas fluctuaciones en temperatura y humedad.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol, Sombra total\n" +
                "\n" +
                "Cactus orquídea prospera en ambientes con luz brillante e indirecta, preferiblemente con sol parcial para un crecimiento equilibrado. Las condiciones de luz óptimas permiten a cactus orquídea desarrollar un frondoso follaje y flores vibrantes. Una desviación de esto, ya sea hacia pleno sol o sombra total, puede ser tolerada, pero puede afectar la salud de cactus orquídea; la luz solar directa excesiva podría provocar quemaduras en las hojas, mientras que la luz insuficiente puede resultar en un crecimiento desgarbado y una menor floración. En interiores, cactus orquídea debe colocarse cerca de ventanas orientadas al este y, en exteriores, bajo cobertura parcial. Rasgos adaptativos como la capacidad de soportar diversas condiciones de luz hacen que cactus orquídea sea adecuada para varios entornos de cultivo.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Cactus orquídea presenta una resistencia moderada a las fluctuaciones de temperatura, pero tiene límites específicos de tolerancia. Puede soportar temperaturas tan bajas como 5℃ (41℉) y tan altas como 43℃ (109℉). Esto hace que cactus orquídea sea algo adaptable, pero requiere medidas de protección en entornos con frío o calor extremos. Los síntomas de condiciones demasiado frías incluyen amarillamiento y caída de las hojas, mientras que el calor excesivo puede causar quemaduras en las hojas y deshidratación. Para contrarrestar esto, proporciona aislamiento o mueve la planta al interior durante los períodos de frío y asegúrate de brindar sombra y riego adecuado en condiciones de calor extremo. Estas consideraciones de cuidado ayudan a mantener la salud y vitalidad de la planta.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Composición del suelo: Arena, Marga\n" +
                "\n" +
                "Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 6-7.5\n" +
                "\n" +
                "Cactus orquídea prospera en suelos bien drenados, ya que es propensa a la pudrición de raíces. Para obtener los mejores resultados, utiliza una mezcla específica para cactus y suculentas, que está fácilmente disponible y satisface las necesidades de cactus orquídea. Si no está disponible, combina partes iguales de tierra para macetas, arena gruesa y perlita para imitar este ambiente. Asegúrate de que la mezcla permita un drenaje y aeración adecuados, lo cual es crucial para la salud de cactus orquídea. Añade al suelo un fertilizante equilibrado soluble en agua dos veces al año para un crecimiento óptimo.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Horario de riego: Cada 2 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Cactus orquídea prospera en ambientes que imitan su hábitat nativo de la selva tropical, donde la alta humedad y la lluvia regular son frecuentes. Esta especie muestra una preferencia por la humedad constante pero es adaptable a períodos de sequía. El riego debe ajustarse a una vez cada dos semanas, asegurando que el suelo tenga tiempo para secarse ligeramente entre sesiones. Como planta epífita comúnmente cultivada en interiores, cactus orquídea se beneficia enormemente de la nebulización durante su temporada de crecimiento activa para mantener la humedad ambiental que anhela.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Fertiliza cactus orquídea cada 2-3 semanas durante el crecimiento activo (primavera y verano) con un fertilizante específico para cactus al 25% para impulsar la floración y la salud de la planta. Reduce a una vez al mes en otoño y suspende en invierno. La sobre-fertilización puede dañar las raíces y el follaje, así que adhiérete a concentraciones diluidas y asegura una cobertura uniforme del suelo. Supervisa las etapas de crecimiento y ajusta la fertilización según corresponda para obtener un vigor y rendimiento óptimos en la floración.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Cactus orquídea no requiere poda. Elimina flores y ramas marchitas para que la planta utilice los nutrientes en un crecimiento saludable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Época de trasplante: Mediados de primavera, Finales de primavera, Verano, Otoño, Principios de invierno\n" +
                "\n" +
                "La mejor época para trasplantar cactus orquídea es desde mediados de primavera hasta principios de invierno. Las condiciones favorables incluyen luz brillante e indirecta en lugares bien drenados. El trasplante se realiza mejor con cuidado para aflojar suavemente las raíces y mantener la humedad del suelo para una transición satisfactoria. Sea amable y convincente.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (180, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, Injertos, Siembra\n" +
                "\n" +
                "Cactus orquídea, un cactus epífita vibrante con impresionantes flores rojas, prospera cuando se propaga por esquejes, un método que armoniza perfectamente con sus hábitos de crecimiento. Inicia seleccionando un segmento de tallo sano; un corte limpio y estratégico garantiza un estrés mínimo. Permite que el esqueje forme una costra durante unos días, promoviendo la curación y reduciendo el riesgo de pudrición. Al plantar, utiliza un sustrato bien drenado y con grava para imitar su entorno nativo, fomentando un desarrollo de raíces robusto. La luz indirecta cataliza el crecimiento sin quemar los tejidos delicados. El uso de hormonas enraizantes es opcional pero puede acelerar la etapa de establecimiento, seguido de un riego moderado que equilibra la hidratación con la naturaleza tolerante a la sequía de la especie.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Biznaga elongada prospera mejor en condiciones con luz solar completa, un ambiente que contribuye a su crecimiento robusto y floración. La duración de la exposición a la luz brillante debe coincidir con el ciclo natural de luz del día. Biznaga elongada puede soportar sol parcial, pero períodos prolongados fuera de su preferencia de luz pueden resultar en patrones de crecimiento subóptimos y una reducción de la floración. Adaptativamente, biznaga elongada puede modificar su morfología para una mejor absorción de luz. Aunque es principalmente una especie de exterior, cuando se planta al aire libre, biznaga elongada debe ocupar un lugar que garantice varias horas de luz solar directa, evitando áreas donde predomine la sombra, para mantener su salud y vigor.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Biznaga elongada exhibe una notable resistencia a las variaciones de temperatura, tolerando el frío hasta 0℃ (32℉) y el calor hasta 43℃ (109℉). Esta adaptabilidad significa que biznaga elongada puede prosperar en una variedad de entornos, pero requiere ciertas medidas de protección. Durante el frío extremo, pueden aparecer síntomas como decoloración y tallos blandos, lo que indica la necesidad de aislamiento o reubicación en interiores. Por otro lado, el calor excesivo puede causar marchitamiento y manchas de quemaduras solares; la sombra y el riego adecuado ayudan a mitigar estos efectos. Comprender estas tolerancias de temperatura asegura un cuidado óptimo y longevidad para biznaga elongada en diversos entornos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Composición del suelo: Arena, Marga\n" +
                "\n" +
                "Tipo de suelo: Tierra para suculentas y cactus\n" +
                "\n" +
                "pH del suelo: 6-7.5\n" +
                "\n" +
                "Biznaga elongada prospera en una mezcla de suelo bien drenado, lo que previene la pudrición de raíces. Para crear una mezcla adecuada, combina un 50% de tierra para macetas con un 50% de perlita o pómez. Si la perlita o la pómez no están disponibles, utiliza arena gruesa. Asegúrate de que las macetas tengan agujeros de drenaje para ayudar en la evacuación del exceso de agua. Para mejorar el crecimiento, mezcla fertilizante de liberación lenta en el suelo al comienzo de la temporada de crecimiento. Mantén la mezcla para macetas aireada y evita compactarla para apoyar el sensible sistema de raíces de biznaga elongada.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Horario de riego: Cada 3 semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad baja\n" +
                "\n" +
                "Biznaga elongada prospera en ambientes áridos, reflejando sus condiciones desérticas nativas donde el agua es escasa. Este cactus se ha adaptado para almacenar agua de manera eficiente, lo que lo hace altamente tolerante a la sequía. Requiere un riego mínimo, con un calendario de una vez cada tres semanas siendo suficiente para mantener su salud. Como planta de interior, biznaga elongada prefiere luz brillante y un suelo bien drenado para evitar la pudrición de raíces por exceso de humedad. En cuanto a los ciclos de crecimiento, el riego poco frecuente contribuye al patrón de crecimiento lento pero constante de biznaga elongada.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Para un crecimiento óptimo, fertiliza biznaga elongada cada 4-6 semanas durante las temporadas de crecimiento activo, primavera y verano, con un fertilizante específico para suculentas y cactus diluido. Esto mejora la salud de biznaga elongada, realzando la floración mientras mantiene la robustez. Aplica dosis al 25% para evitar quemaduras en raíces, deteniéndote en los periodos de hibernación de otoño e invierno. Utiliza formulaciones granulares o líquidas por igual, asegurando una distribución uniforme en el suelo y evitando el contacto directo con la superficie de biznaga elongada. Siempre riega bien a biznaga elongada antes y después de la aplicación para facilitar la absorción y prevenir la sobrecarga de nutrientes, crucial para el delicado sistema de raíces de biznaga elongada.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Biznaga elongada no requiere mucha poda. Elimina flores y ramas marchitas para que la planta utilice los nutrientes en un crecimiento saludable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Época de trasplante: Principios de verano, Mediados de verano\n" +
                "\n" +
                "El mejor momento para trasplantar biznaga elongada es durante los meses cálidos y soleados de principios a mediados del verano. Esto permite que la planta eche raíces sanas en su nueva ubicación. Durante el trasplante, asegúrese de que biznaga elongada reciba mucha luz solar indirecta y un suelo que drene bien. Manipule con cuidado las delicadas espinas durante el proceso para que el trasplante tenga éxito.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (190, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División, Siembra\n" +
                "\n" +
                "Biznaga elongada, un suculento nativo de los desiertos de México, prospera en condiciones que imitan su hábitat natural. Para una propagación exitosa, asegura luz brillante y una mezcla de suelo bien drenado. Los esquejes son el método ideal; selecciona una sección saludable, permítela que se cale para evitar la pudrición por varios días y luego planta en una mezcla de cactus adecuada. Riega escasamente una vez que el esqueje haya enraizado para fomentar el crecimiento y evita el riego excesivo, que es perjudicial para biznaga elongada.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'La Calathea majestica necesita luz indirecta brillante para desarrollarse bien. Aquí tienes los detalles específicos:\n" +
                "\n" +
                "Tipo de luz ideal: Luz natural filtrada, como la que pasa a través de una cortina delgada, o cerca de una ventana orientada al este o al norte.\n" +
                "\n" +
                "Evita: La luz solar directa, especialmente en las horas fuertes (mediodía), ya que puede quemar sus hojas y decolorar sus vetas.\n" +
                "\n" +
                "También tolera: Luz media o baja, pero su crecimiento será más lento y los colores de sus hojas pueden perder intensidad.\n" +
                "\n" +
                "Consejo extra: Gira la maceta de vez en cuando para que crezca de manera uniforme y no se incline hacia la fuente de luz.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'La Calathea majestica es muy sensible a los cambios de temperatura y necesita un ambiente cálido y estable. Aquí te detallo sus requerimientos:\n" +
                "\n" +
                "Rango ideal: Entre 18 °C y 26 °C. No le gusta el frío.\n" +
                "\n" +
                "Nunca exponerla a temperaturas por debajo de 15 °C, ya que puede sufrir daños en las hojas o detener su crecimiento.\n" +
                "\n" +
                "Evita corrientes de aire frío, salidas de aire acondicionado o cambios bruscos de temperatura.\n" +
                "\n" +
                "Ambiente estable: Mantenerla alejada de ventanas abiertas en invierno o de radiadores/calefactores directos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'El sustrato ideal para la Calathea majestica debe ser ligero, aireado y con excelente drenaje, ya que esta planta no tolera el encharcamiento. Aquí los detalles específicos:\n" +
                "\n" +
                "Composición recomendada:\n" +
                "\n" +
                "40% turba o fibra de coco (retención de humedad sin compactarse).\n" +
                "\n" +
                "30% perlita o vermiculita (mejora la aireación y el drenaje).\n" +
                "\n" +
                "20% humus de lombriz o compost (aporte nutricional).\n" +
                "\n" +
                "10% corteza de pino o carbón vegetal (estructura y control de humedad).\n" +
                "\n" +
                "pH ideal: Ligeramente ácido, entre 5.5 y 6.5.\n" +
                "\n" +
                "Evitar: Sustratos pesados o muy compactos como tierra de jardín pura, que retienen demasiada agua y pueden provocar pudrición de raíces.\n" +
                "\n" +
                "Consejo extra: Asegúrate de que la maceta tenga buen drenaje en el fondo para que el exceso de agua pueda salir sin problemas.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'El riego de la Calathea majestica debe ser regular pero controlado, ya que es muy sensible tanto al exceso como a la falta de agua. Aquí te doy los detalles específicos:\n" +
                "\n" +
                "Frecuencia:\n" +
                "\n" +
                "Primavera y verano: Riega cuando la capa superior del sustrato (unos 2-3 cm) esté seca al tacto. Suele ser cada 4–6 días, dependiendo del clima.\n" +
                "\n" +
                "Otoño e invierno: Reduce la frecuencia a cada 7–10 días, pero siempre revisando el sustrato antes de regar.\n" +
                "\n" +
                "Cantidad: Riega de forma uniforme hasta que comience a salir un poco de agua por los orificios de drenaje. Luego, asegúrate de vaciar el plato o cachepot para evitar que las raíces queden en contacto con el agua.\n" +
                "\n" +
                "Tipo de agua: Usa preferiblemente agua filtrada, de lluvia o destilada, ya que es sensible al cloro y al exceso de sales minerales del agua del grifo. El agua a temperatura ambiente es ideal.\n" +
                "\n" +
                "Evita:\n" +
                "\n" +
                "Encharcar el sustrato (provoca pudrición de raíces).\n" +
                "\n" +
                "Dejarla secar completamente entre riegos (puede provocar hojas enrolladas o con bordes marrones).', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'El abonado de la Calathea majestica es clave para mantener su follaje sano y vibrante, pero debe hacerse con cuidado debido a su sensibilidad. Aquí los detalles específicos:\n" +
                "\n" +
                "Época de abonado:\n" +
                "Solo durante la primavera y el verano, que es su etapa de crecimiento activo. En otoño e invierno se suspende el abonado.\n" +
                "\n" +
                "Frecuencia:\n" +
                "Cada 3 a 4 semanas, dependiendo del tipo de fertilizante usado.\n" +
                "\n" +
                "Tipo de fertilizante:\n" +
                "Usa un abono líquido equilibrado y diluido, idealmente 10-10-10 o 20-20-20 (nitrógeno, fósforo, potasio), o uno específico para plantas verdes de interior.\n" +
                "Se recomienda diluirlo al 50% de la dosis indicada por el fabricante para evitar la sobre-fertilización.\n" +
                "\n" +
                "Alternativa natural:\n" +
                "También puedes usar humus líquido de lombriz o té de compost, que son más suaves y menos agresivos para las raíces.\n" +
                "\n" +
                "Evita:\n" +
                "\n" +
                "Fertilizantes con alto contenido en sales o de liberación rápida.\n" +
                "\n" +
                "Aplicar sobre sustrato seco, ya que puede quemar las raíces (riega ligeramente antes de abonar).', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'La poda en la Calathea majestica no es intensiva, pero sí importante para mantenerla saludable y estética. Aquí tienes cómo hacerlo correctamente:\n" +
                "\n" +
                "¿Qué podar?\n" +
                "\n" +
                "Hojas secas, amarillas o con bordes marrones: Córtalas desde la base, lo más cerca posible del sustrato.\n" +
                "\n" +
                "Hojas dañadas parcialmente: Puedes recortar solo la parte afectada si prefieres mantener la hoja, aunque no se regenerará.\n" +
                "\n" +
                "Frecuencia:\n" +
                "No hay una frecuencia fija; hazlo cuando veas hojas deterioradas o si deseas controlar su forma.\n" +
                "\n" +
                "Herramienta adecuada:\n" +
                "Usa tijeras limpias y afiladas (desinfectadas con alcohol) para evitar la propagación de enfermedades.\n" +
                "\n" +
                "Consejo extra:\n" +
                "Si notas muchas hojas secándose, revisa las condiciones de riego, luz y humedad, ya que la poda no soluciona el problema de fondo.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'El trasplante de la Calathea majestica es importante para renovar el sustrato y darle espacio a sus raíces. Aquí tienes cómo hacerlo correctamente:\n" +
                "\n" +
                "\uD83D\uDDD3 Frecuencia ideal\n" +
                "Cada 1 a 2 años, preferiblemente en primavera o principios de verano, cuando la planta está activa.\n" +
                "\n" +
                "También si notas raíces saliendo por los agujeros de drenaje o el sustrato se compacta y drena mal.\n" +
                "\n" +
                "\uD83E\uDEB4 Elección de maceta\n" +
                "Usa una maceta con orificios de drenaje.\n" +
                "\n" +
                "Elige una que sea solo 2–3 cm más grande de diámetro que la actual; una demasiado grande retiene más humedad y puede provocar pudrición.\n" +
                "\n" +
                "\uD83C\uDF31 Pasos para trasplantar\n" +
                "Riega ligeramente el día anterior para facilitar la extracción.\n" +
                "\n" +
                "Saca con cuidado la planta sin dañar las raíces.\n" +
                "\n" +
                "Revisa las raíces: corta las que estén podridas o negras con tijeras desinfectadas.\n" +
                "\n" +
                "Coloca una capa de drenaje (arcilla expandida o piedras pequeñas).\n" +
                "\n" +
                "Añade el sustrato adecuado (como el que te mencioné antes).\n" +
                "\n" +
                "Acomoda la planta y rellena alrededor, presionando suavemente para eliminar bolsas de aire.\n" +
                "\n" +
                "Riega ligeramente después del trasplante.\n" +
                "\n" +
                "⚠\uFE0F Precauciones\n" +
                "Evita trasplantar si la planta está débil o estresada.\n" +
                "\n" +
                "No fertilices justo después; espera unas 3–4 semanas para permitir que se adapte.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (200, 'La propagación de la Calathea majestica se realiza exclusivamente por división de rizomas, ya que no se propaga eficazmente por esquejes ni semillas en ambientes domésticos. Aquí te explico cómo hacerlo correctamente:\n" +
                "\n" +
                "\uD83E\uDDEA Método: División de rizomas\n" +
                "Ideal hacerlo durante el trasplante, en primavera o verano, cuando la planta está en crecimiento activo.\n" +
                "\n" +
                "\uD83D\uDD2A Pasos para propagar\n" +
                "Saca la planta con cuidado de la maceta.\n" +
                "\n" +
                "Retira el exceso de sustrato para exponer el sistema de raíces y rizomas (tallos subterráneos).\n" +
                "\n" +
                "Busca secciones con al menos 2–3 hojas y raíces propias. Separa con la mano o un cuchillo desinfectado.\n" +
                "\n" +
                "Planta cada división en su propia maceta con sustrato fresco (ligero, aireado y con buen drenaje, como te mencioné antes).\n" +
                "\n" +
                "Riega ligeramente después de plantar y mantén en un lugar con luz indirecta brillante y alta humedad.\n" +
                "\n" +
                "\uD83C\uDF3F Cuidados post-propagación\n" +
                "Mantén las nuevas plantas en ambiente húmedo y cálido (puedes cubrirlas con una bolsa plástica perforada durante los primeros días para conservar humedad).\n" +
                "\n" +
                "Evita fertilizar durante las primeras 3–4 semanas.\n" +
                "\n" +
                "⚠\uFE0F Ten en cuenta:\n" +
                "La Calathea majestica no tolera bien el estrés, así que no la dividas con demasiada frecuencia.\n" +
                "\n" +
                "Es normal que las divisiones se vean algo decaídas los primeros días.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Para la Calathea bella, la iluminación ideal es la siguiente:\n" +
                "\n" +
                "Luz indirecta brillante, como luz filtrada a través de una cortina o situada cerca de una ventana orientada al este o norte.\n" +
                "\n" +
                "Evita la luz solar directa, ya que puede quemar sus delicadas hojas y hacer que pierdan el color y la textura.\n" +
                "\n" +
                "Puede tolerar zonas con luz media a baja, pero en esos casos su crecimiento será más lento y los colores menos vivos.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Calatea Network muestra una sensibilidad moderada a las fluctuaciones de temperatura, tolerando un rango de 5℃ (41℉) a 43℃ (109℉). Esta adaptabilidad a la temperatura implica que calatea Network puede soportar algunas variaciones, pero aún requiere atención cuidadosa durante condiciones climáticas extremas. En condiciones de frío, puede mostrar amarillamiento o rizado de las hojas, mientras que un calor excesivo puede causar caos en las hojas o marchitamiento. Para contrarrestar el enfriamiento excesivo, utiliza materiales aislantes y mueve la planta a áreas más cálidas. Durante el sobrecalentamiento, asegúrate de proporcionar suficiente sombra y riego constante. Tales medidas aseguran la salud de calatea Network, haciendo que sea adecuada para ambientes templados con cuidados cuidadosos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Composición del suelo: Arena, Marga\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 5.5-6.5\n" +
                "\n" +
                "Calatea Network prospera en suelos bien drenados para prevenir la pudrición de raíces. Una mezcla de suelo adecuada incluye partes iguales de tierra para macetas, perlita y turba para una óptima aireación. Si la turba no está disponible, se puede sustituir por fibra de coco. El drenaje se puede mejorar agregando una capa de grava en la base de la maceta. Periódicamente, incorpore fertilizante de liberación lenta para apoyar el crecimiento, siguiendo las instrucciones del paquete. Esta mezcla asegura el equilibrio adecuado entre retención de humedad y drenaje para promover la salud de calatea Network.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Calatea Network prospera en ambientes que imitan su hábitat de selva tropical nativa, donde la alta humedad y la lluvia regular son la norma. Esta especie prefiere la humedad constante pero es tolerante si el riego se retrasa ocasionalmente. Debe regarse cada semana para mantener su exuberante follaje. Como planta de interior, calatea Network se beneficia enormemente de una mayor humedad que se puede proporcionar mediante rociado frecuente o colocándola en una bandeja de guijarros con agua para emular las condiciones húmedas que ama. En cuanto a los ciclos de crecimiento, el riego constante contribuye a los vibrantes patrones de hojas de calatea Network y a su salud general durante su temporada de crecimiento activa.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Utilice fertilizantes de nutrición equilibrada para calatea Network, aplicándolos cada cuatro semanas durante la temporada de crecimiento activa, en primavera y verano. Reduzca la frecuencia de alimentación a una vez cada seis u ocho semanas en otoño e invierno. Diluya el fertilizante a la mitad de su fuerza para prevenir quemaduras en las raíces y fomentar el desarrollo de un exuberante follaje. La sobre-fertilización puede provocar acumulación de sal, por lo que enjuague el suelo periódicamente con agua. Asegurarse de que calatea Network reciba la cantidad y tipo correctos de nutrientes apoyará sus vibrantes hojas con patrones y su crecimiento robusto.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Caracterizado por sus llamativos patrones de hojas y movimientos únicos, calatea Network se beneficia de una poda ocasional para mantener su apariencia exuberante. Realiza la poda a principios de la primavera, eliminando cualquier hoja amarillenta o dañada para promover un crecimiento saludable. Corta el tallo cerca de la base utilizando tijeras limpias y afiladas. La poda regular ayuda a mejorar la circulación del aire, previene enfermedades y fomenta un follaje más vibrante. Asegúrate de que la planta no esté expuesta a condiciones extremas después de la poda para una óptima recuperación.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Época de trasplante: Mediados de verano, Finales de verano\n" +
                "\n" +
                "El mejor momento para trasplantar calatea Network es durante la temporada de crecimiento desde su punto máximo hasta el final, asegurando una transición sin problemas. Elige un lugar sombreado y húmedo y mantén la consistencia del suelo para ayudar a que calatea Network prospere después del trasplante.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (210, 'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: División\n" +
                "\n" +
                "Calatea Network es una planta tropical reconocida por su impresionante follaje que presenta un patrón de red único. Para propagar calatea Network, es esencial una separación cuidadosa a nivel de la raíz debido a su naturaleza rizomatosa. Divida suavemente las raíces, asegurándose de que cada sección tenga una cantidad justa de estructura de raíces y follaje. Este método mantiene los intrincados patrones de hojas característicos de calatea Network, permitiendo que las nuevas plantas prosperen con humedad constante, calor y luz indirecta.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Para la Calathea roseopicta, la iluminación debe ser:\n" +
                "\n" +
                "Luz indirecta brillante, ideal para mantener sus colores intensos y el patrón característico de sus hojas.\n" +
                "\n" +
                "Evitar la luz solar directa, porque puede quemar las hojas y causar manchas marrones.\n" +
                "\n" +
                "Puede adaptarse a luz media, aunque con menos intensidad de colores y crecimiento más lento.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Calatea rosa (Little Princess) es moderadamente sensible a las fluctuaciones de temperatura. Toleran temperaturas tan bajas como 5℃ (41℉) y tan altas como 43℃ (109℉). Para cuidar de calatea rosa (Little Princess), evita la exposición a corrientes de aire frío y calor extremo. Los síntomas de estrés por frío incluyen el rizado de las hojas y los bordes marrones, mientras que el sobrecalentamiento causa marchitez y quemaduras en las hojas. Protege a calatea rosa (Little Princess) del frío utilizando materiales aislantes y mantenlo alejado de la luz solar directa o ambientes calurosos. Estas medidas aseguran que se mantenga saludable y vibrante.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Composición del suelo: Marga, Calcáreo, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 5.5-7\n" +
                "\n" +
                "Calatea rosa (Little Princess) prospera en un suelo bien drenado. Una mezcla de 2 partes de turba o fibra de coco, 1 parte de perlita y 1 parte de mezcla para macetas mejora la aireación. Si la perlita no está disponible, use pómez o arena gruesa. Asegúrese de que las macetas tengan agujeros de drenaje para evitar raíces aguadas. Modifique con fertilizante de liberación lenta anualmente, siguiendo las instrucciones del paquete para la dosificación. Mantenga estas condiciones para un crecimiento saludable.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Originario de las selvas tropicales, calatea rosa (Little Princess) prospera en alta humedad y condiciones de suelo consistentemente húmedo. Esta especie es conocida por su preferencia por la humedad equilibrada de forma uniforme sin estar encharcada. Para imitar su hábitat natural, calatea rosa (Little Princess) debe regarse cada semana. Como planta de interior, calatea rosa (Little Princess) se beneficia enormemente de la pulverización regular para mantener la humedad ambiental que anhela. Específicamente, durante su temporada de crecimiento activa, asegurar una humedad adecuada promoverá un follaje exuberante y patrones vibrantes.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Para una salud óptima, calatea rosa (Little Princess) se beneficia de un fertilizante líquido equilibrado a media fuerza cada cuatro semanas durante la temporada de crecimiento. La fertilización fomenta un crecimiento robusto y mantiene el follaje vibrante. Cese la fertilización en otoño e invierno para alinearse con el período de dormancia de calatea rosa (Little Princess). La sobrefertilización puede provocar acumulación de sales, así que enjuague el suelo periódicamente. Para una aplicación precisa, siga las instrucciones de la etiqueta del producto, asegurando un enfoque modesto ya que las raíces de calatea rosa (Little Princess) son sensibles a las quemaduras químicas.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Calatea rosa (Little Princess) es conocido por su follaje llamativo y patrones de hojas únicos. Pode esta planta a principios de primavera para fomentar un nuevo crecimiento y mantener su forma. Usa tijeras limpias y afiladas para recortar las hojas muertas o dañadas en la base y eliminar hojas amarillentas o antiestéticas para promover la salud general de la planta. Evita la poda excesiva, ya que calatea rosa (Little Princess) puede ser sensible; en su lugar, céntrate en un mantenimiento ligero y regular. La poda ayuda a mejorar la circulación de aire y la penetración de la luz, esenciales para un follaje vibrante.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Época de trasplante: Mediados de primavera, Finales de primavera, Principios de verano\n" +
                "\n" +
                "Calatea rosa (Little Princess) prospera mejor cuando se trasplanta desde mediados de primavera hasta principios del verano debido a condiciones de crecimiento ideales. Elija un lugar con sombra y suelo bien drenado. Asegúrese de que el área mantenga la humedad y el calor para una aclimatación exitosa.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (220, 'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: División\n" +
                "\n" +
                "Calatea rosa (Little Princess) es una variedad popular ampliamente apreciada por sus llamativos patrones de follaje. La propagación se realiza típicamente mediante división, donde los jardineros deben separar cuidadosamente los rizomas durante el trasplante. Al dividir, asegúrese de que cada sección tenga al menos un punto de crecimiento. Separe suavemente las raíces, minimizando el daño, y trasplante las divisiones en la mezcla de macetas adecuada. Después del trasplante, mantener una alta humedad y temperaturas cálidas ayudará a calatea rosa (Little Princess) a establecerse rápidamente.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Requisitos de luz solar: Sombra total\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial, Luz solar indirecta\n" +
                "\n" +
                "Bijao prospera mejor bajo condiciones de sombra total que proporcionan iluminación suave y difusa sin exposición directa al sol. Este nivel de luz optimiza el crecimiento de bijao y mantiene su follaje vibrante. Cuando se expone a una iluminación más allá de su sombra total preferida, bijao puede experimentar estrés que puede manifestarse en quemaduras en las hojas o pérdida de color. Los rasgos adaptativos de bijao, como sus hojas anchas, optimizan la absorción de luz en su hábitat natural del sotobosque. Idealmente, bijao debería ser plantado al aire libre en una posición donde pueda recibir amplia sombra durante todo el día, reflejando su entorno natural de poca luz para mantener su salud y crecimiento.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Bijao es bastante resistente, tolerando temperaturas de 5℃ (41℉) a 43℃ (109℉). Sin embargo, el frío extremo puede causar marchitez o acaramelamiento de las hojas, mientras que el calor excesivo puede llevar a la quemadura de las hojas y deshidratación. Durante extremos de temperatura, asegúrate de que bijao esté bien protegido, proporcionando suficiente agua y humedad en el calor, y protegiéndolo de las heladas con coberturas o colocándolo en interiores en el frío. Tal tolerancia a la temperatura lo convierte en una opción versátil, pero se debe tener cuidado de protegerlo de condiciones climáticas incontrolables. La adaptabilidad a climas variados ofrece flexibilidad en las configuraciones ambientales pero exige enfoques proactivos en temperaturas adversas.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Tipo de suelo: Mezcla para macetas\n" +
                "\n" +
                "pH del suelo: 5.5-7.5\n" +
                "\n" +
                "Bijao prospera en suelos bien drenados para prevenir el encharcamiento. Se puede hacer una mezcla adecuada con partes iguales de mezcla para macetas, perlita y corteza de orquídea para una buena aireación. Asegura un drenaje adecuado utilizando una maceta con agujeros. Mejora el crecimiento añadiendo un fertilizante de liberación lenta mensualmente durante la temporada de crecimiento. Evita el riego excesivo para proteger las raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Originario de la selva tropical húmeda, bijao prospera en entornos con humedad constante. Esta especie prefiere alta humedad y tiene tolerancia moderada a la sequía. Debe ser regado cada semana para mantener su frondoso follaje. Suele ser cultivado en interiores debido a sus hojas decorativas, bijao se beneficia de un rocío regular para replicar las condiciones húmedas de su hábitat natural, asegurando un crecimiento vibrante durante su temporada de crecimiento activa.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Para bijao, use fertilizantes con alto contenido de nitrógeno para apoyar el crecimiento exuberante del follaje. Fertilice mensualmente durante la primavera y el verano con una solución a media fuerza, reduciendo a cada seis semanas en otoño e invierno para alinearse con el crecimiento más lento de bijao. Evite la sobre-fertilización, que puede provocar acumulación de sales en el suelo. Revise los signos de deficiencia o exceso para ajustar las prácticas de fertilización para una salud óptima. La alimentación regular realza la vitalidad de bijao, contribuyendo al bienestar general de la planta.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Conocido por sus amplias y llamativas hojas, bijao se beneficia de la poda a principios de primavera. Retire cualquier follaje muerto o dañado para fomentar un crecimiento saludable y prevenir enfermedades. Evite cortar demasiado de una vez; en su lugar, recorte selectivamente para dar forma a la planta. Esta poda estacional no solo mejora la apariencia, sino que también facilita una mejor penetración de luz y circulación de aire, promoviendo el vigor general.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Época de trasplante: Finales de primavera, Principios de verano\n" +
                "\n" +
                "La temporada óptima para trasplantar bijao es durante el periodo comprendido entre finales de verano y principios de otoño (S3-S4), ya que el clima más fresco facilita la transición de la planta. Bijao prefiere un entorno de sombra parcial para un crecimiento próspero. Recuerde preparar siempre un hoyo más grande en comparación con el tamaño de su sistema radicular antes del trasplante para garantizar un trasplante satisfactorio.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (230, 'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: Siembra\n" +
                "\n" +
                "Originario de los bosques tropicales, bijao prospera en ambientes cálidos y húmedos. El éxito en la propagación depende de replicar estas condiciones. Comience sembrando las semillas en suelo bien drenado y con capacidad de retención de humedad dentro de un ambiente controlado y cálido. Emplee una rutina de rocío para mantener alta humedad, esto simula el hábitat natural de bijao. El calor suave desde abajo puede fomentar la germinación. Se requiere paciencia, ya que las plántulas pueden tardar en desarrollarse completamente. A medida que crezcan, adáptelas gradualmente a condiciones menos controladas antes de trasplantarlas.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Requisitos de luz solar: Sombra total\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Calatea prospera en sombra total, donde la intensidad de la luz está difuminada, protegiendo sus delicadas hojas del sol directo. La exposición constante a la luz indirecta fortalece su crecimiento y mantiene un follaje vibrante. Aunque calatea puede soportar sol parcial, demasiada luz directa puede llevar a hojas descoloridas y un crecimiento atrofiado. Para adaptarse, las hojas de calatea pueden rizarse o cambiar de color para minimizar el daño. Dentro de un entorno interior, una ventana orientada al norte o un lugar con luz filtrada es ideal. En exteriores, calatea debe estar en una zona sombreada, potencialmente bajo un dosel que imite su hábitat natural del sotobosque.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Calatea muestra una sensibilidad moderada a las fluctuaciones de temperatura. Puede soportar temperaturas desde 5℃ (41℉) hasta 43℃ (109℉). En condiciones más frías, por debajo de 5℃, calatea puede presentar enrollamiento o marrón en las hojas, mientras que temperaturas superiores a 43℃ podrían provocar escaldado y marchitamiento de las hojas. Cuidar implica mantener temperaturas estables en interiores, especialmente durante climas extremos. Para protección contra el frío, resguarda la planta de corrientes de aire y usa materiales aislantes. Durante períodos excesivamente calurosos, asegúrate de suficiente humedad y luz indirecta para prevenir el sobrecalentamiento. Adaptar las prácticas de cuidado a estas tolerancias de temperatura ayuda a mantener el follaje vibrante y la salud general de calatea.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Composición del suelo: Marga, Arena, Calcáreo, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6.1-6.5\n" +
                "\n" +
                "Calatea prospera con un suelo bien aireado y bien drenante. Una mezcla de suelo adecuada incluye partes iguales de tierra para macetas, turba y perlita o arena gruesa. La turba mantiene una ligera acidez y humedad, mientras que la perlita/arena garantiza un buen drenaje, crucial para prevenir el encharcamiento. Si la turba no está disponible, el coco es una alternativa viable. Mezcle regularmente un fertilizante equilibrado de liberación lenta según las instrucciones del producto para apoyar el crecimiento. Mantenga el drenaje con una estructura de suelo suelto y usando macetas con agujeros de drenaje.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad alta\n" +
                "\n" +
                "Originaria del sotobosque de los bosques tropicales, calatea prospera en alta humedad y humedad constante. Esta especie está adaptada a entornos donde el agua está fácilmente disponible pero bien drenada, evitando condiciones encharcadas. El riego debe hacerse una vez por semana para imitar el ciclo de hidratación de su hábitat natural. Calatea prefiere luz indirecta y un suelo consistentemente húmedo, lo que la hace más comúnmente cultivada en interiores donde se pueden controlar estas condiciones. En entornos interiores, asegurar una humedad adecuada es clave para que el follaje de calatea mantenga sus patrones vibrantes.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Nutra calatea cada 4 semanas con un fertilizante equilibrado, soluble en agua y rico en nitrógeno durante su temporada de crecimiento activa (primavera y verano). No más de 1/4 de la fuerza recomendada para evitar quemaduras y acumulación de fertilizante. En otoño e invierno, reduzca a cada 6-8 semanas para coincidir con el crecimiento más lento de calatea. La fertilización promueve el follaje vibrante de calatea, favoreciendo un crecimiento exuberante y saludable. Siempre riegue calatea antes de alimentarlo para proteger las raíces. El lavado periódico del suelo evitará la acumulación de sales de los fertilizantes.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Calatea, conocido por su impresionante follaje, prospera mejor cuando se poda a principios de primavera. Durante este tiempo, retira cualquier hoja muerta o dañada para fomentar un crecimiento más saludable. Recortar tallos desgarbados mejora la forma y densidad. Utiliza herramientas limpias y afiladas para evitar infecciones. La poda regular no solo mantiene la estética, sino que también promueve un follaje robusto y vibrante. Este enfoque asegura que calatea siga siendo una adición impresionante en cualquier jardín interior.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Época de trasplante: Finales de primavera, Verano\n" +
                "\n" +
                "El mejor momento para trasplantar calatea es entre finales de primavera y finales de verano, cuando disfruta de abundante calor y luz solar. Elija un lugar con luz brillante e indirecta y un suelo que drene bien. Tenga especial cuidado de no dañar las raíces durante el trasplante para que calatea prospere.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (240, 'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: División\n" +
                "\n" +
                "Para propagar calatea, es mejor usar la división durante la primavera o el otoño. Este método es relativamente fácil, con signos de éxito que incluyen un nuevo crecimiento de hojas. Separe cuidadosamente los grupos de raíces para garantizar una propagación saludable.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Requisitos de luz solar: Sombra total\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Verde para siempre prospera en sombra total, donde la luz es suave y difusa, protegiendo su delicado follaje de los rayos directos del sol. La exposición prolongada a la luz suave durante todo el día imita su hábitat natural de sotobosque, promoviendo un crecimiento vibrante y manteniendo patrones de hojas ricos. Aunque verde para siempre puede soportar sol parcial, se debe evitar el sol directo o excesivo para prevenir hojas desteñidas y quemaduras. Cuando se cultiva en interiores, verde para siempre prefiere ventanas con orientación norte o lugares con luz filtrada. En exteriores, verde para siempre prospera cuando se planta bajo el dosel de plantas más grandes, asegurando tanto protección contra los rayos fuertes como acceso a la calidad de luz adecuada para su salud y desarrollo.',1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 43 ℃\n" +
                "\n" +
                "Verde para siempre presenta una notable resistencia a las temperaturas, soportando fríos de hasta 5℃ (41℉) y calores de hasta 43℃ (109℉). Sin embargo, es sensible a las fluctuaciones extremas. En condiciones frías, verde para siempre puede mostrar hojas rizadas o bordes marrones. En exceso de calor, se espera quemaduras en las hojas y marchitez. Para el cuidado, es crucial proteger a verde para siempre de las heladas y el calor intenso. Proporciona aislamiento o muévelo al interior durante las olas de frío. Durante el calor extremo, asegúrate de que tenga suficiente hidratación y sombra. Esta adaptabilidad hace que verde para siempre sea adecuado para entornos variados, con las acciones protectoras necesarias que aseguran su longevidad y salud.',2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Composición del suelo: Marga, Calcáreo, Arcilla\n" +
                "\n" +
                "Tipo de suelo: Mezcla para macetas, Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 5.5-6.5\n" +
                "\n" +
                "Verde para siempre prospera en una mezcla de tierra bien drenante. Combine dos partes de turba o fibra de coco, una parte de perlita y una parte de corteza de orquídea para promover la aireación. Si la perlita no está disponible, use pómice o vermiculita. Asegúrese de contar con un drenaje con una maceta que tenga agujeros en la base. Agregue periódicamente un fertilizante líquido equilibrado, siguiendo las instrucciones de la etiqueta, para aumentar los niveles de nutrientes sin compactar la tierra.',3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad alta\n" +
                "\n" +
                "Verde para siempre prospera en un ambiente húmedo, con su hábitat nativo siendo las selvas tropicales. Está bien adaptado para mantener un equilibrio relativo de contenido de agua, mostrando ninguna tolerancia particular a la sequía. Regar cada semana es lo mejor para esta planta, manteniéndola exuberante y vibrante. Siendo una planta perenne a menudo cultivada en interiores, verde para siempre prefiere un suelo húmedo que imite sus condiciones naturales de la selva tropical.',4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Para el crecimiento robusto de verde para siempre, utilice fertilizante rico en nitrógeno para fomentar un follaje exuberante, aplicándolo bimensualmente durante la primavera y el verano. Utilice la mitad de la dosis recomendada, ya que el exceso puede dañar las delicadas raíces. Cese en otoño e invierno para coincidir con el período de descanso de verde para siempre. Reintroduzca gradualmente en primavera. Aplique durante el riego para una distribución uniforme y asegúrese de que el suelo esté húmedo para evitar quemaduras en las raíces. Esta alimentación específica sostiene la vitalidad y salud de verde para siempre.',5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Época de poda: Principios de primavera, Finales de invierno\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Verde para siempre exhibe hojas con patrones strikingly. Las técnicas clave de poda implican eliminar hojas dañadas o amarillentas para mantener su apariencia y salud. El momento óptimo para podar es a principios de primavera, alineándose con su ciclo de crecimiento. Poda en este momento fomenta un nuevo crecimiento vigoroso. Consideraciones específicas incluyen el uso de herramientas afiladas y desinfectadas para prevenir enfermedades, y mantener la humedad después de la poda. La poda regular promueve una mejor circulación del aire, reduciendo riesgos de plagas y enfermedades, y fomenta una planta más llena y vibrante.',6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Época de trasplante: Verano\n" +
                "\n" +
                "Para obtener los mejores resultados, trasplante verde para siempre durante los meses cálidos y cautivadores de principios a finales del verano, cuando la planta experimenta un crecimiento activo. Busque un lugar bien iluminado y uniformemente húmedo para favorecer el éxito, y tenga cuidado con las delicadas raíces durante el trasplante. ¡Feliz jardinería!',7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (250,'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: División\n" +
                "\n" +
                "La temporada ideal de propagación para verde para siempre es durante la primavera y el otoño, utilizando la división. Esta planta tiene una dificultad moderada de propagación. La propagación exitosa se indica con un nuevo crecimiento. Mantenga las divisiones bien regadas y sombreadas inicialmente.',8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Boca del dragón prospera mejor bajo la luz solar plena, lo que apoya óptimamente su crecimiento, floración y salud general. Aunque principalmente requiere exposición a luz directa e intensa durante la mayor parte del día, boca del dragón muestra una notable tolerancia a condiciones de sol parcial. En condiciones de luz subóptimas, como sombra parcial, puede haber una disminución en su vigor y su capacidad para florecer de manera robusta. Adaptativamente, boca del dragón puede ajustar la orientación de sus hojas para maximizar la absorción de luz, mejorando su supervivencia en escenarios de luz menos que ideales. Se recomienda para la siembra al aire libre, boca del dragón se posiciona idealmente en áreas soleadas o parcialmente sombreadas, asegurando una penetración de luz solar suficiente para un desarrollo óptimo.',1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Temperatura ideal: 20 - 32 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 35 ℃\n" +
                "\n" +
                "Boca del dragón muestra una notable resistencia a las variaciones de temperatura, tolerando el frío hasta 0℃ (32℉) y el calor hasta 35℃ (95℉). Esta adaptabilidad hace que boca del dragón sea adecuado para diversos entornos, aunque puede requerir medidas de protección durante condiciones climáticas extremas. Bajo estrés por frío, boca del dragón puede mostrar síntomas como decoloración de las hojas y crecimiento lento, mientras que un calor excesivo podría causar marchitez o quemaduras en las hojas. Para mitigar el enfriamiento excesivo, cubra a boca del dragón con telas para heladas o muévala al interior. Por el contrario, durante el sobrecalentamiento, asegúrese de un riego adecuado y proporcione sombra. Comprender estas tolerancias a la temperatura es crucial para asegurar que boca del dragón prospere en sus condiciones de cultivo.',2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Para la Boca de dragón (Antirrhinum majus), el sustrato ideal es:\n" +
                "\n" +
                "Suelo bien drenado, suelto y rico en materia orgánica. No tolera suelos encharcados.\n" +
                "\n" +
                "Mezcla recomendada:\n" +
                "\n" +
                "50–60% tierra para macetas fértil,\n" +
                "\n" +
                "20–30% compost o humus de lombriz (nutrición),\n" +
                "\n" +
                "10–20% perlita, arena gruesa o vermiculita (mejor drenaje).\n" +
                "\n" +
                "pH óptimo: Ligeramente ácido a neutro, entre 6.0 y 7.0.\n" +
                "\n" +
                "Consejo extra: Si cultivas en jardín, asegúrate de que el suelo esté bien labrado y no se compacte, especialmente tras lluvias.',3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Originario de los bosques sombríos, boca del dragón prospera en condiciones de suelo moderadamente húmedo que imitan su entorno natural de sotobosque. Prefiere la humedad constante pero puede tolerar breves períodos de sequedad. Para una salud óptima, se debe regar boca del dragón cada semana, asegurando un equilibrio entre la hidratación y el drenaje para prevenir la pudrición de raíces. Como especie de sotobosque comúnmente cultivada al aire libre, boca del dragón se beneficia del acolchado para retener la humedad del suelo y simular la hojarasca de su hábitat nativo, promoviendo un ciclo de crecimiento natural.',4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Para boca del dragón, fertilice con moderación con un fertilizante alto en fósforo para impulsar la floración. Aplique una vez al comienzo de la temporada de crecimiento; frecuente puede dañar las delicadas raíces. Esencial para la vitalidad y la producción de flores, la fertilización correcta mejora la salud de boca del dragón sin estimular un crecimiento excesivo de follaje. Los ajustes estacionales no son necesarios, ya que boca del dragón no es un gran consumidor de nutrientes. Utilice la dosis recomendada; la sobre-fertilización puede dañar la planta. Asegure una distribución uniforme para prevenir quemaduras en las raíces. Un enfoque informado y moderado para la fertilización sostiene la vitalidad natural de boca del dragón.',5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Boca del dragón es una hierba semi-parásita conocida por sus coloridas flores tubulares. Para una salud y crecimiento óptimos, poda boca del dragón de principios a finales de primavera, asegurando eliminar cualquier tallo muerto o dañado. Recorta las secciones crecidas en exceso para mantener la forma y fomentar una floración vigorosa. La poda también ayuda a gestionar su propagación, ya que boca del dragón puede volverse invasiva. Siempre utiliza herramientas limpias y afiladas para prevenir la transmisión de enfermedades.',6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Época de trasplante: Primavera\n" +
                "\n" +
                "Para los boca del dragón, la ventana desde el abrazo rejuvenecedor de la primavera temprana hasta la floración verde de finales de primavera es óptima para el trasplante, asegurando su establecimiento en un sitio parcialmente sombreado con un suelo bien drenado. Recuerde tener en cuenta el carácter amigable del suelo: suelto y fértil es lo mejor para los boca del dragón.',7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (320,'Época de propagación: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "Tipo de propagación: Siembra\n" +
                "\n" +
                "Boca del dragón prospera cuando sus semillas se siembran en un suelo bien drenado con moderada humedad y sombra parcial a completa, imitando su hábitat nativo de sotobosque. Estas semillas tienen un letargo natural que requiere condiciones específicas para romperse, asegurando así que germinen en el momento adecuado. Para lograr una germinación exitosa, un proceso de estratificación que simula las condiciones invernales puede ser útil. Esto implica enfriar las semillas durante varias semanas, lo que los jardineros pueden hacer colocándolas en un refrigerador antes de sembrarlas. Una vez sembradas, proporcionar una humedad constante y protección del sol directo apoyará la germinación y el crecimiento de las plantas jóvenes.',8)")

        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Clavel lanudo prospera mejor a pleno sol, requiriendo al menos seis horas de luz solar directa cada día para promover un crecimiento robusto y una floración vibrante. Aunque clavel lanudo tiene una buena tolerancia a la luz solar parcial, recibir menos de la luz solar ideal puede llevar a una disminución de la floración y tallos más débiles. En términos de adaptabilidad, clavel lanudo no posee rasgos únicos significativos que se ajusten a diferentes exposiciones a la luz. Para la siembra al aire libre, clavel lanudo debe ser colocado en áreas que aseguren una exposición adecuada a la luz solar, como camas abiertas o pendientes orientadas al sur, para maximizar su salud y atractivo estético.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Temperatura ideal: 20 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 38 ℃\n" +
                "\n" +
                "Clavel lanudo demuestra una notable resistencia a las fluctuaciones de temperatura, tolerando el frío hasta 0℃ (32℉) y el calor hasta 38℃ (100℉). Esta tolerancia lo hace adecuado para una variedad de climas, pero requiere medidas de protección en condiciones extremas. En clima frío, clavel lanudo puede mostrar un crecimiento ralentizado o daños por heladas, mientras que el calor excesivo puede provocar marchitez y quemaduras en las hojas. Para mitigar estos problemas, se debe colocar mantillo alrededor de la base de la planta durante las olas de frío y proporcionar sombra e riego adecuado en el intenso calor. Estas adaptaciones aseguran la salud y longevidad de clavel lanudo a pesar de las temperaturas extremas.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Composición del suelo: Arena, Calcáreo, Arcilla, Marga\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-7.5\n" +
                "\n" +
                "Clavel lanudo prospera en suelos de jardín bien drenados. Asegura un buen drenaje mezclando arena o perlita para evitar el encharcamiento. Una mezcla adecuada se puede preparar con 1 parte de suelo de jardín, 1 parte de arena/perlita y 1 parte de compost. Agrega un fertilizante de liberación lenta durante la siembra para mejorar el crecimiento. Evita condiciones de encharcamiento utilizando macetas con agujeros de drenaje o creando camas de jardín elevadas.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Originario del Mediterráneo, clavel lanudo prospera en suelos bien drenados y puede tolerar condiciones secas. Su tolerancia a la sequía es notable, requiriendo riego cada semana para mantener su salud sin sobre-saturación. Transicionando suavemente desde sus paisajes nativos áridos, clavel lanudo prefiere un equilibrio de humedad pero perdonará lapsos ocasionales en los horarios de riego. Como planta exterior a menudo encontrada en jardines templados, la capacidad de prosperar de clavel lanudo depende de niveles consistentes de humedad durante su temporada de crecimiento activa para apoyar su florecimiento vibrante.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Para mantener los vibrantes flores de clavel lanudo, aplique un fertilizante balanceado cada 4-6 semanas durante el crecimiento activo. La fertilización fomenta una salud robusta y una floración prolífica. Utilice una mezcla 10-10-10, con moderación, ya que demasiado puede dañar a clavel lanudo. En la dormancia, deje de alimentar para permitir el descanso. Ajuste los horarios para cambios estacionales; más en el auge de crecimiento de primavera, menos a medida que el otoño se acerca. Siempre riegue el suelo antes de fertilizar para evitar quemaduras en las raíces. Este enfoque personalizado asegura que clavel lanudo prospere con vitalidad óptima.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Clavel lanudo es una planta perenne conocida por sus vibrantes flores magenta y su follaje plateado. Poda clavel lanudo de principios a finales de primavera para promover un crecimiento saludable. Retira las flores marchitas y recorta los tallos para fomentar una segunda floración. Corta cualquier follaje muerto o dañado para mejorar la circulación del aire y prevenir enfermedades. La poda regular ayuda a mantener la forma compacta de la planta y una floración prolífica. Asegúrate de usar herramientas limpias y afiladas para evitar el estrés en la planta.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Época de trasplante: Principios de primavera, Mediados de primavera, Mediados de otoño, Finales de otoño\n" +
                "\n" +
                "El momento ideal para trasplantar clavel lanudo es a principios o mediados de primavera y a mediados o finales de otoño, ya que estas estaciones favorecen un crecimiento sano de las raíces. Seleccione un lugar soleado o parcialmente sombreado y deje espacio suficiente para el crecimiento. Manipule con cuidado clavel lanudo durante el proceso de trasplante para evitar daños en las raíces y garantizar unos resultados óptimos.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (330, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División, Siembra\n" +
                "\n" +
                "El método preferido de propagación para clavel lanudo es por esquejes, idealmente realizado en primavera o verano. El proceso es moderadamente fácil, con signos exitosos que incluyen un nuevo crecimiento de hojas. Asegure niveles adecuados de humedad para resultados óptimos.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Neguilla prospera mejor bajo luz solar directa, lo que optimiza su crecimiento, salud y mejora sus capacidades de floración. Aunque neguilla puede tolerar el sol parcial, niveles de luz reducidos pueden obstaculizar su crecimiento robusto y resultar en menos flores. Idealmente, esta planta florece en áreas abiertas donde puede recibir luz solar ininterrumpida durante la mayor parte del día. Evita plantar neguilla en áreas demasiado sombreadas, ya que puede llevar a un desarrollo subóptimo y aumentar la susceptibilidad a enfermedades. Al aire libre, neguilla debe estar posicionado para captar la máxima luz para un rendimiento óptimo.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Temperatura ideal: 20 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 41 ℃\n" +
                "\n" +
                "Neguilla exhibe una notable resiliencia a las variaciones de temperatura, tolerando rangos desde 0ºC (32ºF) hasta 41ºC (106ºF). Esta amplia tolerancia significa que neguilla puede soportar extremos de frío y calor, aunque requiere cuidados específicos en tales condiciones. Durante oleadas de frío, neguilla puede presentar síntomas como decoloración o marchitamiento de las hojas, mientras que el estrés por calor puede llevar a quemaduras en las hojas y una reducción de la floración. Para mitigar el sobreenfriamiento, utiliza coberturas contra heladas o mueve los ejemplares en macetas al interior. Para el sobrecalentamiento, proporciona sombra y asegura un riego adecuado. Estas medidas mejoran la adaptabilidad de neguilla, haciéndola adecuada para diversos entornos, aunque es crucial prestar atención a las acciones protectoras durante los extremos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Composición del suelo: Arena, Marga, Arcilla, Calcáreo\n" +
                "\n" +
                "Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-7.5\n" +
                "\n" +
                "En su hábitat natural, neguilla crece en suelos bien drenados con un pH de 6.0 a 7.5, ligeramente ácidos a neutros. La absorción de nutrientes es óptima dentro de este rango, promoviendo un crecimiento saludable. Neguilla prospera mejor en una composición de suelo arenoso o limoso, enriquecido con materia orgánica. Para una mezcla de suelo ideal, combina 50% de tierra de jardín, 30% de arena y 20% de Compost. Asegura un buen drenaje agregando perlita o grava fina. Para mejorar el crecimiento, aplica un fertilizante equilibrado y multiusos cada 4-6 semanas durante la temporada de crecimiento. Las condiciones adecuadas del suelo previenen el encharcamiento y las deficiencias de nutrientes.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Originaria de regiones templadas, neguilla está adaptada a niveles moderados de humedad en su suelo. Esta especie prospera con una hidratación constante pero puede soportar breves períodos de sequía, presentando un requerimiento equilibrado de agua. Prefiere ser regada cada semana para mantener su salud y vigor. Por lo general, se cultiva al aire libre debido a su naturaleza resistente, neguilla se alinea bien con climas templados donde los patrones de lluvia apoyan su ciclo de crecimiento sin necesidad de un riego adicional excesivo.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Proporcionar a neguilla fertilizantes ricos en fósforo fomenta un desarrollo radicular robusto y flores vibrantes. Para obtener resultados óptimos, aplique un fertilizante líquido equilibrado diluido a la mitad cada 4-6 semanas durante la temporada de crecimiento. Evite la sobre-fertilización que puede dañar el crecimiento de neguilla. Los ajustes estacionales en la alimentación son cruciales; reduzca la frecuencia en la fase invernal dormante. Consejo práctico: riegue neguilla antes de fertilizar para prevenir quemaduras en las raíces. Adaptadas para una experiencia de jardinería variada, estas pautas garantizan el florecimiento de neguilla.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Neguilla es una planta anual conocida por sus vibrantes flores de color rosa-púrpura y tallos delgados. Para un crecimiento óptimo, recorta neguilla a principios o finales de la primavera para fomentar un nuevo crecimiento sano. Enfócate en eliminar cualquier tallo muerto o dañado y recorta el crecimiento más viejo para prevenir el hacinamiento. Esto promueve una mejor circulación de aire y reduce el riesgo de enfermedades. La poda adecuada mejora la floración y la salud general de la planta, asegurando una exhibición más vigorosa y atractiva en el jardín.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Época de trasplante: Principios de primavera\n" +
                "\n" +
                "La época óptima de trasplante de neguilla es durante su estación favorita, /S1/. Este período garantiza el crecimiento más robusto. Elija un lugar con exposición a pleno sol para esta especie amante del sol. Reafirmar suavemente la tierra alrededor de su base durante el trasplante puede favorecer el establecimiento de las raíces. Asegúrese de regar adecuadamente, pero evite el riego excesivo.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (340, 'Época de propagación: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "Tipo de propagación: Siembra\n" +
                "\n" +
                "Una cautivadora flor silvestre, neguilla suele propagarse sembrando las semillas directamente en el suelo. Para un crecimiento exitoso, elija un sitio bien drenado con exposición completa al sol. Prepare el suelo aflojándolo y creando una capa fina. Esparcir las semillas de manera uniforme y cubrirlas ligeramente con tierra, ya que requieren un buen contacto pero poca profundidad para germinar efectivamente. El riego regular, especialmente durante períodos de sequía, apoyará la germinación y el establecimiento de plántulas. Neguilla se beneficia de aclareo temprano, permitiendo que las plantas restantes tengan suficiente espacio para desarrollarse.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Conejito de los muros prospera en condiciones de pleno sol, donde recibe luz directa e intensa durante la mayor parte del día. Esta exposición es esencial para un crecimiento robusto y una salud óptima. Sin embargo, la planta también puede tolerar sol parcial, lo que la hace adaptable a lugares donde la luz es ligeramente difusa o donde hay períodos de sombra. Las desviaciones de sus necesidades ideales de luz pueden resultar en un crecimiento más lento y una capacidad de floración disminuida. La follaje de conejito de los muros puede mostrar signos de adaptación como ligeras variaciones de color al transitar entre diferentes niveles de luz. En exteriores, conejito de los muros debe ser plantado en lugares donde la luz solar es abundante para lograr el mejor rendimiento, asegurando algo de protección contra el sol intenso de la tarde en climas más cálidos es beneficioso.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Temperatura ideal: 20 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 38 ℃\n" +
                "\n" +
                "Conejito de los muros demuestra una notable resistencia a los cambios de temperatura, tolerando un rango de -5℃ (23℉) a 35℃ (95℉). Esta adaptabilidad lo hace adecuado para una variedad de climas, aunque son esenciales medidas de protección durante el clima extremo. Los síntomas de frío excesivo incluyen marchitamiento y decoloración de las hojas, mientras que el sobrecalentamiento puede causar crecimiento lento y hojas quemadas. Para mitigar el enfriamiento excesivo, use mantas para heladas o mueva la planta adentro; para el sobrecalentamiento, asegúrese de un riego adecuado y sombra. Tales medidas ayudarán a mantener la salud de conejito de los muros a través de diversas condiciones ambientales.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Para los Conejitos de los Muros (Saxifraga stolonifera), el sustrato ideal es:\n" +
                "\n" +
                "Ligero, bien drenado y aireado para evitar el encharcamiento, que puede pudrir las raíces.\n" +
                "\n" +
                "Una mezcla recomendada puede ser:\n" +
                "\n" +
                "50% tierra para macetas rica en materia orgánica,\n" +
                "\n" +
                "30% perlita o arena gruesa para mejorar el drenaje,\n" +
                "\n" +
                "20% turba o fibra de coco para retener algo de humedad sin encharcar.\n" +
                "\n" +
                "pH: Prefiere un sustrato ligeramente ácido a neutro, entre 6.0 y 7.0.\n" +
                "\n" +
                "Evita sustratos compactos o con mal drenaje, que pueden provocar enfermedades radiculares.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Conejito de los muros es una planta que prospera en áreas con lluvias moderadas, prefiriendo condiciones de humedad uniforme. Tiene un requisito de agua relativamente equilibrado y puede tolerar breves periodos de sequedad. Regar cada semana mantendrá esta planta saludable y vibrante. Siendo una hierba anual, conejito de los muros experimenta su ciclo de crecimiento durante la época de lluvias, por lo que es esencial imitar estas condiciones al cultivarla en interiores o exteriores.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Para conejito de los muros, utilice fertilizantes de nutrición equilibrada con refuerzos ocasionales ricos en nitrógeno para promover el crecimiento y la floración. La alimentación mensual durante el crecimiento activo, con una frecuencia reducida en la inactividad, se alinea con el ciclo de vida de conejito de los muros. Aplique dosis al 25% para evitar quemaduras por exceso de nutrientes, asegurando una salud vibrante. Los ajustes estacionales y las cantidades cuidadosamente medidas son fundamentales. La dilución precisa y la orientación a la zona de raíces benefician enormemente a conejito de los muros. Consejo de seguridad: use guantes protectores durante la aplicación.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (350, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Conejito de los muros, conocido por sus delicadas flores rosadas y hojas similares a helechos, se beneficia de la poda en la primavera temprana a tardía. Recorte las flores marchitas y los tallos largos para fomentar un crecimiento más abundante y prolongar la floración. Elimine el follaje muerto o enfermo para mantener la salud de la planta. La poda regular ayuda a controlar el hábito natural de expansión de conejito de los muros y proporciona una forma más compacta. Siempre use herramientas afiladas y limpias para prevenir la propagación de enfermedades. Estas técnicas aseguran una exhibición vibrante y bien cuidada en el jardín.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Época de trasplante: Primavera\n" +
                "\n" +
                "Trasplanta conejito de los muros durante la amable acogida de los meses más suaves, desde el despertar de la primavera temprana hasta el amanecer del umbral del verano. Elige un lugar bañado por el sol con un suelo bien drenado para fomentar una transición sin problemas, encarnando la resistencia de la naturaleza.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Época de propagación: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "Tipo de propagación: Siembra\n" +
                "\n" +
                "Conejito de los muros se cultiva mediante técnicas de siembra directa con un enfoque en lograr condiciones de suelo óptimas. El suelo debe ser bien drenado y fértil, con preferencia por niveles de pH ligeramente alcalinos a neutros. La siembra debe realizarse en un área donde conejito de los muros reciba luz solar adecuada, asegurando que las semillas estén ligeramente cubiertas con tierra para protección. La clave para un crecimiento exitoso implica mantener una humedad constante sin encharcamiento, acompañada de un manejo suave durante el proceso de siembra para evitar dañar las delicadas semillas. La vigilancia regular de plagas y enfermedades contribuirá al establecimiento saludable de conejito de los muros.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Requisitos de luz solar: A pleno sol\n" +
                "\n" +
                "Tolerancia a la luz solar: Sol parcial\n" +
                "\n" +
                "Clavel de poeta prospera en condiciones donde puede disfrutar del pleno sol, asegurando un crecimiento robusto y una floración prolífica. La intensidad y la duración ininterrumpida de la luz juegan un papel crucial en la salud de la planta. Aunque disfruta de un buen baño de sol, clavel de poeta también posee una tolerancia admirable para entornos de sol parcial. En tales condiciones, aunque las flores pueden ser menos abundantes, la planta mantiene su vitalidad. Adaptable por naturaleza, clavel de poeta puede ajustar su follaje para optimizar la absorción de luz. Al ser cultivada al aire libre, un lugar que reciba abundante luz matutina es ideal. Para los cultivadores de interior, colocar a clavel de poeta cerca de una ventana soleada simulará su hábitat preferido, fomentando su encanto vibrante y sus flores coloridas.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Temperatura ideal: 20 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 38 ℃\n" +
                "\n" +
                "Clavel de poeta demuestra una notable resistencia a un amplio rango de temperaturas, tolerando frío de hasta 0℃ (32℉) y calor de hasta 38℃ (100℉). Esta adaptabilidad hace que clavel de poeta sea adecuada para varios climas, aunque las temperaturas extremas requieren atención. En condiciones frías, por debajo de 0℃, clavel de poeta puede mostrar marchitez o decoloración, mientras que el calor por encima de 38℃ puede causar quemaduras en las hojas y una reducción en la floración. Las medidas protectoras, como el acolchado en climas fríos y proporcionar sombra o riego durante el intenso calor, son cruciales para su cuidado. Asegurarse de una adecuada aclimatación y supervisión puede prevenir el estrés y mantener a clavel de poeta prosperando en diversos entornos.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Composición del suelo: Marga, Arena, Calcáreo, Arcilla, Franco-arenoso\n" +
                "\n" +
                "pH del suelo: 6-7\n" +
                "\n" +
                "Clavel de poeta prefiere ser plantado en suelos bien drenados y fértiles. Un suelo neutro a alcalino como la tiza o el limo es el mejor para su crecimiento. La tolerancia de la planta a la sequía la hace popular entre los jardineros principiantes, ya que puede sobrevivir con un esfuerzo mínimo o un régimen estricto de riego.\n" +
                "Sin embargo, no florecerá en suelos con mal drenaje. Por esta razón, es mejor agregar un drenaje extra en forma de grava hortícola o arena a su suelo si está jardinando en un área con suelo arcilloso pesado o donde el nivel freático es particularmente alto y es probable que el suelo se humedezca y compacte durante los meses de invierno.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Originaria de los bordes de los bosques de Europa, clavel de poeta prospera en condiciones que imitan su entorno nativo con humedad moderada. Se ha adaptado para ser relativamente tolerante a la sequía pero aprecia una hidratación constante, alineándose con el riego semanal. El cultivo debe reflejar un equilibrio entre permitir que el suelo se seque ligeramente y mantener una ligera humedad. Normalmente cultivada al aire libre debido a su naturaleza floreciente, clavel de poeta se beneficia enormemente del agua de lluvia durante su temporada de crecimiento activa, lo que promueve flores robustas y follaje saludable.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Para un crecimiento saludable, clavel de poeta prospera con fertilizantes equilibrados (como una mezcla 10-10-10) en su temporada de crecimiento, de primavera a verano. La aplicación periódica cada 4-6 semanas promueve flores robustas. Durante la siembra, una mezcla alta en fósforo garantiza un desarrollo radicular sólido. Aplique con moderación; la sobre-fertilización puede dañar a clavel de poeta. Ajuste la fertilización en la inactivad, reduciendo la frecuencia. Use con cuidado; una cuarta taza por yarda cuadrada es suficiente. Es fundamental evitar el contacto del fertilizante con el follaje para prevenir quemaduras.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Esta planta bienal cuenta con racimos de flores vibrantes. Para un crecimiento óptimo, pode clavel de poeta a principios o finales de la primavera. Elimine las flores marchitas y los tallos muertos para promover un crecimiento más saludable y más flores. Recorte hasta un par de hojas o yemas saludables. La poda no solo mantiene la forma, sino que también previene enfermedades. El mantenimiento regular fomenta un crecimiento renovado y ciclos de floración. Consultar pautas hortícolas confiables asegura un cuidado efectivo.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Época de trasplante: Mediados de primavera, Finales de primavera, Principios de verano\n" +
                "\n" +
                "El momento perfecto para trasplantar clavel de poeta es durante la transición de finales de primavera a principios de verano, ya que la planta prospera en climas más cálidos. Las condiciones ideales incluyen un lugar soleado con suelo bien drenado. Sea cuidadoso al trasplantar para garantizar un crecimiento satisfactorio.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (360, 'Época de propagación: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "Tipo de propagación: Corte, División, Siembra\n" +
                "\n" +
                "Una planta ornamental popular, clavel de poeta se propaga típicamente mediante esquejes para garantizar consistencia genética. Para un enraizamiento exitoso, use brotes sanos y sin flores. Corte secciones con al menos dos nudos y sumérjalas en hormona de enraizamiento para un desarrollo óptimo de las raíces. Plante en una mezcla de suelo bien drenado y mantenga una humedad constante sin encharcamiento. Proporcione luz indirecta y un calor suave para fomentar el crecimiento, asegurando un entorno estable que evite cambios extremos.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Requisitos de luz solar: Sol parcial\n" +
                "\n" +
                "Tolerancia a la luz solar: A pleno sol\n" +
                "\n" +
                "Clavel del aire prospera en luz brillante e indirecta, favoreciendo entornos que reciban los beneficios de la exposición parcial al sol. Es esencial para un crecimiento robusto y una floración prolífica. Sin embargo, clavel del aire también muestra una tolerancia admirable a pleno sol, un testamento a su resistencia adaptativa. La luz directa excesiva puede, sin embargo, causar quemaduras en las hojas o una apariencia descolorida, lo que potencialmente estanca el crecimiento. Cuando se cultiva en interiores, clavel del aire se adapta bien a ventanas orientadas al este o al oeste, aprovechando suficiente luz sin la dureza del sol de mediodía. En exteriores, prefiere sombra dappled bajo vegetación más alta, que imita su hábitat natural y promueve una salud óptima y desarrollo floral.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Clavel del aire demuestra una notable resistencia a las variaciones de temperatura, soportando el frío hasta 0℃ (32℉) y el calor hasta 43℃ (110℉). Esta adaptabilidad lo hace adecuado para una variedad de entornos, pero requiere cuidado durante el clima extremo. En condiciones frías, clavel del aire puede presentar hojas marrones o marchitas, mientras que el calor excesivo puede causar quemaduras en las puntas de las hojas y deshidratación. Para proteger a clavel del aire del frío, considera utilizar mantas térmicas o moverlo al interior. Para el calor, asegúrate de proporcionar una humedad adecuada y rociado ocasional. Esta tolerancia a la temperatura subraya la necesidad de una adaptabilidad situacional en la rutina de cuidado de clavel del aire, abordando de manera efectiva las fluctuaciones ambientales.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'pH del suelo: 6-7\n" +
                "\n" +
                "Las hojas de clavel del aire están cubiertas con una capa de células especiales que absorben agua. Pueden absorber humedad y nutrientes del aire o del polvo. Por lo tanto, la mayoría de las especies no necesitan suelo y pueden colgarse directamente en el aire como decoración en paredes, muebles, corcho y piedras.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Para un crecimiento saludable y flores vibrantes, fertilice clavel del aire quincenalmente en las temporadas de crecimiento con fertilizantes equilibrados y solubles en agua al 25%. Durante los períodos de reposo, reduzca la fertilización a una vez al mes. El exceso de fertilizante puede dañar clavel del aire, así que asegúrese de una dilución precisa. Antes de la aplicación, riegue clavel del aire para evitar quemaduras en las raíces. Los ajustes estacionales en la fertilización fortalecen la resistencia de clavel del aire y fomentan la floración.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Época de poda: Durante todo el año\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Conocido por su forma de roseta y sus vibrantes flores, clavel del aire se beneficia de la poda a principios de primavera hasta finales de invierno. Recorta las hojas muertas o dañadas con tijeras esterilizadas para promover la circulación de aire y reducir el riesgo de enfermedades. Eliminar las flores marchitas fomenta un nuevo crecimiento. La poda mínima ayuda a mantener su forma y tamaño natural, convirtiéndolo en un excelente candidato para exhibiciones artísticas. La poda regular apoya la salud y longevidad, asegurando una planta vibrante y atractiva.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Época de poda: Durante todo el año\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Conocido por su forma de roseta y sus vibrantes flores, clavel del aire se beneficia de la poda a principios de primavera hasta finales de invierno. Recorta las hojas muertas o dañadas con tijeras esterilizadas para promover la circulación de aire y reducir el riesgo de enfermedades. Eliminar las flores marchitas fomenta un nuevo crecimiento. La poda mínima ayuda a mantener su forma y tamaño natural, convirtiéndolo en un excelente candidato para exhibiciones artísticas. La poda regular apoya la salud y longevidad, asegurando una planta vibrante y atractiva.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Época de trasplante: Mediados de verano, Finales de verano, Principios del otoño\n" +
                "\n" +
                "Para clavel del aire, el mejor periodo para el trasplante es de mediados de primavera a mediados de verano. Esto proporciona a la planta el calor y la luz óptimos para adaptarse a su nueva ubicación. Considere un lugar con luz solar indirecta y condiciones de buen drenaje. Recuerde siempre evitar el estancamiento de agua, ya que podría provocar la pudrición de las raíces.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (370, 'Época de propagación: Principios de primavera, Mediados de primavera, Principios del otoño, Mediados de otoño\n" +
                "\n" +
                "Tipo de propagación: División, Siembra\n" +
                "\n" +
                "Clavel del aire florece mediante la división, donde se forman retoños llamados (pups) alrededor de la base de la planta madre. Estos retoños pueden separarse suavemente cuando alcanzan aproximadamente un tercio del tamaño de la planta progenitora. Retire cuidadosamente los retoños tirando de ellos o cortándolos con una cuchilla esterilizada, dejando suficiente de la base para garantizar su supervivencia. Permita que la superficie cortada se endurezca durante un día antes de colocarla en un medio ligero y aireado para enraizar, manteniendo una humedad alta y evitando el exceso de riego para un crecimiento óptimo.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Para la Margarita del Cabo (Osteospermum), la iluminación ideal es:\n" +
                "\n" +
                "Luz solar directa es fundamental para que florezca abundantemente y mantenga su forma compacta.\n" +
                "\n" +
                "Necesita al menos 6 horas diarias de sol pleno.\n" +
                "\n" +
                "Puede tolerar algo de sombra parcial, pero esto reducirá la floración y puede hacer que la planta se estire.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Temperatura ideal: 20 - 35 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 5 - 41 ℃\n" +
                "\n" +
                "Margarita del Cabo exhibe una notable resistencia a las variaciones de temperatura, tolerando el frío hasta 5℃ (41°F) y el calor hasta 41℃ (106°F). Este amplio rango resalta su adaptabilidad pero requiere cuidados específicos. Durante los meses más fríos, protege a margarita del Cabo con mantillo o mueve las plantas en macetas al interior para prevenir daños por escarcha, los cuales se indican por hojas marchitas y tallos ennegrecidos. En caso de calor extremo, asegúrate de regar regularmente y proporciona sombra para evitar marchitez y quemaduras en las hojas. Comprender estos umbrales permite que margarita del Cabo prospere en diversos entornos, aunque medidas como la protección contra heladas o el sombreado durante olas de calor son esenciales para su salud óptima.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Para la Margarita del Cabo (Osteospermum), el sustrato ideal es:\n" +
                "\n" +
                "Bien drenado y ligero para evitar encharcamientos que dañen las raíces.\n" +
                "\n" +
                "Una mezcla común puede ser:\n" +
                "\n" +
                "Tierra para macetas estándar, enriquecida con un poco de arena gruesa o perlita para mejorar el drenaje.\n" +
                "\n" +
                "pH: Prefiere un sustrato ligeramente ácido a neutro, entre 6.0 y 7.0.\n" +
                "\n" +
                "Evita suelos muy compactos o con mala aireación, que pueden provocar pudrición.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Nivel de humedad: Humedad media\n" +
                "\n" +
                "Proveniente de sus regiones costeras nativas, margarita del Cabo está acostumbrado a la humedad periódica de la niebla y el rocío en lugar de lluvias abundantes. Esta planta muestra una preferencia por la humedad constante pero puede soportar breves períodos secos, gracias a su naturaleza semi-suculenta. En consecuencia, se beneficia del riego semanal para mantener su equilibrio de hidratación. Como arbusto perenne, margarita del Cabo suele embellecer los paisajes exteriores con su resistencia a las condiciones de agua variables y la capacidad de mantener un follaje exuberante durante todo el año.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Fertilice margarita del Cabo quincenalmente en primavera-verano con un fertilizante alto en fósforo para aumentar la vitalidad de la floración y la salud de la planta. Utilice una solución a media fuerza, asegurando que el suelo esté húmedo. Reduzca la alimentación en otoño-invierno a una vez al mes. La sobre-fertilización puede llevar a un crecimiento frondoso a expensas de las flores. Para margarita del Cabo cultivado en contenedores, la alimentación constante es crucial debido a la lixiviación de nutrientes. Siempre siga las instrucciones específicas del producto en cuanto a la cantidad y dilución.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Época de poda: Durante todo el año\n" +
                "\n" +
                "Beneficios de la poda: Estimula la floración, Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Margarita del Cabo es un arbusto perenne de crecimiento expandido conocido por sus vibrantes flores similares a margaritas. Para un crecimiento óptimo, poda a fines del invierno o principios de primavera antes de que inicie el nuevo crecimiento. Elimina los tallos muertos o dañados, da forma a la planta y recorta para fomentar un follaje denso y saludable. La poda anual ayuda a mantener su forma y promueve una floración más prolífica. Evita la poda drástica en otros momentos para prevenir el choque y asegurar la continuidad de la floración. La poda constante mejora la circulación del aire, reduciendo el riesgo de enfermedades.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Época de trasplante: Primavera\n" +
                "\n" +
                "Trasplante margarita del Cabo desde el despertar de la primavera hasta el umbral del verano para un óptimo establecimiento de raíces. Ubíquelo en un lugar soleado con suelo bien drenado. Asegure un comienzo amigable manteniendo las raíces sin perturbar siempre que sea posible.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (430, 'Época de propagación: Primavera, Verano\n" +
                "\n" +
                "Tipo de propagación: Corte, División\n" +
                "\n" +
                "Margarita del Cabo se propaga exitosamente tomando esquejes de plantas sanas y maduras. Para un crecimiento efectivo, use suelo bien drenado y mantenga los esquejes húmedos hasta que establezcan raíces. Asegúrese de que los esquejes tengan al menos un nodo de hoja debajo de la superficie del suelo para fomentar el desarrollo de raíces. Un rociado constante puede evitar que las hojas de los esquejes se sequen mientras enraízan.', 8)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Para la Margarita de la lluvia (Dimorphotheca spp.), la iluminación adecuada es:\n" +
                "\n" +
                "Sol pleno: Necesita al menos 6 a 8 horas diarias de luz solar directa para crecer vigorosamente y florecer de forma abundante.\n" +
                "\n" +
                "En zonas muy calurosas, puede agradecer algo de sombra ligera por la tarde, pero en general prefiere lugares muy soleados.\n" +
                "\n" +
                "En sombra parcial o total, la planta florecerá poco o nada y puede volverse débil y alargada.', 1)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Temperatura ideal: 20 - 38 ℃\n" +
                "\n" +
                "Tolerancia a la temperatura: 0 - 43 ℃\n" +
                "\n" +
                "Margarita de la lluvia demuestra una notable resistencia a las variaciones de temperatura, tolerando el frío hasta 0℃ (32℉) y el calor hasta 43℃ (109℉). Esta adaptabilidad lo hace adecuado para climas diversos, aunque se aconsejan medidas de protección en condiciones extremas. En frío, margarita de la lluvia puede mostrar un crecimiento lento o decoloración de hojas, mientras que el sobrecalentamiento puede causar hojas marchitas o quemadas. Para cuidar a margarita de la lluvia, proporcione aislamiento como mantillo durante temperaturas bajo cero y asegúrese de un riego adecuado y sombra durante las olas de calor. Monitorear los pronósticos del tiempo y ajustar las rutinas de cuidado en consecuencia ayudará a mantener la salud y vitalidad de la planta.', 2)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Tipo de suelo: Tierra de jardín\n" +
                "\n" +
                "pH del suelo: 6-7\n" +
                "\n" +
                "Para un crecimiento óptimo, margarita de la lluvia requiere un suelo bien drenado. Una mezcla adecuada incluye tierra de jardín combinada con arena o perlita para mejorar la aireación y el drenaje, evitando el encharcamiento. Si no hay arena o perlita disponibles, utiliza grava o gravilla fina. Enmienda periódicamente el suelo con un fertilizante equilibrado para promover un crecimiento saludable, aplicándolo cada cuatro a seis semanas durante la temporada de crecimiento. Mantener el suelo ligero asegura que margarita de la lluvia prospere y previene la pudrición de las raíces.', 3)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Horario de riego: Todas las semanas\n" +
                "\n" +
                "Originaria de regiones áridas, margarita de la lluvia se ha adaptado para conservar agua, mostrando una preferencia por condiciones más secas. Esta especie prospera con humedad moderada y puede tolerar breves períodos de sequía. El riego debe cumplir con un horario de una vez por semana para mantener niveles óptimos de humedad sin saturar demasiado el suelo. Por lo general, se cultiva al aire libre debido a su capacidad de predecir lluvias, cerrando sus flores cuando la lluvia es inminente, haciendo de margarita de la lluvia un ejemplo excepcional de cómo algunas plantas han evolucionado comportamientos receptivos a su entorno de hidratación.', 4)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Para margarita de la lluvia, utilice fertilizantes altos en fósforo para mejorar la floración. Fertilice quincenalmente durante el crecimiento activo en primavera y verano, reduciendo a una vez al mes en otoño. Aplique una solución al 25% según las instrucciones del paquete; evite la sobre fertilización. Beneficios: optimiza la floración, promueve la vigorosidad, apoya el desarrollo de raíces. En invierno, cese la fertilización ya que margarita de la lluvia entra en dormancia. Sensible a los nutrientes en exceso, siempre asegure el drenaje del suelo para evitar quemaduras en las raíces. Consejos: use fertilizantes granulares o solubles en agua; incorpórelos en el calendario de riego para una distribución uniforme.', 5)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Época de poda: Primavera, Verano, Otoño\n" +
                "\n" +
                "Beneficios de la poda: Crecimiento vigoroso, Mantiene la planta en forma\n" +
                "\n" +
                "Margarita de la lluvia, conocido por sus flores en forma de margarita y su larga temporada de floración, se beneficia de una poda periódica para mantener su vigor y mejorar la calidad de las flores. Podar a principios de primavera para eliminar cualquier tallo muerto o dañado, fomentando un nuevo crecimiento saludable, y nuevamente más tarde en primavera para dar forma a la planta y promover un crecimiento más arbustivo. La eliminación regular de flores marchitas durante la temporada de floración extenderá la floración. Asegúrate de que los cortes sean limpios y se hagan justo por encima de los nodos de las hojas. La poda promueve eficazmente una planta más saludable y estéticamente agradable.', 6)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Época de trasplante: Principios de verano, Mediados de verano\n" +
                "\n" +
                "El momento ideal para trasplantar margarita de la lluvia es entre finales de primavera y principios de verano, ya que prospera cuando hace calor y hay muchas horas de luz. Elija un lugar soleado y asegúrese de que la tierra drena bien. Recuerde que a esta delicada belleza no le gusta que le toquen las raíces, por lo que debe tener mucho cuidado al trasplantarla.', 7)")
        db.execSQL("INSERT INTO CUIDADOS (COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO) VALUES (440, 'Época de propagación: Mediados de primavera, Finales de primavera\n" +
                "\n" +
                "Tipo de propagación: Siembra\n" +
                "\n" +
                "Margarita de la lluvia es una planta encantadora conocida por sus flores parecidas a margaritas y su capacidad única de indicar la lluvia inminente. Para propagar con éxito margarita de la lluvia, los jardineros deben centrarse en la siembra de sus semillas. Comience preparando una mezcla de suelo con buen drenaje y luego esparza las semillas de manera uniforme sobre la superficie, presionándolas ligeramente en el suelo, pero evite cubrirlas ya que requieren luz para germinar. Mantenga el suelo constantemente húmedo pero no encharcado. Una vez que las plántulas desarrollen hojas verdaderas y sean lo suficientemente fuertes, trasplántelas a su ubicación final asegurando espacio suficiente para el crecimiento.', 8)")

        // INSERTS TIPO_ENFERMEDADES
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (1,'Moho blanco', 'moho_blanco')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (2,'Marchitamiento de flores', 'marchitamiento')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (3,'Cicatrices', 'cicatrices')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (4,'Marchitamiento de hojas', 'marchitamiento_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (5,'Hojas amarillas', 'hojas_amarillas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (6,'Mariquitas', 'mariquitas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (7,'Caída de hoja', 'caida_hoja')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (8,'Amarillamiento intervascular', 'amarillamiento_intervascular')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (9,'Marchitamiento de rama', 'marchitamiento_rama')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (10,'Marchitamiento de planta', 'marchitamiento_planta')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (11,'Moho negro', 'moho_negro')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (12,'Muerte regresiva', 'muerte_regresiva')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (13,'Cochinilla', 'cochinillas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (14,'Pudrición hojas', 'pudrición_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (15,'Mancha marrón', 'mancha_marron')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (16,'Falta de riego', 'falta_riego')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (17,'Áfido', 'afido')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (18,'Muesca', 'muesca')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (19,'Manchas oscuras', 'manchas_oscuras')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (20,'Podredumbre de hojas', 'podredumbre_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (21,'Quemaduras en hojas', 'quemaduras_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (22,'Marchitamiento postFloracion', 'marchitamiento_postfloracion')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (23,'Chinches de encaje', 'chinches_encaje')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (24,'Tizón del pétalo', 'tizon_petalo')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (25,'Orugas', 'orugas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (26,'Deficiencia de nutrientes', 'deficiencia_nutrientes')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (27,'Envejecimiento', 'envejecimiento')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (28,'Escarabajo de las hojas', 'escarabajo_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (29,'Gorgojos de hojas', 'gorgojos_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (30,'Antracnosis', 'antracnosis')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (31,'Punto negro', 'punto_negro')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (32,'Fruta marchita', 'fruta marchita')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (33,'Raiz podrida', 'raiz_podrida')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (34,'Pudrición del tallo', 'pudricion_tallo')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (35,'Insuficiencia de luz', 'insuficiencia_luz')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (36,'Mancha bacteriana', 'mancha_bacteriana')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (38,'Abejas', 'abejas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (39,'Moscas blancas', 'moscas_blancas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (40,'Trips', 'trips')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (41,'Moho en la fruta', 'moho_fruta')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (42,'Pulgones', 'pulgones')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (43,'Cerambícidos', 'cerambicidos')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (44,'Hormigas de fuego', 'hormigas_fuego')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (45,'Moho en la tierra', 'moho_tierra')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (46,'Malformacion de la fruta', 'malformacion_fruta')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (47,'Tizón de fuego', 'tizón_fuego')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (48,'Baja humedad', 'baja_humedad')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (49,'Marchitamiento puntas hojas', 'marchitamiento_puntas_hojas')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (50,'Oídio', 'oidio')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (51,'Clorosis venal', 'clorosis_venal')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (52,'Insectos chupadores de savia', 'insectos_savia')")
        db.execSQL("INSERT INTO TIPO_ENFERMEDADES (ID_TIPO_ENFERMEDAD, NOMBRE_TIPO_ENFERMEDAD, IMAGEN) VALUES (53,'Minadores', 'minadores')")


        // INSERTS ENFERMEDADES
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'Las manchas marrones en las hojas de la azalea de fuego suelen indicar la presencia de enfermedades fúngicas, como la Cercospora o el Phytophthora, o pueden deberse a problemas de riego o quemaduras por sol. Estas manchas pueden aparecer como puntos o áreas irregulares, a menudo rodeadas por un borde amarillo o rojizo. Si no se tratan, las hojas pueden caerse prematuramente y la planta debilitarse.\n','Soluciones: \n 1. Revisión del riego:\n" +
                "\n" +
                "Evita el exceso de agua; la azalea de fuego prefiere suelos húmedos pero bien drenados.\n" +
                "Riega solo cuando la capa superior del sustrato esté seca.\n" +
                "No mojes las hojas al regar, hazlo directamente en la base.\n" +
                "Mejorar la ventilación:\n" +
                "\n" +
                "Asegúrate de que la planta tenga buena circulación de aire alrededor.\n" +
                "Si está muy tupida, poda ligeramente para permitir el flujo de aire.\n" +
                "Tratamiento fúngico:\n" +
                "\n" +
                "Retira y desecha todas las hojas afectadas (no las composte).\n" +
                "Aplica un fungicida específico para azaleas, preferentemente a base de cobre o con ingredientes activos como azoxistrobina.\n" +
                "Realiza la aplicación en días secos y según las recomendaciones del producto.\n" +
                "Evitar quemaduras solares:\n" +
                "\n" +
                "No expongas la azalea de fuego a sol directo intenso, especialmente en horas de mayor radiación.\n" +
                "Prefiere ubicarlas en semisombra o con sol filtrado.\n" +
                "Revisión de nutrientes:\n" +
                "\n" +
                "Aplica fertilizante especial para azaleas (ácido), pero sin excederte, ya que el exceso de sales puede causar manchas.\n" +
                "Mantén el pH del suelo entre 4.5 y 6.0.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'Las flores marchitas en la azalea de fuego pueden deberse al final natural del ciclo de la flor, pero si ocurre de forma prematura suele estar relacionado con estrés hídrico, exceso de sol, plagas, enfermedades fúngicas o deficiencias nutricionales. Las flores se ven caídas, sin color y pueden secarse rápidamente después de abrirse.','1. Riego adecuado:\n" +
                "\n" +
                "Mantén el sustrato húmedo, pero nunca encharcado. Las azaleas de fuego son sensibles tanto al exceso como a la falta de agua.\n" +
                "Riega por la mañana y evita mojar las flores para prevenir hongos.\n" +
                "Ubicación correcta:\n" +
                "\n" +
                "Coloca la planta en semisombra o donde reciba luz filtrada, nunca sol directo fuerte, ya que esto puede marchitar las flores rápidamente.\n" +
                "Protege la planta de corrientes de aire caliente o muy seco.\n" +
                "Poda de flores marchitas (deadheading):\n" +
                "\n" +
                "Retira manualmente las flores marchitas para estimular la producción de nuevas y evitar la propagación de enfermedades.\n" +
                "Hazlo con cuidado, sujetando el tallo para no dañar los brotes cercanos.\n" +
                "Fertilización específica:\n" +
                "\n" +
                "Usa fertilizante ácido especial para azaleas, pero solo durante la época de crecimiento (primavera-verano).\n" +
                "No fertilices en exceso, ya que puede dañar la planta y las flores.\n" +
                "Prevención y control de plagas y enfermedades:\n" +
                "\n" +
                "Revisa regularmente la planta para detectar ácaros, trips o pulgones, que pueden afectar la floración.\n" +
                "Si ves hongos (como manchas en pétalos), retira las flores afectadas y aplica un fungicida adecuado para azaleas.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'El marchitamiento después de la floración en la azalea de fuego es común, pero si las hojas y tallos se ven decaídos, amarillos o secos poco después de la floración (y no es solo la flor la que termina su ciclo natural), puede indicar estrés, enfermedades de raíz (como Phytophthora), deficiencia de nutrientes, o problemas en el riego y el drenaje.','1. Revisión del riego y drenaje:\n" +
                "\n" +
                "Asegúrate de que la planta no esté encharcada. La azalea de fuego es muy sensible al exceso de agua y a suelos mal drenados.\n" +
                "Usa sustrato ácido y suelto, idealmente mezcla de turba, corteza de pino y perlita.\n" +
                "Riega solo cuando la capa superficial esté seca, evitando mojar el follaje.\n" +
                "Examina las raíces:\n" +
                "\n" +
                "Si el problema persiste, saca la planta con cuidado y revisa las raíces. Si están negras, blandas o huelen mal, probablemente hay pudrición por hongos.\n" +
                "En ese caso, elimina las raíces dañadas y trasplanta a sustrato nuevo y bien drenante. Aplica fungicida específico para azaleas.\n" +
                "Poda y limpieza:\n" +
                "\n" +
                "Retira flores y ramas marchitas para evitar la acumulación de hongos o bacterias.\n" +
                "Esteriliza las tijeras antes y después de podar.\n" +
                "Fertilización post-floración:\n" +
                "\n" +
                "Aplica fertilizante para azaleas después de la floración, rico en potasio y bajo en nitrógeno para ayudar a la recuperación y preparar la siguiente brotación.\n" +
                "Evita el estrés ambiental:\n" +
                "\n" +
                "No trasplantes ni podes fuertemente justo después de la floración.\n" +
                "Mantén la planta en un lugar con semisombra y protegido de vientos fuertes.\n" +
                "Control de enfermedades:\n" +
                "\n" +
                "Si sospechas de Phytophthora o algún hongo, aplica un fungicida sistémico adecuado para azaleas y mejora el drenaje.\n" +
                "No reutilices sustrato viejo ni agua estancada.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'Las chinches de encaje (Stephanitis spp.) son plagas comunes en las azaleas de fuego. Son insectos pequeños y planos de color marrón claro a negro, con alas reticuladas que parecen encaje. Suelen encontrarse en el envés de las hojas, donde chupan la savia, provocando manchas amarillas, decoloración, y debilitamiento de la planta. También pueden dejar pequeñas motas negras de excremento.','1. Inspección y monitoreo:\n" +
                "\n" +
                "Revisa frecuentemente el envés de las hojas, especialmente durante primavera y verano.\n" +
                "Si hay presencia de chinches, actúa rápidamente para evitar infestaciones graves.\n" +
                "Poda y eliminación manual:\n" +
                "\n" +
                "Retira manualmente las hojas muy infestadas y deséchalas lejos de la planta.\n" +
                "Poda ramas densas para mejorar la circulación de aire, ya que las chinches prefieren ambientes húmedos y poco ventilados.\n" +
                "Control biológico:\n" +
                "\n" +
                "Fomenta la presencia de depredadores naturales, como mariquitas y crisopas, que se alimentan de estas chinches.\n" +
                "Evita el uso excesivo de insecticidas de amplio espectro, ya que pueden eliminar estos aliados naturales.\n" +
                "Lavado de hojas:\n" +
                "\n" +
                "Rocía el envés de las hojas con agua a presión moderada para desalojar chinches (sin dañar la planta).\n" +
                "Repite el lavado cada pocos días hasta reducir la plaga.\n" +
                "Tratamiento químico (solo si es necesario):\n" +
                "\n" +
                "Usa jabones insecticidas o aceites hortícolas específicos para azaleas. Aplica en el envés de las hojas, siguiendo las indicaciones del fabricante.\n" +
                "Si la infestación es grave, puedes usar insecticidas sistémicos a base de imidacloprid, pero solo como último recurso y preferentemente en aplicaciones dirigidas.\n" +
                "No apliques insecticidas durante la floración para evitar dañar polinizadores.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén la planta sana con riego y fertilización adecuados.\n" +
                "Evita el estrés por sequía o exceso de sol directo, ya que las plantas debilitadas son más propensas a infestaciones.',23)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'El tizón del pétalo (causado principalmente por el hongo Ovulinia azaleae) es una enfermedad común en la azalea de fuego, especialmente durante periodos húmedos y lluviosos. Se manifiesta como manchas acuosas y translúcidas en los pétalos, que avanzan rápidamente tornándose marrón oscuro o negro. Las flores afectadas se marchitan y caen prematuramente, y la enfermedad puede propagarse velozmente entre las flores en condiciones favorables.','1. Retira flores afectadas:\n" +
                "\n" +
                "Elimina y destruye inmediatamente todas las flores enfermas para evitar la propagación del hongo. Nunca las dejes en el suelo ni las compostes.\n" +
                "Evita el riego sobre flores:\n" +
                "\n" +
                "Riega siempre en la base de la planta para mantener las flores secas. El exceso de humedad favorece la aparición del tizón.\n" +
                "Mejora la ventilación:\n" +
                "\n" +
                "Poda ligeramente para permitir mayor circulación de aire entre las ramas y flores, ayudando a secar la humedad superficial.\n" +
                "Aplicación de fungicidas:\n" +
                "\n" +
                "Trata preventivamente con fungicidas específicos para azaleas, como los que contienen iprodiona, captan o clorotalonil.\n" +
                "Realiza la aplicación antes de la floración y repite según las indicaciones del producto, sobre todo si se prevé clima húmedo.\n" +
                "Evita el contacto planta-planta:\n" +
                "\n" +
                "Mantén las azaleas de fuego separadas unas de otras para reducir la posibilidad de contagio.\n" +
                "Sanitiza herramientas:\n" +
                "\n" +
                "Limpia y desinfecta las tijeras de poda y cualquier herramienta que entre en contacto con flores o ramas enfermas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'Las orugas en la azalea de fuego son larvas de mariposas o polillas que se alimentan de las hojas, provocando perforaciones, bordes mordidos y, en infestaciones graves, defoliación parcial o total de la planta. Las orugas suelen encontrarse en el envés de las hojas o entre los brotes tiernos, y pueden debilitar seriamente la planta si no se controlan.','1. Inspección regular:\n" +
                "\n" +
                "Examina frecuentemente la planta, especialmente en primavera y verano, buscando orugas, huevos o excrementos (pequeñas bolitas negras) en el envés de las hojas y brotes.\n" +
                "2. Eliminación manual:\n" +
                "\n" +
                "Si la infestación es pequeña, retira las orugas a mano y deséchalas lejos del jardín.\n" +
                "Utiliza guantes para evitar contacto directo.\n" +
                "3. Poda de partes afectadas:\n" +
                "\n" +
                "Corta hojas o brotes muy dañados y elimínalos adecuadamente para reducir la población de orugas y la posibilidad de que se extiendan.\n" +
                "4. Control biológico:\n" +
                "\n" +
                "Fomenta la presencia de aves y depredadores naturales en el jardín.\n" +
                "Puedes introducir bacterias específicas como Bacillus thuringiensis (Bt), que es inocua para la planta y otros insectos, pero letal para las orugas cuando la ingieren.\n" +
                "5. Tratamiento químico (solo si es necesario):\n" +
                "\n" +
                "Si la infestación es grave y persiste, utiliza insecticidas específicos para orugas, asegurándote de que sean seguros para azaleas (consulta la etiqueta del producto).\n" +
                "Aplica temprano en la mañana o al atardecer y evita los días de floración para no dañar polinizadores.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Mantén la planta sana con riegos y fertilizaciones adecuadas, ya que las plantas vigorosas resisten mejor los ataques de plagas.\n" +
                "Retira las hojas caídas y restos vegetales donde las orugas puedan invernar.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'La quemadura de hojas en la azalea de fuego se manifiesta como bordes marrones, secos o quebradizos en las hojas, a veces acompañados de manchas amarillas entre las venas. Esto suele ser causado por exposición excesiva al sol, viento fuerte, falta o exceso de riego, acumulación de sales en el sustrato, o incluso fertilización inadecuada. Las hojas afectadas pueden enrollarse y caer prematuramente.','1. Ubicación adecuada:\n" +
                "\n" +
                "Protege la azalea de fuego del sol directo intenso, especialmente en las horas centrales del día.\n" +
                "Prefiere semisombra o luz filtrada, idealmente bajo árboles o mallas de sombreo.\n" +
                "2. Riego correcto:\n" +
                "\n" +
                "Mantén el sustrato uniformemente húmedo, pero nunca encharcado.\n" +
                "Usa agua blanda (preferiblemente de lluvia o filtrada), ya que el exceso de sales puede quemar las raíces y manifestarse en las hojas.\n" +
                "3. Evita el viento fuerte:\n" +
                "\n" +
                "Coloca la planta en un sitio protegido de corrientes de aire fuertes que puedan secar y dañar las hojas.\n" +
                "4. Fertilización moderada:\n" +
                "\n" +
                "Usa fertilizantes específicos para azaleas, en dosis bajas y solo en la época de crecimiento.\n" +
                "Evita abonar en pleno calor o con el sustrato seco, pues esto puede acentuar la quemadura.\n" +
                "5. Revisión del sustrato:\n" +
                "\n" +
                "Si sospechas acumulación de sales, realiza un lavado del sustrato regando abundantemente para arrastrarlas.\n" +
                "Asegúrate de que el drenaje sea excelente y el pH esté entre 4.5 y 6.0.\n" +
                "6. Poda de hojas dañadas:\n" +
                "\n" +
                "Retira cuidadosamente las hojas quemadas para evitar la entrada de enfermedades y estimular nueva brotación.',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'La deficiencia de nutrientes en la azalea de fuego se manifiesta principalmente con hojas pálidas, amarillentas (clorosis), crecimiento lento, brotes débiles y, en casos graves, caída prematura de hojas o escasa floración. Es común que la falta de hierro (clorosis férrica) u otros micronutrientes (como magnesio o manganeso) cause amarilleo entre las venas de las hojas, mientras las venas permanecen verdes.','1. Diagnóstico del tipo de deficiencia:\n" +
                "\n" +
                "Observa el patrón de amarilleo: si las hojas jóvenes están más afectadas, suele ser falta de hierro; si las viejas, puede ser falta de magnesio.\n" +
                "Presta atención a otros síntomas como manchas, necrosis o deformaciones para identificar posibles carencias de otros nutrientes.\n" +
                "2. Corrección del pH del suelo:\n" +
                "\n" +
                "Asegúrate de que el sustrato tenga un pH entre 4.5 y 6.0, ya que la azalea de fuego solo absorbe nutrientes en suelos ácidos.\n" +
                "Si el pH es alto (alcalino), añade azufre elemental o productos acidificantes específicos para reducirlo.\n" +
                "3. Fertilización adecuada:\n" +
                "\n" +
                "Usa fertilizantes diseñados para azaleas o plantas acidófilas, que contienen hierro quelatado y micronutrientes.\n" +
                "Aplica fertilizante en primavera y verano según las indicaciones del fabricante, evitando el exceso.\n" +
                "4. Aplicación foliar (para carencias severas):\n" +
                "\n" +
                "Aplica quelato de hierro o fertilizante foliar específico para azaleas si la clorosis es intensa y visible en nuevas hojas.\n" +
                "Repite el tratamiento cada 2-3 semanas hasta observar mejora.\n" +
                "5. Evita el exceso de riego:\n" +
                "\n" +
                "El riego excesivo puede lavar los nutrientes del sustrato y agravar las carencias. Mantén el suelo húmedo, pero nunca encharcado.\n" +
                "6. Renovación del sustrato:\n" +
                "\n" +
                "Si el sustrato es muy viejo o compacto, considera trasplantar la azalea de fuego a una mezcla fresca, ácida y bien aireada (turba, corteza de pino, perlita).',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'El envejecimiento en la azalea de fuego se refiere al deterioro natural de la planta con el paso de los años. Se manifiesta con menor floración, ramas leñosas y desnudas en la base, hojas más pequeñas o escasas, y mayor susceptibilidad a plagas y enfermedades. Las plantas viejas pueden presentar un aspecto poco vigoroso y menos atractivo, y pueden dejar de producir brotes nuevos si no se les da mantenimiento adecuado.','1. Poda de rejuvenecimiento:\n" +
                "\n" +
                "Realiza una poda drástica a finales del invierno o principio de primavera antes de la brotación.\n" +
                "Corta 1/3 de las ramas viejas y leñosas cerca de la base para estimular el desarrollo de brotes nuevos.\n" +
                "En los años siguientes, repite el proceso hasta renovar toda la planta. No podes más del 1/3 por temporada para evitar estrés excesivo.\n" +
                "2. Fertilización de mantenimiento:\n" +
                "\n" +
                "Usa fertilizante específico para azaleas después de la poda, rico en fósforo y potasio para favorecer la brotación y floración.\n" +
                "Aplica materia orgánica (humus, compost ácido) alrededor de la base para mejorar la estructura del suelo.\n" +
                "3. Riego adecuado:\n" +
                "\n" +
                "Mantén el sustrato húmedo, evitando tanto la sequía como el encharcamiento, ya que las plantas viejas son más sensibles a extremos hídricos.\n" +
                "4. Renovación de sustrato:\n" +
                "\n" +
                "Si la planta lleva muchos años en el mismo sitio, renueva parte del sustrato superficial agregando mezcla fresca y ácida.\n" +
                "Si está en maceta, considera trasplantar a un recipiente ligeramente mayor o renovar parte del sustrato.\n" +
                "5. Control de plagas y enfermedades:\n" +
                "\n" +
                "Las plantas envejecidas son más susceptibles. Revisa regularmente y trata problemas a tiempo.\n" +
                "Mantén la planta aireada y limpia, sin restos de hojas o flores secas.\n" +
                "6. Estimulación de la brotación:\n" +
                "\n" +
                "Puedes aplicar bioestimulantes (extractos de algas, aminoácidos) en primavera para fomentar el desarrollo de nuevos brotes.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (10,'El escarabajo de las hojas (a menudo Altica o especies similares) es una plaga que puede afectar a la azalea de fuego. Los adultos y sus larvas se alimentan de las hojas, dejando orificios irregulares, esqueletizando el limbo foliar o causando manchas translúcidas. Si la infestación es grave, la planta puede debilitarse y reducir su floración.','1. Inspección frecuente:\n" +
                "\n" +
                "Revisa regularmente el envés y haz de las hojas para detectar adultos (escarabajos metálicos o azulados) y larvas pequeñas.\n" +
                "2. Eliminación manual:\n" +
                "\n" +
                "Retira manualmente los escarabajos y larvas si la infestación es baja. Sacude suavemente las ramas sobre una bandeja o tela para recogerlos y deséchalos lejos del jardín.\n" +
                "3. Poda de hojas afectadas:\n" +
                "\n" +
                "Corta y elimina hojas muy dañadas para reducir la población y evitar la propagación.\n" +
                "4. Control biológico:\n" +
                "\n" +
                "Fomenta la presencia de depredadores naturales como aves, mariquitas y avispas parasitarias en el jardín.\n" +
                "5. Tratamiento natural:\n" +
                "\n" +
                "Aplica jabón potásico o aceite de neem sobre las hojas, especialmente en el envés, para reducir la población de escarabajos y larvas. Repite el tratamiento cada 5-7 días mientras persista la plaga.\n" +
                "6. Tratamiento químico (solo si es necesario):\n" +
                "\n" +
                "Utiliza insecticidas específicos para escarabajos de hoja, aptos para azaleas y siguiendo estrictamente las instrucciones del fabricante.\n" +
                "Aplica en las primeras horas de la mañana o al atardecer para minimizar el impacto en polinizadores y fauna benéfica.\n" +
                "Evita tratar durante la floración.\n" +
                "7. Prevención:\n" +
                "\n" +
                "Mantén la planta vigorosa con riego y fertilización adecuados.\n" +
                "Elimina restos vegetales del suelo para evitar sitios de refugio y reproducción.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Las manchas marrones en las hojas de la azalea occidental suelen deberse a infecciones fúngicas (como Cercospora, Phyllosticta o alternaria), bacterianas, quemaduras solares o exceso de humedad. Estas manchas pueden empezar como puntos pequeños, redondos o irregulares, y expandirse hasta secar partes del tejido foliar. Si no se controla, la planta puede debilitarse y perder hojas.','1. Eliminación de hojas afectadas:\n" +
                "\n" +
                "Retira y desecha todas las hojas que presenten manchas marrones para reducir la propagación de patógenos.\n" +
                "2. Mejora de circulación de aire:\n" +
                "\n" +
                "Poda ramas densas para aumentar la ventilación alrededor de la planta y favorecer el secado de las hojas después de la lluvia o el riego.\n" +
                "3. Evita mojar el follaje:\n" +
                "\n" +
                "Riega directamente sobre el sustrato, evitando mojar las hojas, especialmente en las horas de menos sol o por la tarde.\n" +
                "4. Tratamiento con fungicida:\n" +
                "\n" +
                "Aplica fungicidas específicos para plantas acidófilas (como los que contienen cobre, mancozeb o clorotalonil) siguiendo las recomendaciones del fabricante.\n" +
                "Realiza aplicaciones preventivas en épocas húmedas o lluviosas.\n" +
                "5. Mantén la planta sana:\n" +
                "\n" +
                "Asegura un buen drenaje y sustrato ácido.\n" +
                "Fertiliza de acuerdo a las necesidades de la azalea occidental para evitar debilitamiento.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas caídas alrededor de la planta.\n" +
                "No cultives la azalea demasiado apretada con otras plantas para evitar la propagación de enfermedades.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Las flores se caen o secan rápidamente, generalmente por el final del ciclo floral, pero puede ser causado por falta/exceso de agua, calor, sol directo, o enfermedades.','1. Mantén el sustrato húmedo pero bien drenado:\n" +
                "\n" +
                "Riega regularmente, evitando tanto la sequía como el encharcamiento.\n" +
                "2. Ubica la planta en semisombra:\n" +
                "\n" +
                "Evita el sol directo intenso, especialmente en las horas centrales del día.\n" +
                "3. Retira las flores marchitas:\n" +
                "\n" +
                "Elimina las flores secas para promover nuevas floraciones y evitar enfermedades.\n" +
                "4. Fertiliza adecuadamente:\n" +
                "\n" +
                "Usa fertilizante ácido específico para azaleas solo en época de crecimiento.\n" +
                "5. Vigila plagas y hongos en las flores:\n" +
                "\n" +
                "Inspecciona regularmente para intervenir a tiempo si surge algún problema.\n',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Las hojas y tallos se ven decaídos tras la floración, lo que puede indicar problemas de raíz, estrés ambiental, o exceso de agua.','1. Asegura buen drenaje y riega solo cuando la capa superficial esté seca:\n" +
                "\n" +
                "Evita que el sustrato permanezca encharcado.\n" +
                "2. Revisa raíces:\n" +
                "\n" +
                "Si están negras y blandas, elimina las dañadas y trasplanta.\n" +
                "3. Retira residuos y poda partes marchitas:\n" +
                "\n" +
                "Limpia la planta para prevenir enfermedades.\n" +
                "4. Aplica fertilizante después de la floración:\n" +
                "\n" +
                "Ayuda a la recuperación de la planta.\n" +
                "5. Evita trasplantes o podas fuertes justo después de la floración:\n" +
                "\n" +
                "Da tiempo a la planta para recuperarse.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Pequeños insectos con alas transparentes que chupan la savia en el envés de las hojas, causando manchas amarillas y debilitamiento.','1. Revisa envés de hojas y elimina manualmente las afectadas:\n" +
                "\n" +
                "Actúa rápidamente para evitar infestaciones graves.\n" +
                "2. Mejora la ventilación podando ramas densas:\n" +
                "\n" +
                "Favorece el secado y dificulta el ambiente para la plaga.\n" +
                "3. Usa jabón potásico o aceite de neem en infestaciones leves:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Para casos graves, usa insecticidas específicos, evitando la floración:\n" +
                "\n" +
                "Sigue siempre las indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales como mariquitas:\n" +
                "\n" +
                "Mantén el equilibrio biológico en tu jardín.',23)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Manchas acuosas y oscuras en los pétalos, que luego se marchitan rápidamente, causado por hongos como Ovulinia azaleae.','1. Retira y destruye flores infectadas:\n" +
                "\n" +
                "No las dejes en el suelo ni las compostes.\n" +
                "2. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega a nivel del suelo.\n" +
                "3. Mejora ventilación y separa plantas:\n" +
                "\n" +
                "Facilita el secado y reduce el contagio.\n" +
                "4. Aplica fungicidas preventivos antes y durante la floración:\n" +
                "\n" +
                "Usa productos recomendados para azaleas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Larvas que comen hojas, dejando bordes mordidos y defoliación.','1. Retira manualmente orugas visibles:\n" +
                "\n" +
                "Usa guantes para evitar contacto directo.\n" +
                "2. Poda partes muy dañadas:\n" +
                "\n" +
                "Reduce la población y evita que se extiendan.\n" +
                "3. Aplica Bacillus thuringiensis (Bt) como biocontrol:\n" +
                "\n" +
                "Seguro para la planta y selectivo para orugas.\n" +
                "4. Usa insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Protege a los polinizadores.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Bordes marrones y secos, o manchas amarillas por exceso de sol, viento fuerte, riego inadecuado o acumulación de sales.',' Ubica en semisombra o luz filtrada:\n" +
                "\n" +
                "Protege del sol intenso y vientos fuertes.\n" +
                "2. Riega con agua blanda y evita encharcamientos:\n" +
                "\n" +
                "Mantén el sustrato uniformemente húmedo.\n" +
                "3. Fertiliza con productos para plantas acidófilas:\n" +
                "\n" +
                "Sigue las dosis recomendadas.\n" +
                "4. Si hay sales acumuladas, lava el sustrato abundantemente:\n" +
                "\n" +
                "Favorece el drenaje y elimina el exceso de sales.\n',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Hojas amarillas entre las venas, crecimiento pobre y floración escasa, a menudo por falta de hierro, magnesio u otros micronutrientes.','Mantén el pH ácido (4.5–6.0):\n" +
                "\n" +
                "Usa productos acidificantes si es necesario.\n" +
                "2. Usa fertilizantes específicos para azaleas, ricos en hierro quelatado:\n" +
                "\n" +
                "Aplica en primavera y verano.\n" +
                "3. Aplica tratamientos foliares si la clorosis es severa:\n" +
                "\n" +
                "Usa quelatos o fertilizantes foliares según indicaciones.\n" +
                "4. Renueva el sustrato si está viejo o compacto:\n" +
                "\n" +
                "Mejora la estructura y disponibilidad de nutrientes.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (20,'Menor floración, ramas leñosas y base desnuda, hojas pequeñas y débil crecimiento por edad avanzada de la planta.',' Realiza podas de rejuvenecimiento en invierno o primavera:\n" +
                "\n" +
                "Corta 1/3 de las ramas viejas cada año.\n" +
                "2. Fertiliza tras la poda y añade materia orgánica:\n" +
                "\n" +
                "Favorece la brotación y mejora el suelo.\n" +
                "3. Renueva parte del sustrato superficial:\n" +
                "\n" +
                "Añade mezcla fresca y ácida.\n" +
                "4. Mantén riego y control de plagas adecuado:\n" +
                "\n" +
                "Las plantas viejas son más susceptibles.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Escarabajos y larvas que perforan hojas, pueden esqueletizarlas y debilitar la planta.','1. Revisa y elimina manualmente escarabajos y larvas:\n" +
                "\n" +
                "Sacude suavemente las ramas para recogerlos.\n" +
                "2. Poda hojas muy afectadas:\n" +
                "\n" +
                "Reduce la población y previene el daño.\n" +
                "3. Usa jabón potásico o aceite de neem:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Aplica insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Sigue indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales:\n" +
                "\n" +
                "Mantén un jardín equilibrado.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Las manchas marrones en las hojas de la azalea china suelen deberse a infecciones fúngicas (como Cercospora, Phyllosticta o alternaria), bacterianas, quemaduras solares o exceso de humedad. Estas manchas pueden empezar como puntos pequeños, redondos o irregulares, y expandirse hasta secar partes del tejido foliar. Si no se controla, la planta puede debilitarse y perder hojas.','1. Eliminación de hojas afectadas:\n" +
                "\n" +
                "Retira y desecha todas las hojas que presenten manchas marrones para reducir la propagación de patógenos.\n" +
                "2. Mejora de circulación de aire:\n" +
                "\n" +
                "Poda ramas densas para aumentar la ventilación alrededor de la planta y favorecer el secado de las hojas después de la lluvia o el riego.\n" +
                "3. Evita mojar el follaje:\n" +
                "\n" +
                "Riega directamente sobre el sustrato, evitando mojar las hojas, especialmente en las horas de menos sol o por la tarde.\n" +
                "4. Tratamiento con fungicida:\n" +
                "\n" +
                "Aplica fungicidas específicos para plantas acidófilas (como los que contienen cobre, mancozeb o clorotalonil) siguiendo las recomendaciones del fabricante.\n" +
                "Realiza aplicaciones preventivas en épocas húmedas o lluviosas.\n" +
                "5. Mantén la planta sana:\n" +
                "\n" +
                "Asegura un buen drenaje y sustrato ácido.\n" +
                "Fertiliza de acuerdo a las necesidades de la azalea china para evitar debilitamiento.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas caídas alrededor de la planta.\n" +
                "No cultives la azalea demasiado apretada con otras plantas para evitar la propagación de enfermedades.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Las flores se caen o secan rápidamente, generalmente por el final del ciclo floral, pero puede ser causado por falta/exceso de agua, calor, sol directo, o enfermedades.','1. Mantén el sustrato húmedo pero bien drenado:\n" +
                "\n" +
                "Riega regularmente, evitando tanto la sequía como el encharcamiento.\n" +
                "2. Ubica la planta en semisombra:\n" +
                "\n" +
                "Evita el sol directo intenso, especialmente en las horas centrales del día.\n" +
                "3. Retira las flores marchitas:\n" +
                "\n" +
                "Elimina las flores secas para promover nuevas floraciones y evitar enfermedades.\n" +
                "4. Fertiliza adecuadamente:\n" +
                "\n" +
                "Usa fertilizante ácido específico para azaleas solo en época de crecimiento.\n" +
                "5. Vigila plagas y hongos en las flores:\n" +
                "\n" +
                "Inspecciona regularmente para intervenir a tiempo si surge algún problema.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Las hojas y tallos se ven decaídos tras la floración, lo que puede indicar problemas de raíz, estrés ambiental, o exceso de agua.','1. Asegura buen drenaje y riega solo cuando la capa superficial esté seca:\n" +
                "\n" +
                "Evita que el sustrato permanezca encharcado.\n" +
                "2. Revisa raíces:\n" +
                "\n" +
                "Si están negras y blandas, elimina las dañadas y trasplanta.\n" +
                "3. Retira residuos y poda partes marchitas:\n" +
                "\n" +
                "Limpia la planta para prevenir enfermedades.\n" +
                "4. Aplica fertilizante después de la floración:\n" +
                "\n" +
                "Ayuda a la recuperación de la planta.\n" +
                "5. Evita trasplantes o podas fuertes justo después de la floración:\n" +
                "\n" +
                "Da tiempo a la planta para recuperarse.\n',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Pequeños insectos con alas transparentes que chupan la savia en el envés de las hojas, causando manchas amarillas y debilitamiento.','1. Revisa envés de hojas y elimina manualmente las afectadas:\n" +
                "\n" +
                "Actúa rápidamente para evitar infestaciones graves.\n" +
                "2. Mejora la ventilación podando ramas densas:\n" +
                "\n" +
                "Favorece el secado y dificulta el ambiente para la plaga.\n" +
                "3. Usa jabón potásico o aceite de neem en infestaciones leves:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Para casos graves, usa insecticidas específicos, evitando la floración:\n" +
                "\n" +
                "Sigue siempre las indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales como mariquitas:\n" +
                "\n" +
                "Mantén el equilibrio biológico en tu jardín.',23)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Manchas acuosas y oscuras en los pétalos, que luego se marchitan rápidamente, causado por hongos como Ovulinia azaleae.','1. Retira y destruye flores infectadas:\n" +
                "\n" +
                "No las dejes en el suelo ni las compostes.\n" +
                "2. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega a nivel del suelo.\n" +
                "3. Mejora ventilación y separa plantas:\n" +
                "\n" +
                "Facilita el secado y reduce el contagio.\n" +
                "4. Aplica fungicidas preventivos antes y durante la floración:\n" +
                "\n" +
                "Usa productos recomendados para azaleas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Larvas que comen hojas, dejando bordes mordidos y defoliación.','1. Retira manualmente orugas visibles:\n" +
                "\n" +
                "Usa guantes para evitar contacto directo.\n" +
                "2. Poda partes muy dañadas:\n" +
                "\n" +
                "Reduce la población y evita que se extiendan.\n" +
                "3. Aplica Bacillus thuringiensis (Bt) como biocontrol:\n" +
                "\n" +
                "Seguro para la planta y selectivo para orugas.\n" +
                "4. Usa insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Protege a los polinizadores.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Bordes marrones y secos, o manchas amarillas por exceso de sol, viento fuerte, riego inadecuado o acumulación de sales.','1. Ubica en semisombra o luz filtrada:\n" +
                "\n" +
                "Protege del sol intenso y vientos fuertes.\n" +
                "2. Riega con agua blanda y evita encharcamientos:\n" +
                "\n" +
                "Mantén el sustrato uniformemente húmedo.\n" +
                "3. Fertiliza con productos para plantas acidófilas:\n" +
                "\n" +
                "Sigue las dosis recomendadas.\n" +
                "4. Si hay sales acumuladas, lava el sustrato abundantemente:\n" +
                "\n" +
                "Favorece el drenaje y elimina el exceso de sales.\n',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Hojas amarillas entre las venas, crecimiento pobre y floración escasa, a menudo por falta de hierro, magnesio u otros micronutrientes.','1. Mantén el pH ácido (4.5–6.0):\n" +
                "\n" +
                "Usa productos acidificantes si es necesario.\n" +
                "2. Usa fertilizantes específicos para azaleas, ricos en hierro quelatado:\n" +
                "\n" +
                "Aplica en primavera y verano.\n" +
                "3. Aplica tratamientos foliares si la clorosis es severa:\n" +
                "\n" +
                "Usa quelatos o fertilizantes foliares según indicaciones.\n" +
                "4. Renueva el sustrato si está viejo o compacto:\n" +
                "\n" +
                "Mejora la estructura y disponibilidad de nutrientes.\n',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Menor floración, ramas leñosas y base desnuda, hojas pequeñas y débil crecimiento por edad avanzada de la planta.','1. Realiza podas de rejuvenecimiento en invierno o primavera:\n" +
                "\n" +
                "Corta 1/3 de las ramas viejas cada año.\n" +
                "2. Fertiliza tras la poda y añade materia orgánica:\n" +
                "\n" +
                "Favorece la brotación y mejora el suelo.\n" +
                "3. Renueva parte del sustrato superficial:\n" +
                "\n" +
                "Añade mezcla fresca y ácida.\n" +
                "4. Mantén riego y control de plagas adecuado:\n" +
                "\n" +
                "Las plantas viejas son más susceptibles.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (30,'Escarabajos y larvas que perforan hojas, pueden esqueletizarlas y debilitar la planta.','1. Revisa y elimina manualmente escarabajos y larvas:\n" +
                "\n" +
                "Sacude suavemente las ramas para recogerlos.\n" +
                "2. Poda hojas muy afectadas:\n" +
                "\n" +
                "Reduce la población y previene el daño.\n" +
                "3. Usa jabón potásico o aceite de neem:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Aplica insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Sigue indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales:\n" +
                "\n" +
                "Mantén un jardín equilibrado.\n',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Las manchas marrones en las hojas de la azalea temprana suelen deberse a infecciones fúngicas (como Cercospora, Phyllosticta o alternaria), bacterianas, quemaduras solares o exceso de humedad. Estas manchas pueden empezar como puntos pequeños, redondos o irregulares, y expandirse hasta secar partes del tejido foliar. Si no se controla, la planta puede debilitarse y perder hojas.','1. Eliminación de hojas afectadas:\n" +
                "\n" +
                "Retira y desecha todas las hojas que presenten manchas marrones para reducir la propagación de patógenos.\n" +
                "2. Mejora de circulación de aire:\n" +
                "\n" +
                "Poda ramas densas para aumentar la ventilación alrededor de la planta y favorecer el secado de las hojas después de la lluvia o el riego.\n" +
                "3. Evita mojar el follaje:\n" +
                "\n" +
                "Riega directamente sobre el sustrato, evitando mojar las hojas, especialmente en las horas de menos sol o por la tarde.\n" +
                "4. Tratamiento con fungicida:\n" +
                "\n" +
                "Aplica fungicidas específicos para plantas acidófilas (como los que contienen cobre, mancozeb o clorotalonil) siguiendo las recomendaciones del fabricante.\n" +
                "Realiza aplicaciones preventivas en épocas húmedas o lluviosas.\n" +
                "5. Mantén la planta sana:\n" +
                "\n" +
                "Asegura un buen drenaje y sustrato ácido.\n" +
                "Fertiliza de acuerdo a las necesidades de la azalea temprana para evitar debilitamiento.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas caídas alrededor de la planta.\n" +
                "No cultives la azalea demasiado apretada con otras plantas para evitar la propagación de enfermedades.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Las flores se caen o secan rápidamente, generalmente por el final del ciclo floral, pero puede ser causado por falta/exceso de agua, calor, sol directo, o enfermedades.','1. Mantén el sustrato húmedo pero bien drenado:\n" +
                "\n" +
                "Riega regularmente, evitando tanto la sequía como el encharcamiento.\n" +
                "2. Ubica la planta en semisombra:\n" +
                "\n" +
                "Evita el sol directo intenso, especialmente en las horas centrales del día.\n" +
                "3. Retira las flores marchitas:\n" +
                "\n" +
                "Elimina las flores secas para promover nuevas floraciones y evitar enfermedades.\n" +
                "4. Fertiliza adecuadamente:\n" +
                "\n" +
                "Usa fertilizante ácido específico para azaleas solo en época de crecimiento.\n" +
                "5. Vigila plagas y hongos en las flores:\n" +
                "\n" +
                "Inspecciona regularmente para intervenir a tiempo si surge algún problema.\n',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Las hojas y tallos se ven decaídos tras la floración, lo que puede indicar problemas de raíz, estrés ambiental, o exceso de agua.','1. Asegura buen drenaje y riega solo cuando la capa superficial esté seca:\n" +
                "\n" +
                "Evita que el sustrato permanezca encharcado.\n" +
                "2. Revisa raíces:\n" +
                "\n" +
                "Si están negras y blandas, elimina las dañadas y trasplanta.\n" +
                "3. Retira residuos y poda partes marchitas:\n" +
                "\n" +
                "Limpia la planta para prevenir enfermedades.\n" +
                "4. Aplica fertilizante después de la floración:\n" +
                "\n" +
                "Ayuda a la recuperación de la planta.\n" +
                "5. Evita trasplantes o podas fuertes justo después de la floración:\n" +
                "\n" +
                "Da tiempo a la planta para recuperarse.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Pequeños insectos con alas transparentes que chupan la savia en el envés de las hojas, causando manchas amarillas y debilitamiento.','1. Revisa envés de hojas y elimina manualmente las afectadas:\n" +
                "\n" +
                "Actúa rápidamente para evitar infestaciones graves.\n" +
                "2. Mejora la ventilación podando ramas densas:\n" +
                "\n" +
                "Favorece el secado y dificulta el ambiente para la plaga.\n" +
                "3. Usa jabón potásico o aceite de neem en infestaciones leves:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Para casos graves, usa insecticidas específicos, evitando la floración:\n" +
                "\n" +
                "Sigue siempre las indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales como mariquitas:\n" +
                "\n" +
                "Mantén el equilibrio biológico en tu jardín.\n',23)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Manchas acuosas y oscuras en los pétalos, que luego se marchitan rápidamente, causado por hongos como Ovulinia azaleae.','1. Retira y destruye flores infectadas:\n" +
                "\n" +
                "No las dejes en el suelo ni las compostes.\n" +
                "2. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega a nivel del suelo.\n" +
                "3. Mejora ventilación y separa plantas:\n" +
                "\n" +
                "Facilita el secado y reduce el contagio.\n" +
                "4. Aplica fungicidas preventivos antes y durante la floración:\n" +
                "\n" +
                "Usa productos recomendados para azaleas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Larvas que comen hojas, dejando bordes mordidos y defoliación.','1. Retira manualmente orugas visibles:\n" +
                "\n" +
                "Usa guantes para evitar contacto directo.\n" +
                "2. Poda partes muy dañadas:\n" +
                "\n" +
                "Reduce la población y evita que se extiendan.\n" +
                "3. Aplica Bacillus thuringiensis (Bt) como biocontrol:\n" +
                "\n" +
                "Seguro para la planta y selectivo para orugas.\n" +
                "4. Usa insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Protege a los polinizadores.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Bordes marrones y secos, o manchas amarillas por exceso de sol, viento fuerte, riego inadecuado o acumulación de sales.','1. Ubica en semisombra o luz filtrada:\n" +
                "\n" +
                "Protege del sol intenso y vientos fuertes.\n" +
                "2. Riega con agua blanda y evita encharcamientos:\n" +
                "\n" +
                "Mantén el sustrato uniformemente húmedo.\n" +
                "3. Fertiliza con productos para plantas acidófilas:\n" +
                "\n" +
                "Sigue las dosis recomendadas.\n" +
                "4. Si hay sales acumuladas, lava el sustrato abundantemente:\n" +
                "\n" +
                "Favorece el drenaje y elimina el exceso de sales.',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Hojas amarillas entre las venas, crecimiento pobre y floración escasa, a menudo por falta de hierro, magnesio u otros micronutrientes.','1. Mantén el pH ácido (4.5–6.0):\n" +
                "\n" +
                "Usa productos acidificantes si es necesario.\n" +
                "2. Usa fertilizantes específicos para azaleas, ricos en hierro quelatado:\n" +
                "\n" +
                "Aplica en primavera y verano.\n" +
                "3. Aplica tratamientos foliares si la clorosis es severa:\n" +
                "\n" +
                "Usa quelatos o fertilizantes foliares según indicaciones.\n" +
                "4. Renueva el sustrato si está viejo o compacto:\n" +
                "\n" +
                "Mejora la estructura y disponibilidad de nutrientes.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Menor floración, ramas leñosas y base desnuda, hojas pequeñas y débil crecimiento por edad avanzada de la planta.','1. Realiza podas de rejuvenecimiento en invierno o primavera:\n" +
                "\n" +
                "Corta 1/3 de las ramas viejas cada año.\n" +
                "2. Fertiliza tras la poda y añade materia orgánica:\n" +
                "\n" +
                "Favorece la brotación y mejora el suelo.\n" +
                "3. Renueva parte del sustrato superficial:\n" +
                "\n" +
                "Añade mezcla fresca y ácida.\n" +
                "4. Mantén riego y control de plagas adecuado:\n" +
                "\n" +
                "Las plantas viejas son más susceptibles.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (40,'Escarabajos y larvas que perforan hojas, pueden esqueletizarlas y debilitar la planta.','1. Revisa y elimina manualmente escarabajos y larvas:\n" +
                "\n" +
                "Sacude suavemente las ramas para recogerlos.\n" +
                "2. Poda hojas muy afectadas:\n" +
                "\n" +
                "Reduce la población y previene el daño.\n" +
                "3. Usa jabón potásico o aceite de neem:\n" +
                "\n" +
                "Aplica en el envés de las hojas.\n" +
                "4. Aplica insecticidas específicos solo si es necesario y fuera de la floración:\n" +
                "\n" +
                "Sigue indicaciones del fabricante.\n" +
                "5. Fomenta depredadores naturales:\n" +
                "\n" +
                "Mantén un jardín equilibrado.\n',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'Las manchas marrones en las hojas de la azalea alpino suelen deberse a infecciones fúngicas (como Cercospora, Phyllosticta o Alternaria), bacterianas, quemaduras solares o exceso de humedad. Estas manchas pueden comenzar como puntos pequeños, redondos o irregulares, y expandirse hasta secar partes del tejido foliar. Si no se controla, la planta puede debilitarse y perder hojas.','1. Eliminación de hojas afectadas:\n" +
                "\n" +
                "Retira y desecha todas las hojas que presenten manchas marrones para reducir la propagación de patógenos.\n" +
                "2. Mejora de circulación de aire:\n" +
                "\n" +
                "Poda ramas densas para aumentar la ventilación alrededor de la planta y favorecer el secado de las hojas después de la lluvia o el riego.\n" +
                "3. Evita mojar el follaje:\n" +
                "\n" +
                "Riega directamente sobre el sustrato, evitando mojar las hojas, especialmente en las horas de menos sol o por la tarde.\n" +
                "4. Tratamiento con fungicida:\n" +
                "\n" +
                "Aplica fungicidas específicos para plantas acidófilas (como los que contienen cobre, mancozeb o clorotalonil) siguiendo las recomendaciones del fabricante.\n" +
                "Realiza aplicaciones preventivas en épocas húmedas o lluviosas.\n" +
                "5. Mantén la planta sana:\n" +
                "\n" +
                "Asegura un buen drenaje y sustrato ácido.\n" +
                "Fertiliza de acuerdo a las necesidades de la azalea alpino para evitar debilitamiento.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas caídas alrededor de la planta.\n" +
                "No cultives la azalea alpino demasiado apretada con otras plantas para evitar la propagación de enfermedades.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'Las flores de la azalea alpino se caen o secan rápidamente. Esto puede deberse al final natural del ciclo floral, pero también a factores como falta o exceso de agua, calor, exposición al sol directo en exceso, o enfermedades.','1. Mantén el sustrato húmedo pero bien drenado:\n" +
                "\n" +
                "Riega con regularidad, pero evita que el sustrato llegue a estar encharcado.\n" +
                "2. Ubica la planta en semisombra:\n" +
                "\n" +
                "Evita el sol directo intenso, especialmente durante las horas centrales del día, ya que la azalea alpino prefiere luz filtrada.\n" +
                "3. Retira las flores marchitas:\n" +
                "\n" +
                "Elimina las flores secas conforme aparezcan para fomentar nuevas floraciones y prevenir enfermedades.\n" +
                "4. Fertiliza adecuadamente:\n" +
                "\n" +
                "Usa fertilizante ácido específico para azaleas, solo durante la época de crecimiento, para favorecer la floración y la salud general.\n" +
                "5. Vigila plagas y hongos en las flores:\n" +
                "\n" +
                "Revisa las flores y los brotes para detectar síntomas de plagas o enfermedades y actúa rápidamente si notas algo anormal.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'Los gorgojos de las hojas (especialmente el gorgojo de la vid, Otiorhynchus sulcatus) son una plaga común en azaleas alpinas. Los adultos suelen morder los bordes de las hojas en forma de semicírculos, mientras que las larvas, que viven en el suelo, se alimentan de las raíces. Esto puede provocar debilitamiento, amarilleo, defoliación y, en infestaciones graves, la muerte de la planta.','1. Inspección nocturna de adultos:\n" +
                "\n" +
                "Los adultos son nocturnos. Revisa las hojas al anochecer y retíralos manualmente si la infestación es baja.\n" +
                "2. Trampas y barreras:\n" +
                "\n" +
                "Coloca bandas adhesivas o cintas pegajosas en el tronco para dificultar el ascenso de los gorgojos adultos.\n" +
                "3. Reducción de larvas en el sustrato:\n" +
                "\n" +
                "Usa nematodos entomopatógenos (como Steinernema spp. o Heterorhabditis spp.) en el suelo para eliminar larvas.\n" +
                "4. Poda de hojas afectadas:\n" +
                "\n" +
                "Retira las hojas muy dañadas para mejorar el aspecto y reducir el número de gorgojos que pueden alimentarse.\n" +
                "5. Evita el exceso de humedad:\n" +
                "\n" +
                "Mantén el sustrato bien drenado, ya que la humedad favorece a las larvas del gorgojo.\n" +
                "6. Control químico (solo si es necesario):\n" +
                "\n" +
                "Aplica insecticidas específicos para gorgojos siguiendo las indicaciones del fabricante, preferiblemente en el suelo para atacar a las larvas y en periodos fuera de la floración.\n" +
                "7. Prevención:\n" +
                "\n" +
                "Revisa plantas nuevas antes de plantarlas y mantén el entorno limpio de restos vegetales donde los gorgojos pueden refugiarse.',29)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'La astracnosis (también conocida como antracnosis) es una enfermedad fúngica que afecta a la azalea alpino, causada principalmente por hongos del género Colletotrichum. Se manifiesta como manchas marrones o negras irregulares en las hojas, brotes y, en ocasiones, en los tallos. Las áreas afectadas pueden secarse, caer y, si la enfermedad avanza, provocar la defoliación y debilitamiento general de la planta.','1. Eliminación de partes afectadas:\n" +
                "\n" +
                "Retira y destruye todas las hojas y tallos que muestren síntomas de astracnosis para evitar la propagación del hongo.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Poda las ramas densas para aumentar la circulación de aire, ya que la humedad favorece el desarrollo del hongo.\n" +
                "3. Evita mojar el follaje al regar:\n" +
                "\n" +
                "Riega a nivel del sustrato y hazlo preferiblemente en la mañana para que las hojas se sequen rápidamente.\n" +
                "4. Tratamiento con fungicida:\n" +
                "\n" +
                "Aplica fungicidas específicos para antracnosis (por ejemplo, a base de cobre, mancozeb, clorotalonil o captan) siguiendo las indicaciones del fabricante y repite el tratamiento si es necesario, especialmente en épocas de humedad.\n" +
                "5. Mantén la planta sana:\n" +
                "\n" +
                "Procura un sustrato ácido, bien drenado y fertiliza de acuerdo a las necesidades de la azalea alpino para fortalecer su resistencia.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas y flores caídas alrededor de la planta.\n" +
                "No plantes la azalea alpino demasiado cerca de otras plantas para evitar el contagio de enfermedades.',30)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'En la azalea alpino, el envejecimiento se manifiesta por menor floración, ramas leñosas y una base desnuda, hojas pequeñas y un crecimiento débil. Con el paso de los años, la planta puede perder vigor, volverse menos densa y ser más susceptible a enfermedades y plagas.','1. Realiza podas de rejuvenecimiento en invierno o primavera:\n" +
                "\n" +
                "Corta alrededor de 1/3 de las ramas viejas o leñosas cada año para estimular el crecimiento de nuevos brotes y renovar la estructura de la planta.\n" +
                "2. Fertiliza tras la poda y añade materia orgánica:\n" +
                "\n" +
                "Aplica un fertilizante adecuado para plantas acidófilas y agrega compost o materia orgánica al sustrato para mejorar la nutrición y vitalidad de la azalea alpino.\n" +
                "3. Renueva parte del sustrato superficial:\n" +
                "\n" +
                "Retira la capa superior del sustrato y reemplázala con una mezcla fresca y ácida, adecuada para azaleas, para aportar nutrientes y mejorar la estructura del suelo.\n" +
                "4. Mantén riego y control de plagas adecuado:\n" +
                "\n" +
                "Ajusta el riego para mantener el sustrato ligeramente húmedo, evitando encharcamientos, y vigila la aparición de plagas o enfermedades, ya que las plantas envejecidas son más susceptibles.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'El marchitamiento de la azalea alpino se manifiesta cuando la planta pierde turgencia, las hojas y tallos se ven decaídos y pueden llegar a caer. Esto puede deberse a problemas de raíz (como asfixia radicular, podredumbre o ataque de nematodos), estrés ambiental (exceso de calor, viento seco), falta o exceso de agua, o daños por enfermedades fúngicas en el sistema radicular.','1. Verifica el riego y el drenaje:\n" +
                "\n" +
                "Asegúrate de que el sustrato esté uniformemente húmedo, pero nunca encharcado. La azalea alpino es sensible tanto a la sequía como al exceso de agua.\n" +
                "2. Comprueba el estado de las raíces:\n" +
                "\n" +
                "Si la planta sigue marchita pese a regar correctamente, desentierra con cuidado para inspeccionar las raíces. Si están negras, blandas o huelen mal, elimina las partes dañadas y trasplanta a un sustrato nuevo, bien drenado y ácido.\n" +
                "3. Evita el estrés ambiental:\n" +
                "\n" +
                "Protege la azalea alpino del viento fuerte y del sol directo intenso, ubicándola en semisombra o en un lugar resguardado.\n" +
                "4. Poda las partes marchitas:\n" +
                "\n" +
                "Retira hojas y tallos afectados para favorecer la recuperación y prevenir la aparición de hongos secundarios.\n" +
                "5. Fertiliza tras la recuperación:\n" +
                "\n" +
                "Una vez la planta muestre signos de mejora, aplica un fertilizante equilibrado para plantas acidófilas para estimular el rebrote.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Utiliza siempre un sustrato adecuado, aireado y ácido, y evita trasplantes o podas fuertes en épocas de estrés o calor extremo.',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'El tizón del pétalo en la azalea alpino es causado principalmente por hongos como Ovulinia azaleae. Se manifiesta como manchas acuosas, translúcidas o oscuras en los pétalos, que rápidamente se marchitan y caen. Si no se trata, puede destruir gran parte de la floración, ya que el hongo se propaga rápidamente en condiciones de humedad.','1. Retira y destruye flores infectadas:\n" +
                "\n" +
                "Elimina y desecha todas las flores que presenten síntomas, sin dejarlas en el suelo ni compostarlas, para evitar la propagación del hongo.\n" +
                "2. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega a nivel del sustrato para que las flores permanezcan secas, especialmente durante la floración.\n" +
                "3. Mejora la ventilación y separa las plantas:\n" +
                "\n" +
                "Poda ramas densas y coloca las azaleas alpinas con espacio suficiente entre ellas para reducir la humedad y favorecer el secado rápido de los pétalos.\n" +
                "4. Aplica fungicidas preventivos antes y durante la floración:\n" +
                "\n" +
                "Utiliza fungicidas recomendados para azaleas (como los que contienen cobre o clorotalonil) de forma preventiva en épocas húmedas o cuando comience la floración, siguiendo siempre las indicaciones del fabricante.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'El punto negro en la azalea alpino es una afección causada generalmente por hongos como Phyllosticta, Alternaria o Diplocarpon. Aparecen pequeñas manchas negras redondeadas u ovaladas en las hojas (a veces en tallos), que pueden crecer y fusionarse, provocando la caída prematura de hojas y debilitando la planta.','1. Eliminación de hojas y partes afectadas:\n" +
                "\n" +
                "Retira y destruye todas las hojas y tallos con puntos negros para evitar que el hongo se propague.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Poda ramas densas para permitir una mayor circulación de aire y reducir la humedad, ya que estas condiciones favorecen el desarrollo del hongo.\n" +
                "3. Evita mojar el follaje al regar:\n" +
                "\n" +
                "Riega directamente el sustrato y evita que el agua caiga sobre las hojas, especialmente al atardecer o en ambientes húmedos.\n" +
                "4. Aplicación de fungicidas:\n" +
                "\n" +
                "Usa fungicidas específicos para enfermedades foliares (a base de cobre, mancozeb o clorotalonil), siguiendo las indicaciones del fabricante. Realiza aplicaciones preventivas en épocas de alta humedad.\n" +
                "5. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente los restos de hojas caídas y otros desechos alrededor de la planta.\n" +
                "Mantén la azalea alpino separada de otras plantas para evitar la transmisión de enfermedades.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'En la azalea alpino, el marchitamiento posterior a la floración se manifiesta cuando las hojas y tallos se ven decaídos, perdiendo turgencia y vitalidad. Esto puede deberse a un agotamiento natural tras el esfuerzo de la floración, pero también a problemas en las raíces (exceso de agua, mala aireación, enfermedades fúngicas), estrés ambiental o falta de nutrientes tras la floración.','1. Asegura buen drenaje y riega solo cuando la capa superficial esté seca:\n" +
                "\n" +
                "Evita el encharcamiento y deja secar la parte superior del sustrato antes de volver a regar. La azalea alpino es sensible al exceso de humedad tras la floración.\n" +
                "2. Revisa el estado de las raíces:\n" +
                "\n" +
                "Si sospechas problemas de raíz, extrae cuidadosamente la planta y comprueba si hay raíces negras o blandas. Si es así, elimina las raíces dañadas y trasplanta a un sustrato nuevo, ácido y bien aireado.\n" +
                "3. Retira residuos florales y poda partes marchitas:\n" +
                "\n" +
                "Limpia los restos de flores caídas y corta hojas o tallos afectados para prevenir infecciones fúngicas y mejorar la recuperación.\n" +
                "4. Aplica fertilizante después de la floración:\n" +
                "\n" +
                "Utiliza un fertilizante adecuado para plantas acidófilas, preferentemente equilibrado o específico para azaleas, para ayudar a la recuperación y estimular el crecimiento vegetativo.\n" +
                "5. Evita trasplantes o podas fuertes justo después de la floración:\n" +
                "\n" +
                "Permite que la planta se recupere antes de realizar intervenciones mayores.\n" +
                "6. Vigila la aparición de plagas y enfermedades:\n" +
                "\n" +
                "Tras la floración, la azalea alpino puede estar más débil y ser más susceptible a problemas, por lo que conviene hacer revisiones periódicas.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (50,'El escarabajo de las hojas es una plaga que puede afectar a la azalea alpino. Los adultos y sus larvas se alimentan de las hojas, creando agujeros irregulares o consumiendo los bordes. En infestaciones severas, pueden provocar defoliación, debilitamiento y hacer a la planta más susceptible a enfermedades.','1. Inspección y retirada manual:\n" +
                "\n" +
                "Revisa regularmente la planta, especialmente el envés de las hojas, y retira manualmente los escarabajos y larvas si la infestación es baja.\n" +
                "2. Trampas y barreras físicas:\n" +
                "\n" +
                "Coloca trampas adhesivas cerca de la base de la planta para atrapar adultos. También puedes usar mallas finas para impedir el acceso de los escarabajos a la planta durante los periodos de mayor actividad.\n" +
                "3. Aplicación de insecticidas biológicos:\n" +
                "\n" +
                "Utiliza productos a base de aceite de neem o jabón potásico para controlar las larvas y adultos, siguiendo siempre las indicaciones del fabricante.\n" +
                "4. Control químico (sólo en casos graves):\n" +
                "\n" +
                "Si la plaga es severa y otras soluciones no funcionan, aplica insecticidas específicos para escarabajos, preferentemente cuando no haya floración y siguiendo estrictamente las indicaciones del fabricante.\n" +
                "5. Prevención:\n" +
                "\n" +
                "Mantén la azalea alpino vigorosa mediante un buen abonado y riego adecuado, ya que las plantas sanas son más resistentes a las plagas.\n" +
                "Retira restos vegetales y hojas caídas donde los escarabajos pueden refugiarse o depositar huevos.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'La mancha marrón en la azalea de Andalucía suele ser causada por hongos (como Phyllosticta, Cercospora o Septoria) o, en menor medida, por estrés abiótico (daños de sol, quemaduras químicas o exceso de humedad). Se observa como manchas marrones de forma circular u ovalada en las hojas, a veces con bordes definidos y centros más claros. Con el tiempo, las manchas pueden crecer, fusionarse y provocar la caída prematura de hojas, debilitando la planta.','1. Eliminación de hojas afectadas:\n" +
                "\n" +
                "Retira y destruye todas las hojas que presenten manchas marrones para evitar la propagación del hongo.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Poda ramas densas para aumentar la circulación de aire y disminuir la humedad, condiciones que favorecen el desarrollo de hongos.\n" +
                "3. Evita mojar el follaje al regar:\n" +
                "\n" +
                "Riega solo el sustrato, no las hojas, y hazlo preferentemente por la mañana para que la planta se seque durante el día.\n" +
                "4. Aplicación de fungicidas:\n" +
                "\n" +
                "Usa fungicidas específicos para enfermedades foliares (a base de cobre, mancozeb o clorotalonil), siguiendo las indicaciones del fabricante. Realiza aplicaciones preventivas en épocas de lluvias o alta humedad.\n" +
                "5. Prevención:\n" +
                "\n" +
                "Limpia frecuentemente restos de hojas caídas y otros desechos vegetales alrededor de la planta para evitar focos de infección.\n" +
                "Mantén la azalea de Andalucía en un lugar bien ventilado y evita el hacinamiento con otras plantas.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'La flor marchita en la azalea de Andalucía es un síntoma común que puede deberse a varias causas: envejecimiento natural de la flor, exceso o falta de riego, enfermedades fúngicas (como el tizón del pétalo), ataque de insectos, estrés ambiental (temperaturas extremas, viento) o deficiencias nutricionales.','1. Elimina flores marchitas rápidamente:\n" +
                "\n" +
                "Retira y desecha todas las flores marchitas o dañadas para evitar la propagación de hongos y mejorar el aspecto de la planta.\n" +
                "2. Revisa el riego:\n" +
                "\n" +
                "Mantén el sustrato húmedo pero no encharcado. Evita tanto el exceso como la falta de agua.\n" +
                "3. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega solo la base de la planta para reducir el riesgo de enfermedades fúngicas en las flores.\n" +
                "4. Mejora la ventilación:\n" +
                "\n" +
                "Poda ramas densas y asegúrate de que la planta esté en un sitio con buena circulación de aire, lo que ayuda a secar rápidamente las flores y previene hongos.\n" +
                "5. Aplica fungicidas si hay sospecha de enfermedades:\n" +
                "\n" +
                "Si notas manchas oscuras, acuosas o crecimiento de moho en las flores, utiliza un fungicida adecuado siguiendo las indicaciones del fabricante.\n" +
                "6. Fertiliza después de la floración:\n" +
                "\n" +
                "Aplica un fertilizante específico para azaleas para ayudar a la planta a recuperarse y estimular la próxima floración.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'Después de la floración, la azalea de Andalucía puede mostrar marchitamiento en hojas y tallos. Las causas frecuentes incluyen agotamiento tras el esfuerzo de la floración, exceso o falta de riego, problemas de raíces (como asfixia radicular o enfermedades fúngicas), deficiencia de nutrientes, o estrés ambiental (por calor, viento o cambios bruscos de temperatura).','1. Asegura un buen drenaje y riego adecuado:\n" +
                "\n" +
                "Mantén el sustrato húmedo pero no encharcado. Deja secar la capa superficial antes de volver a regar y evita el exceso de agua tras la floración.\n" +
                "2. Revisa el estado de las raíces:\n" +
                "\n" +
                "Si el marchitamiento persiste, extrae con cuidado la planta y revisa las raíces. Si están blandas, negras o huelen mal, elimina las partes dañadas y trasplanta a sustrato nuevo, ácido y aireado.\n" +
                "3. Retira restos florales y hojas marchitas:\n" +
                "\n" +
                "Limpia flores caídas y poda partes marchitas para evitar el desarrollo de hongos y mejorar la recuperación.\n" +
                "4. Aplica fertilizante después de la floración:\n" +
                "\n" +
                "Usa un fertilizante equilibrado específico para azaleas o plantas acidófilas. Esto ayuda a la planta a reponerse del gasto energético de la floración.\n" +
                "5. Evita trasplantes o podas fuertes justo después de la floración:\n" +
                "\n" +
                "Deja que la planta recupere vigor antes de realizar intervenciones mayores.\n" +
                "6. Vigila la aparición de plagas y enfermedades:\n" +
                "\n" +
                "Revisa periódicamente la planta, ya que puede estar más débil y susceptible tras la floración.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'Las chinches de encaje (Stephanitis spp.) son pequeños insectos que atacan la azalea de Andalucía alimentándose del envés de las hojas. Sus daños se manifiestan como punteado clorótico (manchas amarillas o blanquecinas), debilitamiento general, y en infestaciones severas, caída prematura de hojas. Al revisar el envés, se pueden observar los insectos adultos, sus huevos y excrementos oscuros.','1. Inspección y retirada manual:\n" +
                "\n" +
                "Revisa el envés de las hojas y, si la infestación es baja, retira manualmente las chinches y hojas muy afectadas.\n" +
                "2. Poda de partes infestadas:\n" +
                "\n" +
                "Realiza una poda selectiva en las ramas más afectadas para reducir la población de chinches y mejorar la ventilación.\n" +
                "3. Lavado de hojas:\n" +
                "\n" +
                "Lava el envés de las hojas con agua a presión para desprender los insectos. Esto es útil si la plaga está en fases iniciales.\n" +
                "4. Aplicación de insecticidas biológicos:\n" +
                "\n" +
                "Emplea jabón potásico o aceite de neem, aplicando bien en el envés de las hojas y repitiendo cada 7-10 días hasta controlar la plaga.\n" +
                "5. Control químico (solo en casos graves):\n" +
                "\n" +
                "Si la plaga es severa y otros métodos no funcionan, utiliza insecticidas específicos para chinches (como piretroides), siguiendo las recomendaciones del fabricante y evitando su uso durante la floración para no afectar polinizadores.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Mantén la azalea bien aireada y sin exceso de humedad, ya que las plantas sanas y bien cuidadas son más resistentes.\n" +
                "Inspecciona regularmente la planta para detectar a tiempo la presencia de chinches de encaje y otros insectos.',23)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'El tizón del pétalo en la azalea de Andalucía es causado principalmente por hongos como Ovulinia azaleae. Se presenta como manchas acuosas, translúcidas o marrones en los pétalos, que rápidamente se marchitan y caen. Las condiciones de alta humedad y lluvias favorecen la propagación, pudiendo arruinar gran parte de la floración.','1. Elimina flores infectadas y restos florales:\n" +
                "\n" +
                "Retira y destruye todas las flores afectadas y los restos caídos alrededor de la planta para evitar la propagación del hongo.\n" +
                "2. Evita mojar las flores al regar:\n" +
                "\n" +
                "Riega solo la base de la planta, nunca los pétalos, para reducir la humedad sobre las flores.\n" +
                "3. Mejora la ventilación:\n" +
                "\n" +
                "Poda ramas densas y mantén suficiente espacio entre plantas para que el aire circule bien y los pétalos se sequen rápidamente.\n" +
                "4. Aplica fungicidas preventivos antes y durante la floración:\n" +
                "\n" +
                "Utiliza fungicidas recomendados para azaleas (por ejemplo, a base de cobre o clorotalonil), siguiendo siempre las indicaciones del fabricante, especialmente cuando se espere humedad alta o lluvias.\n" +
                "5. Prevención:\n" +
                "\n" +
                "Realiza inspecciones frecuentes durante la floración y actúa rápidamente ante los primeros síntomas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'Las orugas son larvas de mariposas o polillas que pueden alimentarse de las hojas, brotes y flores de la azalea de Andalucía. Su daño se observa como mordeduras irregulares, agujeros o incluso la defoliación parcial o total en casos graves. También pueden dejar excrementos oscuros (frass) en las hojas o el sustrato.','1. Inspección y recogida manual:\n" +
                "\n" +
                "Examina la planta, especialmente el envés de las hojas y los brotes tiernos. Si la infestación es baja, retira las orugas manualmente.\n" +
                "2. Poda de partes afectadas:\n" +
                "\n" +
                "Elimina y destruye hojas o brotes muy dañados para reducir la población y ayudar a la recuperación de la planta.\n" +
                "3. Uso de insecticidas biológicos:\n" +
                "\n" +
                "Aplica Bacillus thuringiensis (Bt), un insecticida biológico específico para orugas, siguiendo las instrucciones del fabricante. Es seguro para otros insectos beneficiosos y polinizadores si se usa correctamente.\n" +
                "4. Aplicación de extractos naturales:\n" +
                "\n" +
                "El aceite de neem o infusiones de ajo y chile pueden ayudar a repeler y controlar orugas. Rocía sobre las hojas en las primeras horas del día o al atardecer.\n" +
                "5. Control químico (solo en infestaciones severas):\n" +
                "\n" +
                "Si la plaga es grave y otros métodos no funcionan, utiliza un insecticida específico para orugas, siempre como última opción y siguiendo estrictamente las precauciones del fabricante.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Mantén la planta bien cuidada y revisa regularmente para detectar la aparición temprana de orugas y otros insectos.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'Las quemaduras de las hojas en la azalea de Andalucía suelen aparecer como manchas secas, de color marrón o blanquecino, generalmente en los bordes o puntas de las hojas. Las causas más comunes son la exposición directa y prolongada al sol, riego con agua no adecuada (alta en sales o cal), exceso de fertilizantes, o viento seco y caliente. Las hojas afectadas pueden secarse y caerse prematuramente.','1. Ubicación adecuada:\n" +
                "\n" +
                "Coloca la azalea en un lugar con luz filtrada o semisombra, evitando el sol directo de las horas centrales del día, especialmente en verano.\n" +
                "2. Riego correcto:\n" +
                "\n" +
                "Utiliza agua blanda (preferentemente de lluvia o destilada) y evita mojar las hojas. Asegúrate de que el sustrato esté húmedo pero no encharcado.\n" +
                "3. Evita el exceso de fertilizantes:\n" +
                "\n" +
                "No fertilices en exceso. Usa fertilizantes específicos para azaleas y sigue siempre las dosis recomendadas.\n" +
                "4. Protección contra el viento:\n" +
                "\n" +
                "Si la planta está en exterior, protégela de corrientes de aire caliente y seco que puedan resecar las hojas.\n" +
                "5. Retira hojas dañadas:\n" +
                "\n" +
                "Corta y elimina las hojas con quemaduras para evitar la entrada de enfermedades y mejorar el aspecto de la planta.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Revisa periódicamente el estado de la planta y ajusta la ubicación o el riego si aparecen los primeros síntomas de quemadura.',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'La azalea de Andalucía es una planta acidófila que requiere un equilibrio específico de nutrientes, especialmente hierro, magnesio, nitrógeno y micronutrientes. La deficiencia de alguno de ellos puede manifestarse como amarilleo de hojas (clorosis), crecimiento débil, reducción en la floración, manchas en hojas o bordes secos. Es frecuente si el sustrato es muy calcáreo, pobre en materia orgánica o se riega habitualmente con agua dura (rica en sales y cal).','1. Identifica el tipo de deficiencia:\n" +
                "\n" +
                "Clorosis internerval (hojas amarillas con nervios verdes): suele indicar falta de hierro (clorosis férrica).\n" +
                "Amarilleo general y hojas pequeñas: posible falta de nitrógeno.\n" +
                "Manchas marrones o bordes secos: puede deberse a déficit de potasio o magnesio.\n" +
                "2. Aporta fertilizantes adecuados:\n" +
                "\n" +
                "Usa fertilizantes específicos para plantas acidófilas (azaleas, camelias, rododendros), que contienen hierro quelatado y los nutrientes necesarios.\n" +
                "Sigue las dosis recomendadas y evita la sobre-fertilización.\n" +
                "3. Cambia el sustrato si es necesario:\n" +
                "\n" +
                "Si el suelo es muy alcalino o calcáreo, trasplanta la azalea a un sustrato ácido (pH 4.5–5.5), rico en materia orgánica.\n" +
                "4. Riega con agua blanda:\n" +
                "\n" +
                "Utiliza agua de lluvia, destilada o filtrada. El agua dura puede bloquear la absorción de nutrientes.\n" +
                "5. Aplica quelatos de hierro si hay clorosis:\n" +
                "\n" +
                "Si hay amarilleo de hojas jóvenes, añade quelato de hierro al riego según indicaciones del fabricante.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Realiza abonados regulares en primavera y otoño con productos apropiados para azaleas.\n" +
                "Evita el uso de fertilizantes universales con alto contenido de cal.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'El envejecimiento en la azalea de Andalucía se refiere al proceso natural por el cual la planta, tras varios años, presenta una reducción en la cantidad y calidad de las flores, desarrollo más lento, ramas leñosas y menos brotes nuevos. Las hojas pueden volverse más pequeñas, perder brillo y la planta puede mostrar menor vigor general. Aunque es un proceso normal, se puede ralentizar y mejorar el aspecto de la planta con cuidados específicos.','1. Poda de rejuvenecimiento:\n" +
                "\n" +
                "Realiza una poda drástica a finales del invierno o tras la floración, eliminando ramas viejas y leñosas para estimular la brotación de nuevos tallos.\n" +
                "2. Fertilización adecuada:\n" +
                "\n" +
                "Aplica abonos específicos para plantas acidófilas en primavera y otoño para estimular el crecimiento y la floración.\n" +
                "3. Mejora del sustrato:\n" +
                "\n" +
                "Si la planta está en maceta, trasplanta cada 2-3 años a un sustrato nuevo y ácido. Si está en suelo, añade materia orgánica (turba, compost de hojas) alrededor de la base.\n" +
                "4. Riego y ubicación óptimos:\n" +
                "\n" +
                "Mantén el sustrato húmedo pero bien drenado y ubica la planta en semisombra, protegida del sol directo y del viento.\n" +
                "5. Elimina flores y hojas marchitas:\n" +
                "\n" +
                "Retira regularmente flores y hojas secas para evitar enfermedades y mejorar la apariencia general.\n" +
                "6. División o esquejes:\n" +
                "\n" +
                "Si la planta está muy envejecida y poco recuperable, considera multiplicarla mediante esquejes de las ramas jóvenes y vigorosas.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (60,'Los escarabajos de las hojas (como Otiorhynchus sulcatus, conocido como “picudo de la vid”, o especies de Chrysomelidae) pueden atacar la azalea de Andalucía, alimentándose principalmente del borde de las hojas, produciendo muescas semicirculares o irregulares. Las larvas de algunos de estos escarabajos también pueden dañar las raíces, debilitando la planta. Los daños más visibles son las mordeduras en los bordes de las hojas y, en casos severos, defoliación parcial y debilitamiento general.','1. Inspección nocturna y recogida manual:\n" +
                "\n" +
                "Muchos escarabajos adultos son nocturnos. Revisa la planta por la noche y recoge manualmente los insectos visibles.\n" +
                "2. Trampas y barreras físicas:\n" +
                "\n" +
                "Coloca trampas adhesivas o barreras pegajosas en el tronco y alrededor de la base para dificultar el acceso de los escarabajos.\n" +
                "3. Poda de hojas muy dañadas:\n" +
                "\n" +
                "Elimina las hojas con daños severos para reducir la población de plaga y mejorar la apariencia de la planta.\n" +
                "4. Control biológico:\n" +
                "\n" +
                "Aplica nematodos entomopatógenos (como Steinernema o Heterorhabditis) en el sustrato para combatir las larvas que atacan las raíces.\n" +
                "5. Uso de insecticidas biológicos o químicos (en casos graves):\n" +
                "\n" +
                "Usa productos biológicos como el aceite de neem o piretrinas naturales, aplicando sobre las hojas y tallos.\n" +
                "Si la plaga persiste y es severa, utiliza insecticidas específicos para escarabajos siguiendo las indicaciones del fabricante y evitando su uso durante la floración.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Mantén la planta bien aireada, revisa regularmente y retira hojas y restos vegetales del suelo para evitar refugios de escarabajos y sus larvas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'Las manchas marrones en las hojas de la begonia rex pueden deberse a varias causas, principalmente:\n" +
                "\n" +
                "Enfermedades fúngicas (como botritis, mildiú o manchas foliares por hongos)\n" +
                "Exceso de riego o agua en las hojas\n" +
                "Quemaduras solares o corrientes de aire frío\n" +
                "Deficiencia de nutrientes o acumulación de sales\n" +
                "Daño físico (golpes o rozaduras)','1. Revisa el riego:\n" +
                "\n" +
                "Evita el exceso de agua y no mojes las hojas al regar. La begonia rex prefiere un sustrato ligeramente húmedo, nunca encharcado.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la planta en un lugar bien ventilado, pero sin corrientes de aire frío.\n" +
                "3. Evita el sol directo:\n" +
                "\n" +
                "Las begonias rex deben estar en semisombra o luz filtrada, ya que el sol directo puede provocar quemaduras marrones.\n" +
                "4. Retira las hojas afectadas:\n" +
                "\n" +
                "Corta y desecha las hojas con manchas marrones para evitar la propagación de hongos y mejorar el aspecto de la planta.\n" +
                "5. Aplica fungicida si sospechas de hongos:\n" +
                "\n" +
                "Si las manchas se expanden, tienen bordes amarillos o aspecto húmedo, utiliza un fungicida específico para plantas de interior. Sigue siempre las instrucciones del fabricante.\n" +
                "6. Evita acumulación de sales:\n" +
                "\n" +
                "Si riegas con agua del grifo, alterna con agua destilada o de lluvia para evitar acumulación de sales que puedan causar manchas.\n" +
                "7. No fertilices en exceso:\n" +
                "\n" +
                "Utiliza fertilizantes adecuados para begonias y respeta las dosis recomendadas, ya que el exceso puede provocar quemaduras químicas.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'La pudrición de la hoja en begonia rex suele manifestarse como áreas blandas, oscuras o acuosas en las hojas, que pueden extenderse rápidamente y emitir mal olor. Es causada principalmente por exceso de humedad, riego en exceso, mala ventilación, ataques fúngicos (Botrytis, Pythium, Phytophthora) o bacterianos (Erwinia).','1. Reduce el riego:\n" +
                "\n" +
                "Deja que la parte superior del sustrato se seque antes de volver a regar. No mojes las hojas durante el riego.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la planta en un lugar aireado, pero fuera de corrientes frías. Evita ambientes cerrados y húmedos.\n" +
                "3. Retira partes afectadas:\n" +
                "\n" +
                "Corta y elimina todas las hojas y tallos con síntomas de pudrición usando tijeras desinfectadas. Desecha el material enfermo fuera del compost.\n" +
                "4. Revisa el sustrato y el drenaje:\n" +
                "\n" +
                "Asegúrate de que la maceta tenga buen drenaje y el sustrato sea suelto y aireado. Si el sustrato está empapado, trasplanta la begonia a uno nuevo.\n" +
                "5. Aplica fungicida o bactericida:\n" +
                "\n" +
                "Si la pudrición es causada por hongos o bacterias, utiliza un fungicida sistémico o un bactericida específico para plantas ornamentales según las indicaciones del fabricante.\n" +
                "6. Evita el hacinamiento:\n" +
                "\n" +
                "No coloques muchas plantas juntas; deja espacio entre ellas para mejorar el flujo de aire y reducir la humedad.\n" +
                "7. Prevención:\n" +
                "\n" +
                "Mantén la begonia rex en semisombra, con sustrato ácido y bien drenado, y evita el exceso de humedad ambiental y en el sustrato.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'La Begonia rex es principalmente apreciada por sus hojas, ya que sus flores suelen ser pequeñas y poco vistosas. Sin embargo, si tu begonia rex presenta flores marchitas, esto puede deberse a causas naturales (ciclo normal de la floración), estrés ambiental, exceso o falta de riego, deficiencia de nutrientes, o problemas de hongos si la floración es anormalmente corta o las flores se pudren antes de secarse.','1. Elimina las flores marchitas:\n" +
                "\n" +
                "Corta las flores secas o marchitas con tijeras limpias para evitar el desarrollo de hongos y mejorar el aspecto de la planta.\n" +
                "2. Revisa el riego:\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. Evita mojar las flores y hojas durante el riego.\n" +
                "3. Ambiente adecuado:\n" +
                "\n" +
                "Coloca la begonia rex en un lugar con buena luz indirecta y sin corrientes de aire frío. Evita la exposición directa al sol.\n" +
                "4. Fertilización equilibrada:\n" +
                "\n" +
                "Usa un fertilizante específico para plantas de hoja decorativa (no para floración), ya que el exceso de fósforo puede provocar un desarrollo anormal de las flores.\n" +
                "5. Control de plagas y hongos:\n" +
                "\n" +
                "Si las flores marchitan y se pudren rápidamente, revisa la presencia de hongos y aplica un fungicida suave si es necesario.\n" +
                "6. Recuerda:\n" +
                "\n" +
                "Es normal que las flores de la begonia rex tengan una vida corta. La belleza principal de esta planta está en sus hojas.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'La begonia rex, aunque es principalmente decorativa por sus hojas, puede florecer ocasionalmente. Es común que, después de la floración, algunas partes de la planta (especialmente los tallos florales y hojas cercanas) presenten marchitamiento o debilidad. Esto suele deberse a causas naturales, aunque ciertas condiciones pueden agravar el proceso:\n" +
                "\n" +
                "Estrés por floración (la planta gasta energía en florecer y puede debilitarse)\n" +
                "Riego inadecuado (exceso o falta de agua durante la floración)\n" +
                "Deficiencia de nutrientes después de la floración\n" +
                "Ambiente seco o con poca luz tras la floración\n" +
                "Envejecimiento natural de hojas/tallos que acompañaron a la floración','1. Retira flores y tallos marchitos:\n" +
                "\n" +
                "Corta los restos florales y tallos secos con tijeras limpias para evitar desarrollo de hongos y mejorar el aspecto general.\n" +
                "2. Mantén un riego adecuado:\n" +
                "\n" +
                "Después de la floración, revisa la humedad del sustrato. Evita tanto el exceso como la sequía; la tierra debe estar ligeramente húmeda.\n" +
                "3. Fertiliza suavemente:\n" +
                "\n" +
                "Aplica un fertilizante equilibrado para plantas de hoja decorativa (rica en potasio y bajo en fósforo) justo después de la floración para ayudar a la recuperación.\n" +
                "4. Mejora la ventilación y la luz:\n" +
                "\n" +
                "Coloca la planta en un lugar bien iluminado, con luz indirecta y buena ventilación.\n" +
                "5. Evita el estrés ambiental:\n" +
                "\n" +
                "Protege la planta de corrientes frías, calor excesivo o cambios bruscos de ambiente.\n" +
                "6. Es normal cierta pérdida de vigor:\n" +
                "\n" +
                "Es habitual que algunas hojas o tallos envejezcan y mueran tras la floración: simplemente retíralos para mantener la salud y el aspecto de la planta.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'El escarabajo de las hojas (como los crisomélidos o el otiorrinco) puede dañar la begonia rex alimentándose de las hojas. El daño típico son mordeduras irregulares o muescas en los bordes de las hojas, dejando agujeros o áreas parcialmente consumidas. En algunos casos, las larvas pueden atacar las raíces, debilitando aún más la planta.','1. Inspección y recogida manual:\n" +
                "\n" +
                "Revisa la planta, especialmente por la noche (cuando muchos escarabajos están activos) y retira los insectos que veas.\n" +
                "2. Poda de hojas dañadas:\n" +
                "\n" +
                "Corta y elimina las hojas con daños severos para reducir la población de plaga y mejorar el aspecto de la planta.\n" +
                "3. Trampas y barreras:\n" +
                "\n" +
                "Coloca trampas adhesivas cerca de la maceta o barreras pegajosas en el tallo para dificultar el acceso de los escarabajos.\n" +
                "4. Control biológico:\n" +
                "\n" +
                "Si hay presunción de larvas en el sustrato, puedes aplicar nematodos entomopatógenos (por ejemplo, del género Steinernema) que atacan las larvas.\n" +
                "5. Uso de insecticidas biológicos o químicos (solo en infestaciones graves):\n" +
                "\n" +
                "Rocía aceite de neem o piretrinas naturales sobre las hojas. Si la infestación es severa, usa un insecticida específico para escarabajos y sigue las instrucciones del fabricante.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Mantén la planta bien aireada y revisa regularmente. Retira los restos vegetales del sustrato para evitar refugios de escarabajos y larvas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'El envejecimiento en la begonia rex se manifiesta cuando la planta, tras varios años, comienza a perder vigor: las hojas nuevas son más pequeñas, las hojas viejas se secan y caen con más frecuencia, los tallos se vuelven leñosos o alargados y la planta en general se ve menos densa y atractiva. Esto es un proceso natural, pero puede acelerarse por estrés, falta de nutrientes, sustrato agotado o malas condiciones de cultivo.','1. Poda de rejuvenecimiento:\n" +
                "\n" +
                "Corta los tallos viejos, alargados o leñosos dejando solo los brotes más vigorosos. Esto estimula la aparición de nuevos brotes y hojas.\n" +
                "2. Propagación por esquejes:\n" +
                "\n" +
                "Aprovecha los tallos sanos para hacer esquejes y obtener nuevas plantas más jóvenes y vigorosas. La begonia rex enraíza bien en sustrato húmedo o incluso en agua.\n" +
                "3. Renueva el sustrato:\n" +
                "\n" +
                "Trasplanta la begonia rex cada 1-2 años a un sustrato fresco, suelto y rico en materia orgánica. Un sustrato agotado limita el crecimiento.\n" +
                "4. Fertilización adecuada:\n" +
                "\n" +
                "Aplica abonos equilibrados, preferiblemente para plantas de hoja decorativa, en dosis bajas durante la temporada de crecimiento.\n" +
                "5. Mejora el ambiente:\n" +
                "\n" +
                "Asegura buena luz indirecta, humedad ambiental alta y temperatura estable. Evita corrientes de aire y cambios bruscos.\n" +
                "6. Elimina hojas y tallos en mal estado:\n" +
                "\n" +
                "Retira regularmente las hojas y tallos secos o enfermos para evitar la proliferación de hongos y mejorar el aspecto general.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'Los puntos negros en las hojas de la begonia rex suelen deberse a infecciones fúngicas (como el hongo Cercospora o Alternaria), bacterianas (Erwinia), daños por plagas diminutas, o a quemaduras causadas por gotas de agua al sol. Los puntos pueden comenzar como pequeñas manchas oscuras y, si no se tratan, se expanden o se rodean de halos amarillos.','1. Aísla la planta:\n" +
                "\n" +
                "Si tienes otras plantas, separa la begonia rex afectada para evitar el contagio.\n" +
                "2. Retira hojas afectadas:\n" +
                "\n" +
                "Corta con tijeras limpias y desinfectadas todas las hojas que tengan puntos negros. Deséchalas en la basura (no en compost).\n" +
                "3. Control de riego:\n" +
                "\n" +
                "Evita mojar las hojas al regar, ya que la humedad superficial favorece los hongos y bacterias.\n" +
                "4. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la planta en un lugar bien ventilado, sin corrientes frías, para reducir la humedad ambiental.\n" +
                "5. Aplica fungicida o bactericida:\n" +
                "\n" +
                "Usa un fungicida de amplio espectro para plantas ornamentales si sospechas de hongos. Si el avance es muy rápido o hay zonas blandas, considera un bactericida.\n" +
                "6. Evita el sol directo:\n" +
                "\n" +
                "La luz intensa y el agua en las hojas pueden causar quemaduras y manchas.\n" +
                "7. Prevención:\n" +
                "\n" +
                "Mantén la planta libre de restos vegetales, revisa regularmente y evita el hacinamiento.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'La deficiencia de nutrientes en la begonia rex se manifiesta principalmente en el aspecto de las hojas: pérdida de color (clorosis), bordes secos o quemados, manchas amarillas o marrones, crecimiento lento, hojas pequeñas y tallos débiles. Las carencias más comunes incluyen nitrógeno, hierro, magnesio y potasio, pero el exceso de fertilizante también puede causar daños similares.','1. Identificación de síntomas: - Clorosis general (hojas amarillas): Puede indicar falta de nitrógeno. - Hojas jóvenes amarillas con nervaduras verdes: Suele ser deficiencia de hierro. - Manchas marrones y bordes secos: Carencia de potasio o quemaduras por exceso de sales. - Hojas pequeñas y poco crecimiento: Puede ser falta de fósforo o nitrógeno.\n" +
                "\n" +
                "2. Fertilización adecuada:\n" +
                "\n" +
                "Utiliza fertilizantes equilibrados para plantas de hoja decorativa (tipo 20-20-20 o similar) en dosis bajas y regulares durante primavera y verano.\n" +
                "Si sospechas de una deficiencia específica, usa un abono con microelementos (hierro, magnesio, zinc, manganeso).\n" +
                "3. Riego correcto:\n" +
                "\n" +
                "Evita el uso exclusivo de agua del grifo dura, ya que puede bloquear la absorción de ciertos nutrientes. Alterna con agua destilada o de lluvia si es posible.\n" +
                "4. Renueva el sustrato:\n" +
                "\n" +
                "Si la planta lleva mucho tiempo en la misma maceta, trasplanta a un sustrato fresco, rico en materia orgánica y bien aireado.\n" +
                "5. Evita el exceso de fertilizante:\n" +
                "\n" +
                "No sobredosifiques; el exceso puede “quemar” las raíces y provocar síntomas similares a la carencia.\n" +
                "6. Observa la evolución:\n" +
                "\n" +
                "Tras aplicar las correcciones, observa la planta durante 2-3 semanas para ver mejoras. Las hojas dañadas no se recuperan, pero las nuevas deberán salir sanas.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'El “tizón del pétalo” (también conocido como botritis o podredumbre gris) es una enfermedad fúngica frecuente en begonias, causada principalmente por el hongo Botrytis cinerea. Suele aparecer en flores, pero también puede afectar hojas y tallos, especialmente en ambientes húmedos y con poca ventilación.\n" +
                "Síntomas típicos:\n" +
                "\n" +
                "Manchas marrones, acuosas o negruzcas en los pétalos y partes florales.\n" +
                "Decaimiento y pudrición rápida de las flores.\n" +
                "Aparición de moho grisáceo sobre las zonas afectadas.\n" +
                "Puede extenderse a hojas y tallos cercanos si no se controla.','1. Retira flores y partes afectadas:\n" +
                "\n" +
                "Corta y desecha todas las flores y hojas con síntomas de tizón o podredumbre, usando tijeras limpias y desinfectadas.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la begonia rex en un lugar con buen flujo de aire, evitando el hacinamiento con otras plantas.\n" +
                "3. Evita el exceso de humedad:\n" +
                "\n" +
                "Reduce el riego y evita mojar flores y hojas. Riega solo la base.\n" +
                "4. Aplica fungicida:\n" +
                "\n" +
                "Usa un fungicida específico para Botrytis o de amplio espectro siguiendo las indicaciones del fabricante.\n" +
                "5. Limpieza y prevención:\n" +
                "\n" +
                "Retira restos vegetales caídos y mantén el área limpia.\n" +
                "No pulverices agua sobre la planta durante brotes de la enfermedad.\n" +
                "6. Ambiente adecuado:\n" +
                "\n" +
                "Mantén la temperatura estable (18-22°C) y humedad relativa controlada.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (70,'Las orugas pueden alimentarse de las hojas de la begonia rex, dejando agujeros irregulares, bordes mordidos y, en infestaciones graves, incluso esqueletizando las hojas. Suelen encontrarse en el envés de las hojas o escondidas en el sustrato durante el día.','1. Inspección y eliminación manual:\n" +
                "\n" +
                "Examina cuidadosamente el envés de las hojas y el sustrato. Retira las orugas con la mano o con pinzas (usa guantes si lo prefieres).\n" +
                "2. Poda de hojas dañadas:\n" +
                "\n" +
                "Corta y elimina las hojas muy dañadas para evitar estrés en la planta y reducir la población de orugas.\n" +
                "3. Lava la planta:\n" +
                "\n" +
                "Puedes lavar la begonia rex con agua a presión moderada para desalojar orugas pequeñas.\n" +
                "4. Control biológico:\n" +
                "\n" +
                "Introduce depredadores naturales (como crisopas o avispas parasitoides) si el problema es recurrente y tienes un jardín o invernadero.\n" +
                "5. Insecticidas ecológicos:\n" +
                "\n" +
                "Aplica Bacillus thuringiensis (Bt), un insecticida biológico específico para orugas, siguiendo las instrucciones del fabricante.\n" +
                "También puedes usar jabón potásico o aceites vegetales, aunque son menos específicos.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Revisa regularmente la planta, sobre todo en primavera y verano.\n" +
                "Mantén el área alrededor de la maceta limpia y libre de restos vegetales donde las orugas puedan esconderse o pupar.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'Las manchas marrones en las hojas de la begonia alas de ángel pueden tener diversas causas, entre las más frecuentes están:\n" +
                "\n" +
                "Exceso o falta de riego: Puede provocar manchas marrones secas (falta de agua) o blandas (exceso).\n" +
                "Quemaduras solares: Si la planta recibe sol directo, aparecen manchas marrones secas.\n" +
                "Enfermedades fúngicas/bacterianas: Hongos como Botrytis, Cercospora o bacterias pueden causar manchas marrones rodeadas de halos amarillos o bordes irregulares.\n" +
                "Bajas temperaturas o corrientes de aire frío: Causan manchas marrones en las puntas y bordes de las hojas.\n" +
                "Deficiencia de nutrientes: A veces genera manchas marrones dispersas.','1. Identifica la causa:\n" +
                "\n" +
                "¿Las manchas son blandas o secas? ¿Aparecen tras riego, tras exposición al sol o después de un cambio de temperatura?\n" +
                "¿Hay otros síntomas como hojas amarillas, caída de hojas, mal olor, o presencia de moho?\n" +
                "2. Ajusta el riego:\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado.\n" +
                "Riega solo cuando la capa superficial esté seca.\n" +
                "3. Evita sol directo y corrientes frías:\n" +
                "\n" +
                "Coloca la begonia en luz brillante indirecta.\n" +
                "Protege de temperaturas bajo 15 °C y corrientes de aire.\n" +
                "4. Retira hojas afectadas:\n" +
                "\n" +
                "Corta y desecha las hojas con manchas severas usando tijeras desinfectadas.\n" +
                "5. Aplica fungicida si sospechas de hongos:\n" +
                "\n" +
                "Si las manchas se expanden rápidamente o hay moho, usa un fungicida específico.\n" +
                "6. Mejora la ventilación y revisa la humedad:\n" +
                "\n" +
                "Evita ambientes demasiado húmedos y mantén buena circulación de aire.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'La pudrición de las hojas en la begonia alas de ángel suele estar provocada por exceso de humedad, riego encharcado, mala ventilación, o infecciones fúngicas/bacterianas como Botrytis, Pythium, o Erwinia. Los síntomas incluyen zonas blandas, húmedas y marrón oscuro o negras en las hojas, a veces con mal olor; estas áreas se expanden rápidamente y pueden afectar tallos y otras hojas.','1. Retira las hojas afectadas:\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas para cortar todas las hojas con signos de pudrición. Deséchalas en la basura, no en compost.\n" +
                "2. Reduce el riego:\n" +
                "\n" +
                "Deja secar la capa superficial del sustrato antes de volver a regar.\n" +
                "Asegúrate de que la maceta drene correctamente y nunca dejes agua estancada en el plato.\n" +
                "3. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la planta en un lugar aireado, pero sin corrientes frías directas.\n" +
                "4. Aplica fungicida o bactericida:\n" +
                "\n" +
                "Si la pudrición avanza rápido o hay zonas blandas y con mal olor, aplica un fungicida de amplio espectro (o bactericida si sospechas de bacterias) siguiendo las indicaciones del fabricante.\n" +
                "5. Evita mojar las hojas:\n" +
                "\n" +
                "Riega solo la base de la planta, evitando mojar el follaje.\n" +
                "6. Revisa el sustrato:\n" +
                "\n" +
                "Si la pudrición es severa, considera trasplantar a un sustrato nuevo, bien aireado y drenante.\n" +
                "7. Prevención:\n" +
                "\n" +
                "Mantén la begonia en luz indirecta brillante, sin exposición directa al sol.\n" +
                "No agrupar demasiado las plantas para evitar exceso de humedad ambiental.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'Las flores marchitas en la begonia alas de ángel son un proceso normal después de la floración. Sin embargo, una marchitez prematura puede estar asociada a factores ambientales, riego inadecuado, falta de nutrientes o ataque de plagas/enfermedades. La floración en esta begonia es estacional y, tras el ciclo, las flores tienden a secarse y caer.\n" +
                "\n" +
                "Causas posibles:\n" +
                "\n" +
                "Final natural del ciclo floral: Las flores duran pocos días o semanas.\n" +
                "Estrés por riego: Tanto el exceso como la falta de agua pueden provocar marchitez rápida.\n" +
                "Calor o luz solar directa: Exposición a temperaturas altas o sol directo acelera el deterioro.\n" +
                "Corrientes de aire/frío: Cambios bruscos dañan las flores.\n" +
                "Falta de nutrientes: Especialmente durante la floración.\n" +
                "Plagas o enfermedades: Hongos y bacterias pueden provocar marchitez y podredumbre.','1. Retira flores marchitas:\n" +
                "\n" +
                "Usa tijeras limpias para cortar las flores secas o marchitas, favoreciendo nuevas floraciones y evitando enfermedades.\n" +
                "2. Riego adecuado:\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, sin encharcar. Deja secar la capa superficial antes de volver a regar.\n" +
                "3. Ubicación correcta:\n" +
                "\n" +
                "Coloca la planta en luz indirecta brillante, lejos de corrientes de aire y protegido de sol directo.\n" +
                "4. Fertilización moderada:\n" +
                "\n" +
                "Aplica fertilizante equilibrado (tipo 10-10-10 o específico para plantas de flor) durante la época de floración.\n" +
                "5. Vigila plagas y enfermedades:\n" +
                "\n" +
                "Observa si hay manchas, pudrición o insectos en flores y hojas. Si es así, toma medidas específicas.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'El marchitamiento de hojas o tallos tras la floración en la begonia alas de ángel es un fenómeno relativamente común y, en la mayoría de los casos, es parte del ciclo natural de la planta. Sin embargo, si el marchitamiento es excesivo, rápido, o afecta a gran parte de la planta, puede estar relacionado con otros factores de estrés ambiental, riego, o enfermedades.\n" +
                "\n" +
                "Causas posibles:\n" +
                "\n" +
                "Ciclo natural: Después de florecer, la planta dirige su energía a otras partes y algunas hojas o tallos pueden marchitarse y caer.\n" +
                "Estrés por floración: La floración consume muchos nutrientes y agua, pudiendo debilitar temporalmente la planta.\n" +
                "Exceso o falta de riego: Tras la floración, la planta puede necesitar menos agua; el exceso puede causar pudrición y el defecto, deshidratación.\n" +
                "Deficiencia de nutrientes: La floración agota los nutrientes, y si no se reponen, la planta puede mostrar marchitez.\n" +
                "Enfermedades: Hongos o bacterias pueden aprovechar debilidades tras la floración para atacar hojas y tallos.','1. Retira flores y partes marchitas:\n" +
                "\n" +
                "Corta flores secas y hojas/tallos marchitos con tijeras limpias para evitar enfermedades y estimular nuevos brotes.\n" +
                "2. Ajusta el riego:\n" +
                "\n" +
                "Reduce la frecuencia tras la floración, asegurando que el sustrato esté solo ligeramente húmedo.\n" +
                "3. Fertiliza moderadamente:\n" +
                "\n" +
                "Aplica un fertilizante equilibrado (tipo 10-10-10 o específico para plantas de hoja) para reponer los nutrientes perdidos en la floración.\n" +
                "4. Mejora el ambiente:\n" +
                "\n" +
                "Mantén la planta en luz indirecta brillante y fuera de corrientes de aire o sol directo.\n" +
                "5. Observa signos de enfermedad:\n" +
                "\n" +
                "Si el marchitamiento es acompañado de manchas, pudrición o mal olor, trata la planta con fungicida y revisa raíces y sustrato.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'Los escarabajos de las hojas son pequeños insectos que se alimentan del follaje de las plantas, dejando orificios irregulares, bordes mordidos y, en ocasiones, manchas negras (excrementos). En begonias alas de ángel, el daño suele ser visible como agujeros o zonas traslúcidas en las hojas, y a veces se pueden encontrar los escarabajos adultos o sus larvas en el envés de las hojas, sobre todo por la noche.','1. Inspección y eliminación manual:\n" +
                "\n" +
                "Revisa las hojas, especialmente el envés, y recoge manualmente los escarabajos y larvas. Hazlo preferentemente al atardecer o de noche, cuando suelen estar más activos.\n" +
                "2. Poda de hojas muy dañadas:\n" +
                "\n" +
                "Retira y desecha las hojas con daños severos para reducir la población y el estrés en la planta.\n" +
                "3. Lava la planta:\n" +
                "\n" +
                "Puedes rociar la planta con agua a presión moderada para desalojar escarabajos pequeños y larvas.\n" +
                "4. Insecticidas naturales o ecológicos:\n" +
                "\n" +
                "Aplica jabón potásico o aceite de neem en el envés y haz varias aplicaciones semanales hasta controlar la plaga.\n" +
                "Si la infestación es severa, puedes usar insecticidas específicos para escarabajos, siempre siguiendo las indicaciones y evitando productos muy agresivos.\n" +
                "5. Prevención:\n" +
                "\n" +
                "Mantén la zona alrededor de la planta limpia de restos vegetales y humedad excesiva.\n" +
                "Revisa regularmente la planta, sobre todo en primavera y verano, épocas de mayor actividad de estos insectos.\n" +
                "6. Control biológico:\n" +
                "\n" +
                "Si tienes jardín, fomenta la presencia de depredadores naturales como aves, mariquitas o mantis religiosas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'El envejecimiento es un proceso natural en todas las plantas, incluida la begonia alas de ángel. Con el paso del tiempo, las hojas más viejas pueden perder color, volverse amarillas, secarse en los bordes, desarrollar manchas marrones y, finalmente, caer. Los tallos también pueden volverse leñosos o menos vigorosos. Este proceso es normal y parte del ciclo vital de la planta.\n" +
                "\n" +
                "Características típicas del envejecimiento:\n" +
                "\n" +
                "Hojas inferiores amarillas, secas o caídas.\n" +
                "Bordes marrones o puntas secas en hojas viejas.\n" +
                "Tallos alargados o “desnudos” en la base.\n" +
                "Floración menos abundante con los años.','1. Retira hojas y tallos viejos:\n" +
                "\n" +
                "Corta con tijeras limpias las hojas y tallos que se vean secos, amarillos o dañados para que la planta dedique energía a las partes sanas.\n" +
                "2. Fomenta el rebrote:\n" +
                "\n" +
                "Realiza podas ligeras para estimular el crecimiento de nuevos brotes desde la base o desde las yemas laterales.\n" +
                "3. Renueva el sustrato:\n" +
                "\n" +
                "Cada 1-2 años trasplanta la begonia a un sustrato fresco y nutritivo para revitalizar el crecimiento.\n" +
                "4. Fertiliza en época de crecimiento:\n" +
                "\n" +
                "Aplica un fertilizante equilibrado en primavera-verano para ayudar a la formación de hojas nuevas.\n" +
                "5. Propaga si lo deseas:\n" +
                "\n" +
                "Puedes cortar tallos sanos y enraizarlos en agua o sustrato para obtener plantas jóvenes y vigorosas.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'La aparición de puntos negros en las hojas de la begonia alas de ángel puede deberse a varias causas, pero las más comunes son:\n" +
                "\n" +
                "Enfermedades fúngicas o bacterianas: Hongos como Cercospora, Alternaria o bacterias pueden causar pequeños puntos negros, a veces rodeados de halos amarillos o marrones.\n" +
                "Exceso de humedad: El ambiente demasiado húmedo favorece la proliferación de patógenos.\n" +
                "Plagas: Algunos insectos pueden dejar pequeñas marcas o excrementos negros, aunque suelen ir acompañadas de otros daños.\n" +
                "Daño físico o quemaduras: Gotas de agua al sol directo pueden “quemar” la hoja y dejar puntos oscuros.','1. Revisa si los puntos se expanden o tienen halos:\n" +
                "\n" +
                "Si los puntos negros se agrandan o tienen un borde amarillo/marrón, probablemente es un hongo o bacteria.\n" +
                "2. Retira las hojas afectadas:\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas para cortar hojas con muchos puntos negros y evitar la propagación.\n" +
                "3. Mejora la ventilación y controla la humedad:\n" +
                "\n" +
                "Mantén la planta en un lugar aireado y con buen drenaje. Evita mojar las hojas al regar.\n" +
                "4. Aplica fungicida o bactericida:\n" +
                "\n" +
                "Si los puntos se multiplican, utiliza un fungicida de amplio espectro o bactericida siguiendo las instrucciones del fabricante.\n" +
                "5. Observa por plagas:\n" +
                "\n" +
                "Examina el envés de las hojas y los tallos por presencia de insectos. Si ves pequeños bichos o excrementos, aplica un insecticida adecuado.\n" +
                "6. Evita el estrés ambiental:\n" +
                "\n" +
                "No expongas la planta a sol directo ni a cambios bruscos de temperatura, y utiliza agua sin cal para el riego.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'La deficiencia de nutrientes es una causa común de problemas en las begonias alas de ángel cultivadas en maceta. El sustrato agotado, el riego excesivo que lava minerales, o la falta de fertilización pueden provocar síntomas como:\n" +
                "\n" +
                "Hojas amarillas (clorosis), especialmente entre las venas o en hojas viejas.\n" +
                "Crecimiento lento o débil.\n" +
                "Manchas marrones o bordes secos en las hojas.\n" +
                "Hojas pequeñas o deformes.\n" +
                "Poca o nula floración.\n" +
                "Principales nutrientes y sus síntomas de deficiencia:\n" +
                "\n" +
                "Nitrógeno (N): Hojas viejas amarillas, planta débil, crecimiento lento.\n" +
                "Fósforo (P): Hojas oscuras o con tonos rojizos/morados, raíces poco desarrolladas, escasa floración.\n" +
                "Potasio (K): Bordes marrones/secos en hojas, manchas y pérdida de vigor.\n" +
                "Hierro (Fe): Hojas jóvenes amarillas pero venas verdes (clorosis intervenal).\n" +
                "Magnesio (Mg): Amarilleo entre venas en hojas medias a viejas.','1. Fertiliza adecuadamente:\n" +
                "\n" +
                "Usa un fertilizante equilibrado (por ejemplo, 10-10-10 o 20-20-20) o específico para plantas de interior de hoja decorativa.\n" +
                "Aplica cada 3-4 semanas en primavera y verano, reduciendo en otoño e invierno.\n" +
                "2. Renueva el sustrato si es viejo:\n" +
                "\n" +
                "Trasplanta cada 1-2 años a un sustrato fresco, aireado y con buen drenaje.\n" +
                "3. Evita el exceso de riego:\n" +
                "\n" +
                "El agua en exceso puede lavar los nutrientes. Riega solo cuando la capa superficial del sustrato esté seca.\n" +
                "4. Observa y corrige según síntomas:\n" +
                "\n" +
                "Si notas clorosis intervenal en hojas jóvenes, usa fertilizante rico en hierro o quelatos de hierro.\n" +
                "Bordes secos o manchas marrones: aumenta potasio.\n" +
                "Hojas amarillas en general: aumenta nitrógeno.\n" +
                "5. No sobrefertilices:\n" +
                "\n" +
                "El exceso de fertilizante puede quemar raíces y empeorar los síntomas. Sigue siempre las dosis recomendadas.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'El tizón del pétalo es una enfermedad fúngica que afecta especialmente a las flores de las begonias. Es causada principalmente por hongos del género Botrytis (Botrytis cinerea), aunque también pueden intervenir otros patógenos. Se manifiesta por la aparición de manchas marrones o negras en los pétalos, los cuales se marchitan y pueden pudrirse rápidamente. En condiciones de alta humedad, puede verse un moho grisáceo sobre los pétalos y otras partes afectadas.\n" +
                "\n" +
                "Causas y condiciones favorables:\n" +
                "\n" +
                "Alta humedad y mala ventilación.\n" +
                "Riego sobre las flores u hojas.\n" +
                "Restos de flores viejas no retiradas.\n" +
                "Espacios cerrados o agrupamiento de plantas.','1. Retira flores afectadas:\n" +
                "\n" +
                "Corta y desecha todas las flores y partes dañadas con tijeras limpias y desinfectadas.\n" +
                "2. Mejora la ventilación:\n" +
                "\n" +
                "Coloca la begonia en un lugar bien aireado y evita el hacinamiento de plantas.\n" +
                "3. Evita mojar flores y hojas:\n" +
                "\n" +
                "Riega al pie de la planta, no por aspersión.\n" +
                "4. Controla la humedad ambiental:\n" +
                "\n" +
                "Mantén el ambiente menos húmedo, especialmente en interiores o invernaderos.\n" +
                "5. Aplica fungicida:\n" +
                "\n" +
                "Si el problema persiste, utiliza un fungicida específico para botrytis/tizón, preferiblemente de amplio espectro, siguiendo las indicaciones del fabricante.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Retira flores y hojas marchitas con regularidad.\n" +
                "No dejes restos vegetales sobre el sustrato.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (80,'Las orugas son larvas de algunas mariposas o polillas que pueden atacar la begonia alas de ángel, alimentándose de sus hojas y flores. Suelen dejar agujeros grandes e irregulares, hojas parcialmente comidas y, a veces, excrementos oscuros (“bolitas negras”) sobre las hojas o el sustrato. Las orugas son más activas por la noche o en ambientes húmedos y protegidos.','1. Inspección y eliminación manual:\n" +
                "\n" +
                "Revisa la planta, especialmente el envés de las hojas y entre tallos, para localizar y retirar las orugas. Hazlo preferentemente al atardecer.\n" +
                "2. Poda de hojas muy dañadas:\n" +
                "\n" +
                "Corta y desecha las hojas o flores con daños severos para evitar que la planta siga perdiendo energía.\n" +
                "3. Limpieza general:\n" +
                "\n" +
                "Retira excrementos y restos vegetales del sustrato para reducir el riesgo de reinfestación.\n" +
                "4. Insecticidas biológicos:\n" +
                "\n" +
                "Aplica Bacillus thuringiensis (Bt), un insecticida biológico específico para orugas, seguro para plantas y mascotas. Sigue las instrucciones del fabricante.\n" +
                "También puedes usar aceite de neem como preventivo.\n" +
                "5. Insecticidas químicos (último recurso):\n" +
                "\n" +
                "Si la infestación es muy severa y no puedes controlarla de otra manera, usa un insecticida específico para orugas, siempre siguiendo las indicaciones y tomando precauciones.\n" +
                "6. Prevención:\n" +
                "\n" +
                "Revisa tu planta regularmente, sobre todo en primavera y verano, para detectar orugas jóvenes antes de que causen daños importantes.\n" +
                "Mantén la begonia alejada de plantas infestadas y procura buena ventilación.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'Las manchas marrones en las hojas o flores de la begonia tuberosa suelen observarse como áreas secas, a veces rodeadas de un borde amarillento o blanquecino. Pueden aparecer en hojas viejas o jóvenes y, si no se tratan, pueden expandirse y afectar el aspecto y la salud de la planta.','Elimina las hojas afectadas:\n" +
                "Corta con tijeras limpias todas las hojas (o partes) con manchas marrones para evitar que la enfermedad se extienda.\n" +
                "\n" +
                "Mejora la ventilación y el drenaje:\n" +
                "Coloca la begonia en un lugar aireado, sin amontonarla con otras plantas. Usa sustrato suelto y maceta con buen drenaje.\n" +
                "\n" +
                "Riega correctamente:\n" +
                "Riega solo cuando el sustrato esté seco al tacto en la parte superior. Evita mojar hojas y flores al regar.\n" +
                "\n" +
                "Evita el sol directo:\n" +
                "Las begonias tuberosas prefieren luz indirecta o sombra luminosa.\n" +
                "\n" +
                "Usa fungicida si es necesario:\n" +
                "Si las manchas se extienden rápidamente, aplica un fungicida de amplio espectro siguiendo las indicaciones del producto.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'La pudrición de las hojas se manifiesta como zonas blandas, húmedas y oscuras en hojas y tallos, a veces acompañadas de mal olor. Puede afectar primero las hojas más bajas y avanzar rápidamente si las condiciones no mejoran. Es un problema serio que puede comprometer toda la planta si no se controla a tiempo.','Elimina hojas y tallos afectados:\n" +
                "Corta todas las partes blandas, podridas o con mal olor usando tijeras limpias y desinfectadas. Desecha el material retirado lejos de otras plantas.\n" +
                "\n" +
                "Revisa el sustrato y el drenaje:\n" +
                "Cambia el sustrato si está muy húmedo o huele mal. Usa un sustrato aireado y coloca la maceta en una bandeja que permita escurrir bien.\n" +
                "\n" +
                "Reduce el riego:\n" +
                "Riega solo cuando la parte superior del sustrato esté seca. Evita dejar agua estancada en el plato bajo la maceta.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la planta en un lugar con buena circulación de aire, pero sin corrientes frías.\n" +
                "\n" +
                "Aplica fungicida o bactericida:\n" +
                "Si la pudrición avanza rápido, utiliza un producto específico para hongos y bacterias siguiendo instrucciones del fabricante.\n" +
                "\n" +
                "Evita mojar las hojas:\n" +
                "Riega al pie de la planta, no por aspersión.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'Las flores de la begonia tuberosa pueden marchitarse prematuramente, mostrando pétalos caídos, secos o con manchas marrones. En ocasiones, la flor se decolora o se desprende antes de tiempo. Este problema afecta la apariencia y la duración de la floración, y puede deberse tanto a factores ambientales como a cuidados inadecuados.','Retira flores marchitas o dañadas:\n" +
                "Elimina las flores secas o deterioradas cortándolas con tijeras limpias. Esto favorece nuevas floraciones y previene enfermedades.\n" +
                "\n" +
                "Riega adecuadamente:\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. Evita tanto el exceso como la falta de agua, ya que ambos pueden provocar marchitez.\n" +
                "\n" +
                "Evita el sol directo y el calor excesivo:\n" +
                "Coloca la begonia tuberosa en un lugar con luz indirecta o semisombra, lejos de corrientes de aire caliente o focos de calor.\n" +
                "\n" +
                "Fertiliza en época de crecimiento:\n" +
                "Usa un fertilizante equilibrado cada 2-4 semanas durante la época de floración para fortalecer las flores y prolongar su duración.\n" +
                "\n" +
                "Revisa por plagas y enfermedades:\n" +
                "Examina las flores y hojas en busca de insectos (como trips, pulgones) o signos de hongos. Si detectas problemas, aplica el tratamiento adecuado.\n" +
                "\n" +
                "No mojes las flores al regar:\n" +
                "Riega al pie de la planta para evitar que el agua sobre los pétalos acelere la descomposición de las flores.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'Tras la floración, la begonia tuberosa puede mostrar un marchitamiento progresivo de hojas, tallos y flores. Es común que la planta comience a perder vigor, las hojas se pongan amarillas y se sequen, y los tallos colapsen. Este proceso suele coincidir con el final de la temporada de crecimiento y es parte del ciclo natural de la begonia tuberosa, que entra en un periodo de reposo o dormancia.','Reduce el riego gradualmente:\n" +
                "Disminuye la frecuencia de riego a medida que la planta se marchita. Cuando el follaje esté completamente seco, suspende el riego por completo.\n" +
                "\n" +
                "Permite que la planta entre en reposo:\n" +
                "Deja que hojas y tallos se sequen de forma natural. No cortes el follaje verde; espera a que esté totalmente seco y fácil de retirar.\n" +
                "\n" +
                "Retira el material seco:\n" +
                "Una vez que todo el follaje esté seco, córtalo o retíralo con cuidado para evitar focos de hongos.\n" +
                "\n" +
                "Extrae y almacena el tubérculo:\n" +
                "Si cultivas la begonia tuberosa en maceta, extrae el tubérculo, límpialo y guárdalo en un lugar fresco, seco y oscuro (por ejemplo, en turba seca o virutas de madera) hasta la próxima temporada.\n" +
                "\n" +
                "Evita fertilizar durante el reposo:\n" +
                "No apliques fertilizantes hasta que notes nuevos brotes al inicio de la siguiente primavera.\n" +
                "\n" +
                "Vigila por hongos:\n" +
                "Revisa el tubérculo durante el almacenamiento y elimina cualquier parte blanda o con moho.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'Los escarabajos de las hojas son pequeños insectos que pueden atacar a la begonia tuberosa, alimentándose de sus hojas. Suelen dejar orificios irregulares, bordes mordidos o manchas en las hojas. A veces se pueden ver los escarabajos adultos (de colores variados, normalmente marrón, verde o metálico) o sus larvas en el envés de las hojas o sobre el sustrato. Una infestación severa puede debilitar la planta y afectar su crecimiento y floración.','Elimina manualmente los escarabajos y larvas:\n" +
                "Revisa las hojas, especialmente el envés, y retira los insectos y huevos a mano o con un pincel húmedo.\n" +
                "\n" +
                "Poda hojas muy dañadas:\n" +
                "Corta y desecha las hojas que estén demasiado afectadas para evitar que los escarabajos se sigan alimentando y la planta pierda energía.\n" +
                "\n" +
                "Aplica jabón potásico o aceite de neem:\n" +
                "Pulveriza con jabón potásico o una solución de aceite de neem sobre hojas y tallos, especialmente en el envés, repitiendo cada 5-7 días hasta controlar la plaga.\n" +
                "\n" +
                "Utiliza insecticida específico si la infestación es grave:\n" +
                "Si la plaga persiste y es severa, utiliza un insecticida apto para plantas ornamentales, siguiendo siempre las recomendaciones del fabricante.\n" +
                "\n" +
                "Refuerza la prevención:\n" +
                "Mantén la planta en buen estado de salud, revisa periódicamente las hojas y separa la begonia de otras plantas infestadas. Mantén buena ventilación y elimina restos vegetales.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'El envejecimiento en la begonia tuberosa se manifiesta cuando las hojas más antiguas empiezan a amarillear, secarse por los bordes y finalmente caen de la planta. Es un proceso natural que ocurre a medida que la planta completa su ciclo de crecimiento y desarrollo. Además, después de varias temporadas, algunas begonias pueden mostrar menos vigor o floración reducida si no reciben los cuidados adecuados.','Retira hojas viejas y secas:\n" +
                "Elimina con cuidado las hojas que se han secado o amarilleado para mantener la planta limpia y favorecer la aparición de brotes nuevos.\n" +
                "\n" +
                "Realiza una poda ligera:\n" +
                "Poda las puntas débiles o tallos muy envejecidos para estimular el crecimiento de brotes jóvenes y más fuertes.\n" +
                "\n" +
                "Renueva el sustrato periódicamente:\n" +
                "Cambia parte o todo el sustrato al menos una vez al año, preferentemente al iniciar la temporada de crecimiento, para asegurar nutrientes frescos.\n" +
                "\n" +
                "Fertiliza durante la época de crecimiento:\n" +
                "Usa un fertilizante equilibrado cada 2-4 semanas en primavera y verano para mantener la vitalidad y estimular la floración.\n" +
                "\n" +
                "Propaga si la planta está muy envejecida:\n" +
                "Si notas que la planta pierde vigor después de varias temporadas, puedes multiplicarla dividiendo el tubérculo o sembrando nuevas plantas a partir de brotes sanos.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'La aparición de puntos negros en las hojas de la begonia tuberosa suele presentarse como pequeñas manchas redondeadas, oscuras y bien definidas, que pueden ir aumentando de tamaño con el tiempo. A veces, estas manchas tienen un halo amarillento alrededor y pueden unirse formando áreas más grandes. Los puntos negros pueden aparecer tanto en hojas jóvenes como en adultas y, si no se controlan, pueden debilitar la planta y afectar su desarrollo.','Retira las hojas afectadas:\n" +
                "Corta con tijeras limpias todas las hojas o partes de hojas donde observes puntos negros para frenar la propagación.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Ubica la begonia en un lugar con buena circulación de aire y evita el hacinamiento con otras plantas.\n" +
                "\n" +
                "Evita mojar las hojas al regar:\n" +
                "Riega siempre al pie de la planta y no sobre las hojas, ya que la humedad superficial facilita la aparición de manchas.\n" +
                "\n" +
                "Aplica fungicida si los puntos se extienden:\n" +
                "Usa un fungicida específico para plantas ornamentales siguiendo las instrucciones del fabricante para detener la expansión de las manchas.\n" +
                "\n" +
                "Revisa la planta regularmente:\n" +
                "Inspecciona hojas nuevas y viejas periódicamente para detectar a tiempo nuevos puntos negros y actuar rápidamente.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'La deficiencia de nutrientes en la begonia tuberosa se manifiesta con síntomas como hojas amarillas, crecimiento lento, floración escasa, bordes de hojas secos o manchas irregulares. Las hojas jóvenes o viejas pueden verse afectadas, y en casos severos la planta puede presentar tallos débiles y un aspecto general poco saludable. Cada nutriente esencial suele mostrar síntomas particulares, pero en general la planta se ve menos vigorosa y pierde color.','Fertiliza regularmente en época de crecimiento:\n" +
                "Utiliza un fertilizante equilibrado (por ejemplo, 10-10-10 o 20-20-20) cada 2-4 semanas durante la primavera y el verano, siguiendo siempre las indicaciones del producto.\n" +
                "\n" +
                "Observa síntomas específicos:\n" +
                "Si las hojas amarillean desde la base (nitrógeno), presentan manchas marrones (potasio) o tienen bordes deformados (calcio/magnesio), ajusta el fertilizante para suplir el nutriente específico.\n" +
                "\n" +
                "Evita el exceso de fertilizante:\n" +
                "No sobre-fertilices, ya que puede causar daños como puntas quemadas o acumulación de sales en el sustrato. Si esto ocurre, riega abundantemente para lavar el sustrato.\n" +
                "\n" +
                "Renueva el sustrato si es necesario:\n" +
                "Si la planta lleva mucho tiempo en la misma tierra, cambia parte o todo el sustrato para restablecer la fertilidad y mejorar la estructura.\n" +
                "\n" +
                "Usa agua de buena calidad:\n" +
                "Evita regar con agua muy dura o con alto contenido de sales, ya que puede interferir en la absorción de nutrientes.\n" +
                "\n" +
                "Revisa el drenaje:\n" +
                "Un sustrato encharcado dificulta la absorción de nutrientes. Asegúrate de que la maceta drene correctamente.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'El tizón del pétalo es una enfermedad causada principalmente por hongos (como Botrytis cinerea) que afecta las flores de la begonia tuberosa. Se manifiesta como manchas marrones o negruzcas que suelen comenzar en los bordes de los pétalos, extendiéndose rápidamente y provocando la marchitez y descomposición de la flor. En condiciones de alta humedad, puede observarse un moho grisáceo sobre los pétalos afectados. Si no se trata, puede llegar a afectar hojas, tallos y otras flores.','Elimina las flores y partes afectadas:\n" +
                "Retira y desecha todas las flores y pétalos que presenten manchas o signos de tizón, utilizando tijeras limpias y desinfectadas. Esto reduce la fuente de esporas y frena la propagación.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia en un sitio con buena circulación de aire, separada de otras plantas, para reducir la humedad ambiental.\n" +
                "\n" +
                "Evita el exceso de humedad:\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. No pulverices agua sobre las flores ni mojes los pétalos al regar.\n" +
                "\n" +
                "Aplica fungicida específico:\n" +
                "Usa un fungicida adecuado para Botrytis u hongos similares, siguiendo las indicaciones del fabricante. Repite la aplicación si las condiciones de humedad persisten.\n" +
                "\n" +
                "Retira restos vegetales:\n" +
                "Limpia regularmente los restos de flores y hojas caídas alrededor de la planta para evitar que el hongo sobreviva y se propague.\n" +
                "\n" +
                "Revisa frecuentemente la planta:\n" +
                "Inspecciona a menudo las flores y hojas para detectar síntomas tempranos y actuar rápidamente ante cualquier signo de enfermedad.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (90,'Las orugas son larvas de mariposas o polillas que pueden alimentarse de las hojas, flores y brotes de la begonia tuberosa. Se reconocen por su forma alargada y movimiento ondulante. Suelen dejar hojas con agujeros irregulares, bordes mordidos o incluso consumir completamente hojas jóvenes y brotes tiernos. Una infestación severa puede debilitar la planta y afectar la floración.','Elimina manualmente las orugas:\n" +
                "Revisa las hojas, sobre todo el envés y los brotes, y retira las orugas a mano. Puedes usar guantes o un pincel suave.\n" +
                "\n" +
                "Poda las partes muy dañadas:\n" +
                "Corta y desecha las hojas o tallos que hayan sido gravemente afectados para evitar estrés y facilitar la recuperación de la planta.\n" +
                "\n" +
                "Aplica bioinsecticida (Bacillus thuringiensis):\n" +
                "Utiliza Bacillus thuringiensis (Bt), un bioinsecticida específico para orugas que es seguro para las personas, mascotas y la mayoría de los insectos benéficos. Pulveriza según las instrucciones del fabricante.\n" +
                "\n" +
                "Usa trampas o barreras:\n" +
                "Coloca trampas de feromonas o barreras físicas si tienes muchas plantas y el problema es recurrente.\n" +
                "\n" +
                "Mantén la vigilancia:\n" +
                "Inspecciona regularmente la planta para detectar nuevas orugas lo antes posible y evitar que el daño se extienda.\n" +
                "\n" +
                "Evita insecticidas de amplio espectro si no es necesario:\n" +
                "Los productos químicos fuertes pueden afectar a polinizadores y fauna útil; úsalos solo si la infestación es muy grave y no hay otra alternativa.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'Las manchas marrones en la begonia flaviflora suelen aparecer como áreas irregulares, secas o húmedas, en hojas, tallos o flores. Estas manchas pueden tener bordes definidos o difusos y, dependiendo de la causa, pueden ir acompañadas de amarillamiento, debilitamiento del tejido o incluso caída prematura de la parte afectada. Las causas más comunes incluyen hongos, quemaduras solares, exceso de riego o deficiencia de nutrientes.','Identifica la causa:\n" +
                "Observa si las manchas son secas y aparecen tras exposición al sol (quemadura solar), con aspecto húmedo y blando (hongos), o si hay signos de pudrición y mal olor (exceso de riego).\n" +
                "\n" +
                "Retira las partes afectadas:\n" +
                "Usa tijeras limpias y desinfectadas para cortar hojas, tallos o flores con manchas marrones. Elimina los restos lejos de otras plantas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia flaviflora en un lugar bien aireado, evitando la acumulación de humedad en el follaje.\n" +
                "\n" +
                "Evita mojar las hojas al regar:\n" +
                "Aplica el agua directamente al sustrato, nunca sobre las hojas.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Riega solo cuando la capa superficial del sustrato esté seca. Si el sustrato permanece húmedo mucho tiempo, reduce la frecuencia.\n" +
                "\n" +
                "Aplica fungicida si es necesario:\n" +
                "Si las manchas se extienden y sospechas de hongos, utiliza un fungicida específico para plantas ornamentales siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Protege del sol directo:\n" +
                "Si las manchas aparecen tras exposición solar, mueve la planta a un lugar con luz filtrada o sombra parcial.\n" +
                "\n" +
                "Revisa la nutrición:\n" +
                "Si la planta presenta también hojas amarillas o crecimiento deficiente, considera fertilizar con un abono equilibrado.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'La pudrición de las hojas en la begonia flaviflora se manifiesta como zonas blandas, húmedas y oscuras en las hojas, que pueden volverse marrones o negras y desprender un olor desagradable. Estas áreas suelen expandirse rápidamente, haciendo que las hojas se colapsen, se desintegren o caigan al mínimo contacto. Suele estar provocada por hongos o bacterias, favorecidos por el exceso de humedad, mal drenaje o mala ventilación.','Retira las hojas afectadas:\n" +
                "Corta y desecha de inmediato todas las hojas que presenten signos de pudrición, usando tijeras limpias y desinfectadas para evitar la propagación.\n" +
                "\n" +
                "Reduce el riego:\n" +
                "Deja secar la capa superficial del sustrato antes de volver a regar. Evita el exceso de agua y asegúrate de que la maceta tenga buen drenaje.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia flaviflora en un lugar bien aireado y evita que las hojas se mantengan mojadas mucho tiempo.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Al regar, aplica el agua directamente al sustrato y no sobre las hojas. Es importante proteger las hojas de la humedad persistente.\n" +
                "\n" +
                "Aplica un fungicida o bactericida:\n" +
                "Si la pudrición avanza o afecta varias hojas, utiliza un producto específico para hongos o bacterias, siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Limpia y desinfecta herramientas y macetas:\n" +
                "Después de manipular hojas enfermas, limpia y desinfecta todas las herramientas y superficies para evitar reinfecciones.\n" +
                "\n" +
                "Revisa el sustrato:\n" +
                "Si la pudrición es severa, considera cambiar el sustrato por uno fresco y bien drenante para reducir la presencia de patógenos.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'La flor marchita en la begonia flaviflora se manifiesta cuando los pétalos pierden color y firmeza, se tornan blandos o secos, y finalmente se caen. Las flores pueden presentar bordes marrones, volverse translúcidas o arrugarse prematuramente. Esto puede deberse a causas naturales (final del ciclo de floración), falta o exceso de riego, temperaturas extremas, corrientes de aire, enfermedades fúngicas o plagas.','Retira las flores marchitas:\n" +
                "Corta las flores secas o dañadas con tijeras limpias y desinfectadas para estimular nuevas floraciones y prevenir la propagación de enfermedades.\n" +
                "\n" +
                "Revisa el riego:\n" +
                "Asegúrate de que la planta reciba agua suficiente, pero sin encharcar el sustrato. La begonia flaviflora prefiere un sustrato ligeramente húmedo, sin exceso de agua.\n" +
                "\n" +
                "Controla la temperatura:\n" +
                "Coloca la planta en un lugar donde no sufra cambios bruscos de temperatura ni esté expuesta a corrientes de aire frío o calor extremo.\n" +
                "\n" +
                "Evita mojar las flores:\n" +
                "Al regar, procura no salpicar los pétalos, ya que la humedad directa puede favorecer enfermedades y acelerar la marchitez.\n" +
                "\n" +
                "Revisa la presencia de plagas o enfermedades:\n" +
                "Examina si hay hongos, insectos o manchas extrañas en flores y hojas. Si detectas problemas, aplica el tratamiento adecuado (fungicidas, insecticidas o retiro manual de plagas).\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Fertiliza cada 3-4 semanas durante la época de floración con un abono equilibrado para plantas de flor.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'La marchitación después de la floración en la begonia flaviflora es un proceso natural en el que las flores, tras completar su ciclo, pierden firmeza, se decoloran y finalmente se secan y caen. En algunos casos, la planta puede mostrar un descenso general en vigor, con hojas decaídas o amarillentas, especialmente si ha invertido mucha energía en la floración. Sin embargo, una marchitación excesiva o rápida, acompañada de pérdida de hojas o tallos débiles, puede indicar problemas de riego, nutrición o estrés ambiental.','Retira flores y hojas marchitas:\n" +
                "Elimina regularmente las flores y hojas secas o marchitas con tijeras limpias para estimular nuevas floraciones y evitar enfermedades.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Después de la floración, reduce ligeramente el riego, permitiendo que el sustrato se seque un poco entre riegos, pero sin dejar que la planta se deshidrate.\n" +
                "\n" +
                "Fertiliza moderadamente:\n" +
                "Aplica un fertilizante equilibrado (preferiblemente bajo en nitrógeno) cada 3-4 semanas para ayudar a la planta a recuperarse y estimular el crecimiento de nuevas hojas.\n" +
                "\n" +
                "Proporciona buena ventilación y luz:\n" +
                "Mantén la begonia flaviflora en un lugar luminoso, con luz filtrada, y buena circulación de aire, evitando el sol directo intenso.\n" +
                "\n" +
                "Evita el estrés:\n" +
                "No trasplantes ni cambies de lugar la planta bruscamente durante la fase de recuperación, y protégela de corrientes de aire frío o calor extremo.\n" +
                "\n" +
                "Observa la planta:\n" +
                "Si la marchitación se acompaña de manchas, pudrición o síntomas de plaga, trata el problema específico.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'El escarabajo de las hojas es un insecto que se alimenta de las hojas de la begonia flaviflora, dejando orificios irregulares, áreas mordidas o incluso esqueletizando la hoja (dejando solo las nervaduras). Los adultos y sus larvas pueden estar presentes en el envés de las hojas. La infestación severa debilita la planta, reduce la fotosíntesis y puede afectar la floración y el crecimiento general.','Inspección regular:\n" +
                "Revisa frecuentemente el envés y el haz de las hojas para detectar escarabajos adultos, larvas o huevos.\n" +
                "\n" +
                "Retiro manual:\n" +
                "Si la población es baja, retira los escarabajos y larvas a mano y deséchalos lejos del jardín.\n" +
                "\n" +
                "Limpieza de hojas:\n" +
                "Lava las hojas con agua a presión suave para desalojar insectos y huevos.\n" +
                "\n" +
                "Uso de insecticidas orgánicos:\n" +
                "Aplica insecticidas naturales como jabón potásico o aceite de neem, siguiendo las indicaciones del fabricante. Estos productos son efectivos y menos dañinos para el entorno.\n" +
                "\n" +
                "Insecticidas químicos (solo en casos severos):\n" +
                "Si la plaga es grave y no responde a métodos orgánicos, emplea un insecticida sistémico específico para escarabajos de hoja, siguiendo estrictamente las instrucciones y precauciones del producto.\n" +
                "\n" +
                "Favorece enemigos naturales:\n" +
                "Fomenta la presencia de aves o insectos benéficos (como mariquitas) que pueden ayudar a controlar la población del escarabajo.\n" +
                "\n" +
                "Mantén la planta sana:\n" +
                "Asegura un riego y nutrición adecuados para que la begonia flaviflora resista mejor los daños por plagas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'El envejecimiento en la begonia flaviflora es un proceso natural que ocurre cuando la planta, o algunas de sus partes (hojas, tallos o flores), han completado su ciclo de vida. Los síntomas habituales incluyen hojas y flores que se vuelven amarillas o marrones, se marchitan, pierden firmeza y finalmente caen. La planta puede mostrar un crecimiento más lento, menor producción de flores y una apariencia menos vigorosa. El envejecimiento no es una enfermedad, pero puede acentuarse por factores de estrés como falta de nutrientes, riego inadecuado, poca luz o macetas demasiado pequeñas.','Retira hojas y flores viejas:\n" +
                "Elimina regularmente las partes marchitas o secas con tijeras limpias para estimular el crecimiento de nuevas hojas y flores.\n" +
                "\n" +
                "Renuévala con poda ligera:\n" +
                "Puedes realizar una poda suave para quitar tallos envejecidos y favorecer la aparición de brotes nuevos.\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Fertiliza cada 3-4 semanas durante la época de crecimiento con un abono equilibrado para plantas de flor.\n" +
                "\n" +
                "Revisa el sustrato y la maceta:\n" +
                "Si la begonia lleva mucho tiempo en la misma maceta, considera trasplantarla a un recipiente un poco más grande con sustrato nuevo y rico en materia orgánica.\n" +
                "\n" +
                "Asegura buena iluminación y ventilación:\n" +
                "Coloca la planta en un lugar con luz filtrada y buena circulación de aire para retrasar el envejecimiento de las hojas.\n" +
                "\n" +
                "Mantén un riego adecuado:\n" +
                "Evita tanto el exceso como la falta de agua, ajustando el riego a las necesidades de la planta.\n" +
                "\n" +
                "Rejuvenecimiento por esquejes:\n" +
                "Si la planta está muy envejecida, puedes tomar esquejes sanos para reproducirla y obtener una begonia joven y vigorosa.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'El “punto negro” en la begonia flaviflora suele referirse a la aparición de pequeñas manchas negras o puntos oscuros en hojas, tallos o incluso flores. Estos puntos pueden ser aislados o numerosos, y a menudo son señal de infección por hongos (como Alternaria o Cercospora), presencia de plagas diminutas (como trips o ácaros), o daño físico/mecánico en el tejido. Si no se atiende, estos puntos pueden crecer y provocar necrosis del tejido circundante, debilitando la planta.','Inspección minuciosa:\n" +
                "Examina con cuidado las hojas (anverso y reverso), tallos y flores para identificar si los puntos negros son superficiales (hongo) o pueden moverse (plaga).\n" +
                "\n" +
                "Retira las partes afectadas:\n" +
                "Corta y elimina hojas o partes muy afectadas, usando tijeras limpias y desinfectadas para evitar la propagación de enfermedades.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Asegúrate de que la planta esté en un lugar bien aireado, ya que la humedad estancada favorece hongos y plagas.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Riega directo al sustrato y nunca sobre las hojas.\n" +
                "\n" +
                "Aplica tratamiento adecuado:\n" +
                "\n" +
                "Si sospechas de hongos: Usa un fungicida específico para plantas ornamentales, siguiendo las instrucciones del fabricante.\n" +
                "Si observas plagas diminutas: Aplica un insecticida suave (por ejemplo, jabón potásico o aceite de neem).\n" +
                "Limpia regularmente:\n" +
                "Retira restos vegetales caídos y mantén la zona alrededor de la planta limpia.\n" +
                "\n" +
                "Monitorea la planta:\n" +
                "Observa en los días siguientes si aparecen más puntos negros y repite el tratamiento si es necesario.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'La deficiencia de nutrientes en la begonia flaviflora se manifiesta mediante síntomas como hojas amarillas (clorosis), crecimiento lento, bordes de hojas secos o marrones, manchas pálidas, hojas pequeñas, floración escasa o deformidades en brotes nuevos. Las deficiencias más comunes incluyen nitrógeno (amarillamiento general), potasio (bordes marrones), magnesio (manchas amarillas entre nervaduras), y hierro (amarilleo en hojas jóvenes).','Identifica el síntoma principal:\n" +
                "Observa si el amarillamiento, manchas o bordes secos afectan primero a hojas viejas o nuevas, ya que esto ayuda a saber qué nutriente falta.\n" +
                "\n" +
                "Fertiliza adecuadamente:\n" +
                "Aplica un fertilizante equilibrado para plantas de flor (NPK), siguiendo las dosis recomendadas. Si el síntoma es severo, usa un fertilizante soluble de absorción rápida.\n" +
                "\n" +
                "Mejora el sustrato:\n" +
                "Si la planta lleva mucho tiempo en la misma maceta, renueva parte del sustrato con uno fresco y rico en materia orgánica.\n" +
                "\n" +
                "Evita el exceso de riego:\n" +
                "El riego excesivo puede lavar los nutrientes del sustrato. Riega solo cuando la capa superficial esté seca.\n" +
                "\n" +
                "Aporta micronutrientes:\n" +
                "Si después de fertilizar persisten los síntomas, usa un fertilizante que incluya micronutrientes (hierro, magnesio, zinc).\n" +
                "\n" +
                "Evita la sobre-fertilización:\n" +
                "Un exceso de fertilizante puede causar toxicidad. Si notas puntas quemadas tras abonar, riega abundantemente para diluir el exceso.\n" +
                "\n" +
                "Revisa el pH del sustrato:\n" +
                "Un pH muy alto o muy bajo puede bloquear la absorción de nutrientes. La begonia flaviflora prefiere un pH ligeramente ácido (5.5 a 6.5).',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'El tizón del pétalo es una enfermedad fúngica que afecta principalmente a las flores de la begonia flaviflora. Se manifiesta como manchas marrones o negruzcas en los pétalos, que pueden expandirse rápidamente haciendo que la flor se marchite y se caiga prematuramente. En condiciones de alta humedad, las manchas pueden presentar un borde acuoso y, en ocasiones, un moho grisáceo visible (Botrytis cinerea es el hongo más frecuente). Las flores afectadas pierden atractivo y la enfermedad puede diseminarse a hojas y tallos si no se controla.','Retira las flores afectadas:\n" +
                "Corta y elimina rápidamente las flores con signos de tizón utilizando tijeras limpias y desinfectadas para evitar la propagación.\n" +
                "\n" +
                "Reduce la humedad ambiental:\n" +
                "Asegura buena ventilación alrededor de la begonia flaviflora y evita el exceso de humedad. No mojes las flores ni las hojas al regar.\n" +
                "\n" +
                "Evita el riego por aspersión:\n" +
                "Riega directamente al sustrato y nunca por encima de la planta.\n" +
                "\n" +
                "Aplica fungicida específico:\n" +
                "Utiliza un fungicida recomendado para Botrytis o enfermedades fúngicas en plantas ornamentales, siguiendo siempre las instrucciones del fabricante.\n" +
                "\n" +
                "Mantén el área limpia:\n" +
                "Retira restos de flores y hojas caídas, ya que pueden servir de reservorio para el hongo.\n" +
                "\n" +
                "Espacia las plantas:\n" +
                "Si tienes varias begonias, mantenlas separadas para favorecer la circulación de aire y reducir la propagación del hongo.\n" +
                "\n" +
                "Prevención:\n" +
                "Evita fertilizar en exceso con nitrógeno, ya que favorece tejidos más blandos y susceptibles; utiliza fertilización equilibrada.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (100,'Las orugas son larvas de mariposas o polillas que se alimentan de las hojas de la begonia flaviflora, dejando agujeros irregulares, bordes mordidos o incluso consumiendo hojas enteras. Suelen encontrarse en el envés de las hojas o escondidas entre el follaje. Si la infestación es severa, pueden debilitar considerablemente la planta, afectar su crecimiento y reducir la floración.','Inspección frecuente:\n" +
                "Revisa regularmente las hojas, especialmente el envés, para detectar orugas, huevos o excrementos (pequeñas bolitas oscuras).\n" +
                "\n" +
                "Retiro manual:\n" +
                "Si la infestación es leve, retira las orugas a mano (usando guantes) y deséchalas lejos del jardín.\n" +
                "\n" +
                "Limpieza del follaje:\n" +
                "Lava las hojas con agua a presión suave para desalojar orugas pequeñas y huevos.\n" +
                "\n" +
                "Uso de insecticidas biológicos:\n" +
                "Aplica Bacillus thuringiensis (Bt), un insecticida biológico seguro para plantas ornamentales que ataca únicamente a las orugas. Rocía siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Control químico (solo en casos graves):\n" +
                "Si la infestación es muy severa y no responde a métodos biológicos o manuales, emplea un insecticida específico para orugas, siguiendo estrictamente las precauciones del producto.\n" +
                "\n" +
                "Fomenta enemigos naturales:\n" +
                "Favorece la presencia de aves, avispas parasitoides y otros depredadores de orugas en el entorno.\n" +
                "\n" +
                "Mantén la planta sana:\n" +
                "Un buen riego, nutrición y ventilación ayudarán a la begonia flaviflora a recuperarse mejor de los daños.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'Las manchas marrones en la begonia flor de azúcar suelen aparecer en hojas, tallos o incluso flores. Estas manchas pueden ser pequeñas o grandes, con bordes definidos o difusos, y a veces rodeadas de un halo amarillento. Las causas pueden incluir enfermedades fúngicas (como antracnosis, botritis o mancha foliar), quemaduras por sol directo, daño físico, exceso de fertilizante, o problemas de riego (tanto por exceso como por déficit).','Diagnóstico visual:\n" +
                "Observa la forma, tamaño y ubicación de las manchas. Si son irregulares, húmedas o con borde amarillento, probablemente se trata de un hongo. Si la mancha está seca y en el borde de la hoja, puede ser quemadura o falta de agua.\n" +
                "\n" +
                "Retira las hojas afectadas:\n" +
                "Corta y desecha todas las hojas o partes con manchas, usando tijeras esterilizadas, para evitar la propagación de enfermedades.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la planta en un sitio bien aireado, sin corrientes frías, y separa de otras plantas para evitar contagios.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Riega directamente el sustrato y no sobre las hojas.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. Deja secar la capa superficial antes de volver a regar.\n" +
                "\n" +
                "Aplica fungicida si es necesario:\n" +
                "Si las manchas se extienden, utiliza un fungicida para plantas ornamentales siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Evita el exceso de fertilizante:\n" +
                "Sigue las dosis recomendadas, ya que el exceso puede causar manchas.\n" +
                "\n" +
                "Protege del sol directo:\n" +
                "Si la planta recibe sol fuerte, muévela a un lugar con luz filtrada, ya que la begonia flor de azúcar prefiere luminosidad suave.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'La pudrición de las hojas en la begonia flor de azúcar se manifiesta como zonas blandas, oscuras o acuosas en el tejido de las hojas. Estas áreas pueden volverse marrones o negras, con mal olor, y a menudo se extienden rápidamente, provocando la caída de las hojas afectadas. Esta pudrición suele deberse a exceso de humedad, riego encharcado, mala ventilación, o infecciones fúngicas/bacterianas (como Botrytis, Pythium o bacterias del género Erwinia).','Retira hojas afectadas:\n" +
                "Corta y elimina cuidadosamente las hojas podridas usando tijeras limpias y desinfectadas, para frenar la propagación.\n" +
                "\n" +
                "Reduce el riego:\n" +
                "Deja secar la capa superficial del sustrato antes de regar nuevamente. Evita los encharcamientos.\n" +
                "\n" +
                "Mejora el drenaje:\n" +
                "Asegúrate de que la maceta tenga orificios de drenaje y el sustrato sea suelto, ligero y aireado. Si es necesario, trasplanta a un sustrato nuevo.\n" +
                "\n" +
                "Aumenta la ventilación:\n" +
                "Coloca la planta en un sitio bien ventilado, sin corrientes frías. Una buena circulación de aire reduce la humedad y el riesgo de enfermedades.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Riega solo el sustrato, nunca sobre las hojas.\n" +
                "\n" +
                "Usa fungicidas o bactericidas:\n" +
                "Si la pudrición persiste o es severa, aplica un fungicida o bactericida específico para plantas ornamentales, siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Limpieza constante:\n" +
                "Retira hojas y restos vegetales caídos alrededor de la planta para evitar focos de infección.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'La flor marchita en la begonia flor de azúcar se manifiesta como pétalos caídos, secos, descoloridos o con pérdida de firmeza poco después de la floración. Es normal que las flores envejezcan y se marchiten tras completar su ciclo, pero si ocurre prematuramente, puede deberse a factores como falta o exceso de agua, luz inadecuada, calor excesivo, humedad baja, plagas (como trips) o enfermedades fúngicas (como botritis).','Retira flores marchitas:\n" +
                "Corta y elimina las flores secas o marchitas para estimular nuevas floraciones y evitar el desarrollo de hongos.\n" +
                "\n" +
                "Revisa el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. Evita que la planta pase sed o esté en un sustrato empapado.\n" +
                "\n" +
                "Asegura buena iluminación:\n" +
                "Coloca la begonia en un sitio con luz abundante pero filtrada (evita el sol directo fuerte).\n" +
                "\n" +
                "Regula la temperatura y la humedad:\n" +
                "Evita exponer la planta a calor extremo o corrientes de aire frío. Si el ambiente es seco, aumenta la humedad ambiental (sin mojar las flores).\n" +
                "\n" +
                "Observa signos de plagas o enfermedades:\n" +
                "Si hay manchas, moho o insectos en las flores, considera aplicar un tratamiento adecuado (fungicida o insecticida suave).\n" +
                "\n" +
                "Fertiliza moderadamente:\n" +
                "Usa un abono equilibrado cada 3-4 semanas en época de crecimiento, evitando el exceso de nitrógeno.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'El marchitamiento después de la floración en la begonia flor de azúcar es bastante común y, en muchos casos, es un proceso natural: tras completar el ciclo, las flores y a veces las hojas cercanas pueden perder turgencia, secarse y caer. Sin embargo, si el marchitamiento es excesivo, prematuro o afecta a toda la planta, puede indicar problemas de riego, agotamiento nutricional, baja humedad ambiental, estrés por temperatura, enfermedades (como botritis) o daños en las raíces.','Retira flores y hojas marchitas:\n" +
                "Elimina regularmente las flores y hojas secas para evitar la acumulación de hongos y estimular el crecimiento de nuevos brotes.\n" +
                "\n" +
                "Revisa el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando tanto el encharcamiento como la sequía. El exceso o falta de agua puede causar marchitamiento.\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Después de la floración, aplica un fertilizante equilibrado para ayudar a la recuperación y promover nuevos brotes.\n" +
                "\n" +
                "Aumenta la humedad ambiental:\n" +
                "Si el ambiente es seco, coloca la planta sobre una bandeja con guijarros y agua (sin que la base de la maceta toque el agua) o usa un humidificador.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Ubica la begonia en un lugar bien ventilado, pero protegido de corrientes de aire frío o calor intenso.\n" +
                "\n" +
                "Evita el sol directo fuerte:\n" +
                "Coloca la planta en un sitio con luz filtrada o semisombra, ya que la exposición directa al sol puede resecarla rápidamente.\n" +
                "\n" +
                "Observa si hay signos de enfermedad:\n" +
                "Si notas manchas, moho gris, o pudrición, aplica un fungicida suave y elimina las partes afectadas.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'El escarabajo de las hojas es un insecto que se alimenta de las hojas de la begonia flor de azúcar, dejando orificios irregulares, manchas traslúcidas o bordes mordidos. Tanto los adultos como las larvas pueden causar daños. Si la infestación es severa, la planta puede debilitarse, reducir su crecimiento y perder atractivo ornamental.','Inspección frecuente:\n" +
                "Revisa las hojas (superficie y envés) para detectar la presencia de escarabajos adultos, larvas o huevos.\n" +
                "\n" +
                "Retiro manual:\n" +
                "Si la infestación es leve, retira los escarabajos y larvas a mano (con guantes) y deséchalos lejos de otras plantas.\n" +
                "\n" +
                "Limpieza del follaje:\n" +
                "Lava las hojas con agua a presión suave para eliminar insectos y huevos.\n" +
                "\n" +
                "Uso de insecticidas biológicos:\n" +
                "Aplica jabón potásico o aceite de neem, que son seguros para plantas ornamentales y afectan a los escarabajos sin dañar la planta.\n" +
                "\n" +
                "Control químico (solo si es necesario):\n" +
                "Si la plaga es severa, utiliza un insecticida específico para escarabajos siguiendo cuidadosamente las instrucciones del fabricante y las medidas de seguridad.\n" +
                "\n" +
                "Mantén la planta sana:\n" +
                "Una begonia bien nutrida y en condiciones óptimas de riego y ventilación será más resistente al ataque de plagas.\n" +
                "\n" +
                "Limpieza regular:\n" +
                "Retira hojas muy dañadas y limpia el entorno de la maceta para evitar refugio de plagas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'El envejecimiento en la begonia flor de azúcar es el proceso natural en el que las hojas y flores van perdiendo vigor, se tornan amarillas, marrones o secas y eventualmente caen. Las hojas más viejas (generalmente en la base de la planta) son las primeras en mostrar estos signos. El envejecimiento es normal, pero si ocurre de forma acelerada o afecta a gran parte de la planta, puede estar relacionado con estrés ambiental, falta de nutrientes, enfermedades, plagas, o problemas de riego.','Retira hojas y flores viejas:\n" +
                "Corta regularmente las hojas y flores secas o amarillentas para mantener la planta sana y estimular nuevos brotes.\n" +
                "\n" +
                "Mantén el sustrato adecuado:\n" +
                "Usa un sustrato rico en materia orgánica, bien aireado y con buen drenaje.\n" +
                "\n" +
                "Nutrición equilibrada:\n" +
                "Fertiliza cada 3-4 semanas en época de crecimiento con un abono equilibrado para plantas de flor, evitando excesos.\n" +
                "\n" +
                "Riego correcto:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando encharcamientos y sequías prolongadas.\n" +
                "\n" +
                "Buena iluminación:\n" +
                "La begonia flor de azúcar prefiere luz abundante pero indirecta. Evita el sol directo fuerte, que acelera el envejecimiento.\n" +
                "\n" +
                "Vigila plagas y enfermedades:\n" +
                "Inspecciona regularmente para detectar signos de plagas o manchas extrañas. Trata a tiempo si aparecen problemas.\n" +
                "\n" +
                "Renueva la planta si es necesario:\n" +
                "Si la begonia envejece mucho o pierde gran parte de sus hojas, puedes podar y estimular nuevos brotes, o reproducirla por esquejes para rejuvenecerla.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'La aparición de puntos negros en las hojas, tallos o flores de la begonia flor de azúcar puede deberse a varias causas:\n" +
                "\n" +
                "Hongos: Manchas negras pequeñas suelen relacionarse con infecciones fúngicas (como la mancha negra o antracnosis).\n" +
                "Plagas: Algunos insectos pueden dejar excrementos o causar lesiones negras puntuales.\n" +
                "Daño físico: Golpes o heridas pueden oxidarse y volverse negras.\n" +
                "Condiciones ambientales: Humedad excesiva y poca ventilación favorecen la aparición de hongos.','Inspecciona la planta:\n" +
                "Examina si los puntos negros están hundidos, rodeados de halos amarillos, o si hay presencia de moho o insectos.\n" +
                "\n" +
                "Retira partes afectadas:\n" +
                "Corta y elimina hojas o flores con puntos negros usando tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia en un lugar con aireación adecuada y evita el hacinamiento con otras plantas.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Riega directamente el sustrato, no las hojas, para reducir la humedad superficial.\n" +
                "\n" +
                "Aplica fungicida:\n" +
                "Si los puntos se extienden o aparecen nuevos, utiliza un fungicida para plantas ornamentales siguiendo las indicaciones del fabricante.\n" +
                "\n" +
                "Revisa por plagas:\n" +
                "Si notas pequeños insectos, trata la planta con jabón potásico o aceite de neem.\n" +
                "\n" +
                "Limpia el área:\n" +
                "Remueve restos vegetales y mantén la zona alrededor de la maceta limpia para evitar focos de infección.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'La deficiencia de nutrientes ocurre cuando la begonia flor de azúcar no recibe los minerales esenciales para su crecimiento y floración. Esto puede deberse a un sustrato pobre, riego excesivo que arrastra nutrientes, macetas demasiado pequeñas o falta de fertilización. Los síntomas varían según el nutriente faltante:\n" +
                "\n" +
                "Síntomas comunes según el nutriente:\n" +
                "Nitrógeno: Hojas amarillas (clorosis), especialmente las más viejas; crecimiento lento.\n" +
                "Fósforo: Hojas pequeñas, color verde oscuro o con tonos púrpura; mala floración.\n" +
                "Potasio: Bordes de las hojas secos o quemados; manchas marrones.\n" +
                "Hierro: Hojas jóvenes amarillas con nervaduras verdes (clorosis internerval).\n" +
                "Magnesio: Amarilleo entre nervaduras en hojas viejas.\n" +
                "Calcio: Brotes deformados o crecimiento atrofiado.','Fertiliza regularmente:\n" +
                "Usa un fertilizante equilibrado para plantas de flor (por ejemplo, fórmula 20-20-20 o similar) cada 3-4 semanas en primavera y verano.\n" +
                "\n" +
                "Revisa el sustrato:\n" +
                "Asegúrate de usar un sustrato rico en materia orgánica, bien aireado y con buen drenaje.\n" +
                "\n" +
                "Evita el exceso de riego:\n" +
                "El riego excesivo puede lavar los nutrientes del sustrato. Riega solo cuando la capa superficial esté seca.\n" +
                "\n" +
                "Corrige carencias específicas:\n" +
                "Si notas síntomas claros de un nutriente (por ejemplo, clorosis por hierro), puedes aplicar un fertilizante específico (quelato de hierro, sulfato de magnesio, etc.).\n" +
                "\n" +
                "Trasplanta si es necesario:\n" +
                "Si la planta ha estado mucho tiempo en la misma maceta, trasplanta a un recipiente mayor con sustrato fresco y nutritivo.\n" +
                "\n" +
                "Evita el exceso de fertilizante:\n" +
                "Demasiado abono puede causar acumulación de sales y dañar la planta. Sigue siempre las dosis recomendadas.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'El tizón del pétalo es una enfermedad fúngica, generalmente causada por Botrytis cinerea (moho gris), que afecta las flores de la begonia flor de azúcar. Se manifiesta como manchas marrones o grises en los pétalos, a menudo acompañadas por un polvo grisáceo (esporas del hongo), marchitamiento y pudrición rápida de las flores. Si la humedad ambiental es alta y la ventilación escasa, la enfermedad puede extenderse rápidamente y afectar hojas y tallos jóvenes.','Retira flores afectadas:\n" +
                "Corta y desecha todas las flores con manchas, moho o síntomas de pudrición, usando tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia en un lugar bien aireado y evita el hacinamiento con otras plantas.\n" +
                "\n" +
                "Evita mojar los pétalos:\n" +
                "Riega únicamente el sustrato; evita mojar hojas y flores para no favorecer el desarrollo del hongo.\n" +
                "\n" +
                "Reduce la humedad ambiental:\n" +
                "Si el ambiente es muy húmedo, usa un ventilador suave o deshumidificador para bajar la humedad relativa.\n" +
                "\n" +
                "Aplica fungicida:\n" +
                "Usa un fungicida específico para Botrytis (como productos a base de iprodiona, clorotalonil o cobre), siguiendo las indicaciones del fabricante. Puedes alternar con fungicidas biológicos (extracto de cola de caballo, bicarbonato de sodio, etc.) si lo prefieres.\n" +
                "\n" +
                "Limpieza constante:\n" +
                "Retira restos vegetales, hojas y flores caídas alrededor de la planta para evitar focos de infección.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (110,'Las orugas son larvas de mariposas o polillas que se alimentan de las hojas, flores o brotes tiernos de la begonia flor de azúcar. Se detectan por los agujeros irregulares, bordes mordidos o incluso la presencia visible de las propias orugas, que pueden ser verdes, marrones o con rayas. Si la infestación es fuerte, la planta puede perder mucho follaje y debilitarse.\n" +
                "\n','Inspección visual y retiro manual:\n" +
                "Examina la planta a diario, especialmente en el envés de las hojas y entre los tallos. Retira manualmente las orugas usando guantes y deséchalas lejos de otras plantas.\n" +
                "\n" +
                "Limpieza regular:\n" +
                "Retira hojas muy dañadas y limpia el entorno de la maceta para evitar refugio de plagas.\n" +
                "\n" +
                "Uso de insecticidas biológicos:\n" +
                "Aplica Bacillus thuringiensis (Bt), un insecticida biológico específico contra orugas, seguro para la planta y no tóxico para personas ni mascotas. También puedes usar aceite de neem o jabón potásico como preventivos.\n" +
                "\n" +
                "Control químico (solo si es necesario):\n" +
                "Si la plaga es severa y persiste, utiliza un insecticida específico para orugas siguiendo las instrucciones del fabricante y las medidas de seguridad.\n" +
                "\n" +
                "Mantén la planta sana:\n" +
                "Una planta bien nutrida y con buen riego será más resistente al ataque de plagas.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'Las manchas marrones en las hojas de la begonia robusta pueden tener varias causas, entre las más comunes están:\n" +
                "\n" +
                "Enfermedades fúngicas (como antracnosis, botritis o mancha foliar): producen manchas marrones, a menudo con bordes oscuros o halos amarillos y, en ocasiones, aspecto seco o hundido.\n" +
                "Quemaduras solares: manchas marrones secas e irregulares, generalmente en las hojas expuestas a sol directo.\n" +
                "Exceso o falta de agua: manchas marrones secas (por sequía) o blandas/acuosas (por encharcamiento y pudrición).\n" +
                "Daños físicos o químicos: contacto con productos químicos o daños mecánicos pueden provocar manchas marrones localizadas.','Identifica el origen:\n" +
                "Observa si las manchas están secas o blandas, si aparecen en hojas nuevas o viejas, y si hay otros síntomas como moho, bordes amarillos, etc.\n" +
                "\n" +
                "Elimina hojas afectadas:\n" +
                "Corta y desecha las hojas con manchas marrones usando tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Ajusta la ubicación:\n" +
                "Si sospechas de quemaduras por sol, mueve la begonia robusta a un lugar con luz filtrada o indirecta.\n" +
                "\n" +
                "Controla el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando tanto el encharcamiento como la sequía.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Ubica la planta en un sitio bien ventilado y evita el hacinamiento con otras plantas.\n" +
                "\n" +
                "Aplica fungicida si es necesario:\n" +
                "Si las manchas se extienden y sospechas de hongos, aplica un fungicida específico para plantas ornamentales siguiendo las instrucciones del fabricante.\n" +
                "\n" +
                "Evita mojar el follaje al regar:\n" +
                "Riega solo el sustrato para reducir el riesgo de enfermedades fúngicas.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'La pudrición de las hojas en la begonia robusta se manifiesta como zonas blandas, oscuras, acuosas o incluso malolientes en las hojas, que pueden extenderse rápidamente. Las causas más comunes son:\n" +
                "\n" +
                "Exceso de riego o encharcamiento: El sustrato demasiado húmedo favorece el desarrollo de hongos y bacterias.\n" +
                "Mal drenaje: Una maceta sin agujeros o sustrato compacto impide que el agua salga, favoreciendo la pudrición.\n" +
                "Humedad ambiental muy alta sin ventilación: El ambiente húmedo y estancado predispone a enfermedades.\n" +
                "Enfermedades fúngicas y bacterianas: Patógenos como Botrytis, Pythium o Erwinia causan pudriciones.','Retira hojas afectadas:\n" +
                "Corta y elimina todas las hojas con signos de pudrición usando tijeras limpias y desinfectadas para evitar que la infección se propague.\n" +
                "\n" +
                "Reduce el riego:\n" +
                "Deja secar la capa superficial del sustrato antes de volver a regar. Asegúrate de que la maceta tenga buen drenaje.\n" +
                "\n" +
                "Mejora el drenaje:\n" +
                "Usa un sustrato ligero y aireado, con buen contenido de materia orgánica y perlita o arena. Verifica que la maceta tenga agujeros de drenaje.\n" +
                "\n" +
                "Ventila el ambiente:\n" +
                "Coloca la begonia robusta en un lugar bien ventilado, evitando el hacinamiento y la humedad excesiva.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Al regar, hazlo directamente en el sustrato y evita mojar las hojas.\n" +
                "\n" +
                "Desinfecta herramientas:\n" +
                "Limpia bien las tijeras y utensilios antes y después de podar para no diseminar enfermedades.\n" +
                "\n" +
                "Aplica fungicida si es necesario:\n" +
                "Si la pudrición persiste o se extiende, aplica un fungicida específico para plantas ornamentales siguiendo las instrucciones del fabricante.\n" +
                "\n',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'Las flores marchitas en la begonia robusta pueden deberse a causas naturales o a problemas de cultivo. Es normal que, tras completar su ciclo, las flores se marchiten y sequen. Sin embargo, si las flores se marchitan de forma prematura o en gran cantidad, puede estar relacionado con:\n" +
                "\n" +
                "Falta o exceso de riego.\n" +
                "Ambientes muy secos.\n" +
                "Exceso de calor o exposición al sol directo.\n" +
                "Falta de nutrientes.\n" +
                "Enfermedades fúngicas (como botritis/tizón del pétalo) o plagas.\n','Retira las flores marchitas:\n" +
                "Elimina las flores secas o deterioradas con tijeras limpias para evitar la propagación de enfermedades y estimular nuevas floraciones.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando tanto el encharcamiento como la sequía.\n" +
                "\n" +
                "Controla la humedad y la ventilación:\n" +
                "Aumenta la humedad ambiental (sin encharcar) y asegura una buena ventilación, pero evita corrientes de aire frío o caliente.\n" +
                "\n" +
                "Evita el sol directo:\n" +
                "Coloca la begonia robusta en un sitio con luz indirecta o filtrada.\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Fertiliza cada 3-4 semanas en época de crecimiento con un abono para plantas de flor.\n" +
                "\n" +
                "Revisa por enfermedades:\n" +
                "Si notas manchas, moho o pudrición en las flores, elimina las partes afectadas y aplica un fungicida suave.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'Es normal que, una vez que las flores de la begonia robusta terminan su ciclo, se marchiten y sequen. Sin embargo, si además del marchitamiento de las flores, notas que las hojas o la planta entera se ven decaídas, amarillas o blandas después de la floración, puede deberse a:\n" +
                "\n" +
                "Estrés por el gasto energético de la floración.\n" +
                "Falta de nutrientes tras la floración.\n" +
                "Riego inadecuado (exceso o falta de agua).\n" +
                "Acumulación de flores marchitas que favorecen hongos.\n" +
                "Condiciones ambientales desfavorables (falta de luz, calor excesivo, corrientes de aire frío).','Retira flores marchitas:\n" +
                "Elimina rápidamente las flores secas para evitar desarrollo de hongos y estimular nuevas brotaciones.\n" +
                "\n" +
                "Recorta ligeramente:\n" +
                "Puedes podar los tallos florales secos para favorecer el rebrote y la salud general de la planta.\n" +
                "\n" +
                "Fertiliza después de la floración:\n" +
                "Aplica un fertilizante equilibrado o uno rico en potasio y fósforo para ayudar a la recuperación y estimular nuevos crecimientos.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando tanto el encharcamiento como la sequía.\n" +
                "\n" +
                "Mejora las condiciones de luz y ventilación:\n" +
                "Coloca la begonia robusta en un sitio luminoso, pero sin sol directo fuerte, y con buena ventilación.\n" +
                "\n" +
                "Evita el estrés ambiental:\n" +
                "No sometas la planta a cambios bruscos de temperatura o corrientes de aire.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'El escarabajo de las hojas es un insecto que se alimenta de las hojas de la begonia robusta, provocando agujeros, mordeduras irregulares en los bordes o manchas oscuras donde se alimenta. Los adultos suelen ser pequeños, de colores brillantes (amarillos, verdes, marrones o metálicos) y las larvas, de aspecto blando, también pueden causar daños. Si la infestación es fuerte, la planta puede perder vigor y su aspecto ornamental.','Inspección y retiro manual:\n" +
                "Revisa la planta, especialmente en el envés de las hojas, y retira manualmente los escarabajos y larvas. Puedes aplastarlos o sumergirlos en agua jabonosa.\n" +
                "\n" +
                "Limpieza del entorno:\n" +
                "Retira hojas dañadas, restos vegetales y limpia alrededor de la maceta para evitar refugios de plagas.\n" +
                "\n" +
                "Uso de insecticidas biológicos:\n" +
                "Aplica jabón potásico o aceite de neem en las hojas y tallos, especialmente donde veas adultos o larvas. Son seguros para la planta y el ambiente.\n" +
                "\n" +
                "Control químico (si la infestación es severa):\n" +
                "Utiliza un insecticida específico para coleópteros, siempre siguiendo las instrucciones y precauciones del fabricante.\n" +
                "\n" +
                "Mantén la planta fuerte:\n" +
                "Un buen riego, sustrato aireado y nutrición equilibrada ayudan a la begonia robusta a resistir mejor las plagas.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'El envejecimiento es un proceso natural en la begonia robusta, así como en cualquier planta ornamental. Con el tiempo, las hojas más viejas empiezan a amarillear, secarse o caerse, y los tallos pueden volverse leñosos o perder vigor. La floración puede disminuir y el crecimiento hacerse más lento.\n" +
                "\n" +
                "Síntomas comunes de envejecimiento:\n" +
                "\n" +
                "Hojas amarillas, secas, con bordes marrones o caídas en la base de la planta.\n" +
                "Tallos viejos, gruesos o leñosos.\n" +
                "Menor producción de flores y brotes nuevos.\n" +
                "Aspecto general menos vigoroso o denso.\n','Poda rejuvenecedora:\n" +
                "Corta los tallos viejos o poco productivos para estimular el crecimiento de brotes nuevos y sanos. Usa tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Elimina hojas secas y amarillas:\n" +
                "Retira regularmente el follaje muerto o envejecido para evitar acumulación de enfermedades y mejorar el aspecto estético.\n" +
                "\n" +
                "Renueva el sustrato:\n" +
                "Trasplanta la begonia robusta cada 2-3 años a una maceta más grande con sustrato fresco y rico en materia orgánica.\n" +
                "\n" +
                "Fertiliza adecuadamente:\n" +
                "Aplica un fertilizante equilibrado durante la época de crecimiento (primavera-verano) para aportar nutrientes y estimular brotes jóvenes.\n" +
                "\n" +
                "Mantén buenas condiciones de luz y riego:\n" +
                "Ubica la planta en un lugar luminoso con luz indirecta y riega moderadamente, evitando encharcamientos y sequías.\n" +
                "\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'La aparición de puntos negros en las hojas, tallos o flores de la begonia robusta suele estar asociada a enfermedades fúngicas o bacterianas, plagas o daños ambientales. Los puntos negros pueden ser pequeños y dispersos o formar manchas más grandes con halos amarillos o marrones. Si no se trata, el problema puede extenderse y debilitar la planta.','Revisa cuidadosamente la planta:\n" +
                "Examina el envés de las hojas y otras partes para descartar presencia de plagas.\n" +
                "\n" +
                "Retira las partes afectadas:\n" +
                "Corta y elimina hojas, flores o tallos con puntos negros usando tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia robusta en un área con buena circulación de aire y evita el exceso de humedad.\n" +
                "\n" +
                "Evita mojar el follaje:\n" +
                "Riega solo el sustrato y no las hojas para reducir la propagación de hongos y bacterias.\n" +
                "\n" +
                "Aplica un fungicida:\n" +
                "Si los puntos se extienden, utiliza un fungicida de amplio espectro (a base de cobre, mancozeb, clorotalonil, etc.) siguiendo las indicaciones del fabricante.\n" +
                "\n" +
                "Controla el riego:\n" +
                "No encharques el sustrato y permite que la superficie se seque entre riegos.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'La deficiencia de nutrientes ocurre cuando la begonia robusta no recibe los minerales esenciales para un crecimiento sano. Esto puede deberse a un sustrato pobre, falta de fertilización o problemas en la absorción de nutrientes por exceso o defecto de riego. Las deficiencias más comunes y sus síntomas son:\n" +
                "\n" +
                "Síntomas de deficiencias frecuentes:\n" +
                "\n" +
                "Nitrógeno: Hojas amarillas (clorosis) empezando por las hojas más viejas, crecimiento lento.\n" +
                "Fósforo: Hojas verde-azuladas o con tonos púrpura, crecimiento atrofiado y poca floración.\n" +
                "Potasio: Bordes de las hojas marrones o secos, manchas amarillas o necrosis en los bordes.\n" +
                "Hierro: Hojas jóvenes amarillas pero venas verdes (clorosis intervenal).\n" +
                "Magnesio: Amarilleo entre las venas de hojas más viejas, pero venas permanecen verdes.\n" +
                "Calcio: Puntas de hojas deformes o necrosadas, crecimiento atrofiado.\n" +
                "Micronutrientes (zinc, boro, manganeso, etc.): Manchas, deformaciones o amarilleo irregular en hojas jóvenes.','Identifica el síntoma principal:\n" +
                "Observa qué hojas están afectadas (viejas, jóvenes), el patrón del amarilleo o manchas, y si hay deformaciones.\n" +
                "\n" +
                "Fertiliza adecuadamente:\n" +
                "Usa un fertilizante equilibrado para plantas de interior o específico para begonias. Aplica cada 3-4 semanas en época de crecimiento (primavera-verano) según la dosis recomendada.\n" +
                "\n" +
                "Renueva el sustrato si es antiguo:\n" +
                "Si la planta lleva más de un año sin trasplante, cambia a un sustrato fresco y rico en materia orgánica.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Riega de forma regular pero sin encharcar, ya que el exceso de agua puede bloquear la absorción de nutrientes.\n" +
                "\n" +
                "Evita el uso excesivo de fertilizante:\n" +
                "Un exceso puede causar toxicidad (bordes quemados, hojas secas) y bloquear otros nutrientes.\n" +
                "\n" +
                "Considera suplementos de micronutrientes:\n" +
                "Si los síntomas persisten, usa un fertilizante que incluya micronutrientes o aplica quelatos según necesidad.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'El tizón del pétalo, también conocido como botritis o podredumbre gris (causada por el hongo Botrytis cinerea), es una enfermedad fúngica muy común en begonias y otras plantas ornamentales. Ataca principalmente flores, pero puede extenderse a hojas y tallos.\n" +
                "\n" +
                "Síntomas característicos:\n" +
                "\n" +
                "Manchas marrones o grises en los pétalos de las flores, que se expanden rápidamente.\n" +
                "Aparición de un moho gris polvoriento sobre los pétalos afectados.\n" +
                "Flores que se marchitan, pudren y caen prematuramente.\n" +
                "Si la humedad es alta, el hongo puede propagarse a hojas y tallos, causando manchas y podredumbre blanda.','Retira flores y partes afectadas:\n" +
                "Elimina rápidamente todas las flores marchitas y partes con manchas o moho. Usa tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Coloca la begonia robusta en un lugar bien ventilado y evita el hacinamiento con otras plantas.\n" +
                "\n" +
                "Evita mojar flores y hojas al regar:\n" +
                "Riega directamente el sustrato, no el follaje ni las flores.\n" +
                "\n" +
                "Reduce la humedad ambiental:\n" +
                "Evita la acumulación de agua y el exceso de humedad en el ambiente, especialmente en interiores.\n" +
                "\n" +
                "Aplica fungicida si es necesario:\n" +
                "Utiliza un fungicida específico para botritis (a base de cobre, iprodiona, clorotalonil, etc.), siguiendo las indicaciones del fabricante.\n" +
                "\n" +
                "Limpieza regular:\n" +
                "Retira restos vegetales de la superficie del sustrato o alrededor de la planta.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (120,'Las orugas son larvas de mariposas o polillas que se alimentan de las hojas de la begonia robusta, causando daños visibles como agujeros, bordes irregulares, hojas parcialmente comidas o incluso defoliación si la infestación es fuerte. También pueden dejar excrementos oscuros (bolitas negras) en las hojas y el sustrato.','Inspección y retiro manual:\n" +
                "Revisa bien la planta, especialmente el envés de las hojas y los tallos. Retira las orugas a mano y deséchalas lejos de la planta.\n" +
                "\n" +
                "Limpieza del entorno:\n" +
                "Quita hojas dañadas y restos vegetales del sustrato y alrededores para evitar refugios y huevos.\n" +
                "\n" +
                "Aplicación de insecticidas biológicos:\n" +
                "\n" +
                "Utiliza Bacillus thuringiensis (Bt), un bioinsecticida seguro para plantas y personas, pero letal para orugas. Rocía según las instrucciones del producto.\n" +
                "También puedes usar jabón potásico o aceite de neem, aunque son menos específicos.\n" +
                "Control químico (solo si es necesario):\n" +
                "Si la infestación es muy severa, utiliza un insecticida específico para orugas siguiendo las indicaciones del fabricante.\n" +
                "\n" +
                "Prevención:\n" +
                "Mantén la planta saludable y revisa regularmente para detectar huevos o pequeñas orugas antes de que causen daños significativos.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Las cicatrices son marcas permanentes en los segmentos del cactus de Navidad. Aparecen como zonas secas, blanquecinas, marrones o corchosas, a veces hundidas, y pueden variar desde pequeñas manchas hasta áreas más grandes.','Identifica la causa:\n" +
                "Observa si la cicatriz está asociada a daño físico, quemaduras, plagas o enfermedades pasadas.\n" +
                "\n" +
                "Prevención:\n" +
                "\n" +
                "Evita golpes o roces al mover la planta.\n" +
                "Protege del sol directo, sobre todo en las horas fuertes.\n" +
                "Mantén un riego regular y evita el exceso de agua.\n" +
                "Controla periódicamente las plagas y enfermedades.\n" +
                "Tratamiento:\n" +
                "\n" +
                "Las cicatrices existentes no pueden eliminarse, pero no afectan la salud general si la planta está vigorosa.\n" +
                "Si la zona dañada es grande o blanda, recorta el segmento afectado con tijeras limpias y desinfectadas.\n" +
                "Si hay signos de enfermedad activa (zona húmeda, mal olor, avance de la mancha), trata con fungicida o bactericida.\n" +
                "Cuidado estético:\n" +
                "Si las cicatrices son antiestéticas y la planta es grande, puedes podar los segmentos afectados para estimular nuevos brotes.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Síntomas de falta de riego en cactus de Navidad:\n" +
                "\n" +
                "Segmentos (hojas) arrugados, blandos o flácidos.\n" +
                "Pérdida de turgencia, aspecto deshidratado.\n" +
                "Color apagado o amarillento en los segmentos.\n" +
                "Caída prematura de brotes, flores o segmentos.\n" +
                "Crecimiento detenido o lento.','Riego inmediato pero controlado:\n" +
                "Riega la planta con agua a temperatura ambiente hasta que el sustrato quede bien húmedo, pero sin encharcar. Espera a que escurra el exceso de agua.\n" +
                "\n" +
                "No satures el sustrato:\n" +
                "Si el sustrato está extremadamente seco y se ha compactado, es mejor humedecerlo poco a poco durante varios días en vez de un riego muy abundante de golpe.\n" +
                "\n" +
                "Observa la recuperación:\n" +
                "En unos días los segmentos deberían recuperar firmeza y color. Si no mejora, revisa el estado de las raíces.\n" +
                "\n" +
                "Ajusta la frecuencia de riego:\n" +
                "\n" +
                "Riega cuando la capa superior del sustrato (2-3 cm) esté seca al tacto.\n" +
                "En primavera y verano, el cactus de Navidad necesita más agua; en otoño e invierno, menos.\n" +
                "Evita el exceso de sol directo:\n" +
                "El sol directo puede agravar la deshidratación. Mantén la planta en luz brillante pero filtrada.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Después de la floración, el cactus de Navidad puede desarrollar frutos (pequeñas bayas de color rosado, rojo o púrpura). Si estas frutas aparecen marchitas, arrugadas o blandas antes de madurar, puede ser señal de problemas de cultivo o de condiciones ambientales adversas.','Retira los frutos marchitos:\n" +
                "Corta con cuidado las frutas blandas o secas para evitar enfermedades y mejorar el aspecto de la planta.\n" +
                "\n" +
                "Revisa el riego:\n" +
                "Mantén el sustrato ligeramente húmedo pero nunca encharcado. Riega solo cuando la capa superficial esté seca.\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Aplica un fertilizante equilibrado cada 3-4 semanas en época de crecimiento para fortalecer la planta.\n" +
                "\n" +
                "Mejora el ambiente:\n" +
                "Coloca la planta en un sitio con luz brillante indirecta, sin cambios drásticos de temperatura.\n" +
                "\n" +
                "Vigila enfermedades:\n" +
                "Si el fruto presenta manchas negras, moho o mal olor, trata la planta con un fungicida adecuado.',32)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Síntomas de marchitamiento:\n" +
                "\n" +
                "Segmentos (hojas) blandos, arrugados o flácidos.\n" +
                "Caída de segmentos o flores.\n" +
                "Color apagado o amarillento.\n" +
                "Crecimiento detenido.\n','Revisa el sustrato:\n" +
                "\n" +
                "Si está seco, riega de forma gradual.\n" +
                "Si está húmedo y la planta sigue marchita, revisa las raíces: si están marrones, blandas o con mal olor, hay pudrición.\n" +
                "Airea y trasplanta si es necesario:\n" +
                "Si hay pudrición, corta raíces dañadas y trasplanta a un sustrato fresco y bien drenante.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Riega cuando la capa superficial esté seca, nunca encharques.\n" +
                "\n" +
                "Ubica en lugar adecuado:\n" +
                "Luz brillante indirecta, sin sol directo fuerte ni corrientes de aire.\n" +
                "\n" +
                "Vigila plagas y enfermedades:\n" +
                "Si hay síntomas de plaga o enfermedad, actúa con el tratamiento específico (insecticida o fungicida).',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Las flores del cactus de Navidad pueden marchitarse prematuramente, mostrando pétalos caídos, secos o arrugados, y pérdida de color y firmeza. Este marchitamiento puede afectar flores abiertas o capullos antes de abrirse.\n" +
                "\n','Retira las flores marchitas:\n" +
                "Córtalas suavemente para favorecer nuevas floraciones y evitar enfermedades.\n" +
                "\n" +
                "Ajusta el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, sin encharcar ni dejar que se seque completamente.\n" +
                "\n" +
                "Controla el ambiente:\n" +
                "Coloca la planta en un sitio con luz brillante indirecta, lejos de corrientes de aire y sin sol directo. Si el ambiente es seco, aumenta la humedad con un humidificador o bandeja con agua cerca (sin mojar la planta).\n" +
                "\n" +
                "Evita mover la planta en floración:\n" +
                "El cactus de Navidad es sensible a los cambios de posición cuando está en flor.\n" +
                "\n" +
                "Aporta nutrientes:\n" +
                "Aplica fertilizante equilibrado cada 3-4 semanas durante la época de crecimiento y floración.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'El envejecimiento es el proceso natural por el que el cactus de Navidad, como cualquier planta, experimenta cambios con el paso de los años. Una planta madura puede vivir varias décadas si se cuida bien, pero muestra ciertos signos de edad.\n" +
                "\n" +
                "Síntomas y señales de envejecimiento:\n" +
                "\n" +
                "Segmentos viejos y leñosos en la base (tallo central endurecido o marrón).\n" +
                "Segmentos (hojas) más antiguos con cicatrices, manchas o arrugas permanentes.\n" +
                "Menor producción de brotes nuevos y flores.\n" +
                "Crecimiento más lento.\n" +
                "Puntas secas o deshidratadas.\n" +
                "Raíces densas y compactas, a veces con menos vigor.','Poda de rejuvenecimiento:\n" +
                "Corta segmentos viejos o dañados para estimular el crecimiento de brotes nuevos. Puedes hacer esquejes y multiplicar la planta.\n" +
                "\n" +
                "Trasplante:\n" +
                "Si la planta lleva varios años en la misma maceta, trasplántala a un sustrato nuevo y bien drenante. Esto ayuda a revitalizar las raíces.\n" +
                "\n" +
                "Fertilización adecuada:\n" +
                "Aplica fertilizante equilibrado en época de crecimiento (primavera-verano) para estimular la brotación y la floración.\n" +
                "\n" +
                "Riego cuidadoso:\n" +
                "Ajusta el riego para evitar tanto el exceso como la sequía, ya que las plantas viejas pueden ser más sensibles a desbalances hídricos.\n" +
                "\n" +
                "Buena iluminación:\n" +
                "Proporciona luz brillante indirecta para favorecer la fotosíntesis y la producción de nuevos brotes.\n" +
                "\n" +
                "Propagación:\n" +
                "Si la base está muy leñosa o la planta pierde fuerza, toma esquejes sanos de los segmentos y plántalos para obtener una planta joven.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'El marchitamiento después de la floración en el cactus de Navidad es un fenómeno común y natural. Tras el intenso esfuerzo energético que implica la producción de flores, la planta puede mostrar segmentos más blandos, decaídos o arrugados, y un aspecto menos vigoroso. Durante este periodo, la Schlumbergera entra en una fase de reposo en la que reduce su actividad metabólica, necesita menos agua y nutrientes, y se prepara para recuperarse y fortalecer sus tejidos antes de iniciar un nuevo ciclo de crecimiento y floración. Este proceso de marchitamiento suele ser temporal y, con los cuidados adecuados, la planta recupera su vitalidad en pocas semanas.','Retira flores y segmentos marchitos:\n" +
                "Elimina con cuidado las flores secas y los segmentos muy dañados para evitar enfermedades y estimular nuevos brotes.\n" +
                "\n" +
                "Reduce el riego temporalmente:\n" +
                "Después de la floración, la planta entra en un periodo de reposo. Deja secar la capa superficial del sustrato antes de volver a regar.\n" +
                "\n" +
                "Coloca en lugar fresco y con luz indirecta:\n" +
                "Mantén la planta en un sitio luminoso, pero evita el sol directo y las corrientes de aire.\n" +
                "\n" +
                "No fertilices inmediatamente:\n" +
                "Espera unas semanas tras la floración antes de volver a aportar fertilizante, permitiendo que la planta descanse.\n" +
                "\n" +
                "Vigila el ambiente:\n" +
                "Evita cambios bruscos de temperatura y mantén la humedad ambiental moderada.\n" +
                "\n" +
                "Retoma cuidados normales tras el reposo:\n" +
                "Pasadas 4-6 semanas, vuelve a regar y fertilizar con normalidad para estimular el crecimiento de nuevos segmentos.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Las cochinillas son una de las plagas más comunes en el cactus de Navidad (Schlumbergera). Aquí tienes cómo identificarlas, el daño que causan y qué hacer para eliminarlas:\n" +
                "\n" +
                "¿Cómo reconocer las cochinillas?\n" +
                "Se ven como pequeños bultitos blancos, algodonosos o marrones adheridos a los segmentos del cactus.\n" +
                "Suelen encontrarse en las uniones de los segmentos, el envés de las “hojas” o en la base de la planta.\n" +
                "A veces dejan un residuo pegajoso (melaza) en la planta o el sustrato.\n" +
                "Daños que provocan\n" +
                "Debilitan la planta al succionar savia, provocando segmentos amarillos, blandos o deformados.\n" +
                "Pueden favorecer la aparición de hongos (fumagina) debido a la melaza.\n" +
                "Si la plaga es severa, la planta puede marchitarse y dejar de florecer.','Aísla la planta para evitar que la plaga se extienda a otras plantas.\n" +
                "Retira manualmente las cochinillas con un algodón o bastoncillo humedecido en alcohol.\n" +
                "Lava la planta con agua tibia y un poco de jabón neutro; enjuaga bien para eliminar restos.\n" +
                "Aplica insecticida específico para cochinillas (puede ser ecológico, como jabón potásico o aceite de neem, o químico si la infestación es grave).\n" +
                "Repite el tratamiento cada 7-10 días hasta asegurarte de que han desaparecido.\n" +
                "Revisa periódicamente la planta para detectar rebrotes de la plaga.',13)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'Las orugas son larvas de mariposas o polillas que pueden aparecer en el cactus de Navidad, alimentándose de los segmentos de la planta y causando daños visibles.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Presencia de orugas visibles en la planta.\n" +
                "Agujeros irregulares en segmentos (“hojas”).\n" +
                "Bordes comidos y restos de heces pequeñas en el sustrato o sobre la planta.\n" +
                "Segmentos debilitados o caídos.','Inspecciona la planta y retira manualmente las orugas con guantes o pinzas.\n" +
                "Lava la planta con agua para eliminar orugas pequeñas o huevos.\n" +
                "Aplica un insecticida biológico a base de Bacillus thuringiensis (BT) siguiendo las indicaciones del fabricante.\n" +
                "Mantén la planta vigilada y repite el tratamiento si notas nuevas orugas.\n" +
                "Limpia el área alrededor de la planta para prevenir re-infestaciones.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (130,'La raíz podrida es un problema en el cactus de Navidad donde las raíces se descomponen, impidiendo que la planta absorba agua y nutrientes de forma adecuada.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Segmentos blandos, arrugados o translúcidos.\n" +
                "Mal olor proveniente del sustrato o la base de la planta.\n" +
                "Raíces oscuras, blandas o deshechas al sacarlas de la maceta.\n" +
                "Caída de segmentos o debilitamiento general.','Extrae la planta de la maceta y elimina todo el sustrato húmedo o contaminado.\n" +
                "Recorta todas las raíces afectadas con tijeras limpias, dejando solo raíces sanas y firmes.\n" +
                "Deja secar la planta unas horas antes de replantar.\n" +
                "Trasplanta en un sustrato nuevo, ligero y bien drenante.\n" +
                "Riega muy poco durante las primeras semanas y solo cuando el sustrato esté casi seco.\n" +
                "Vigila la planta y repite el proceso si reaparecen síntomas.',33)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Las cicatrices en el cactus quebradizo son marcas visibles en la superficie de los segmentos, resultado de daños previos o heridas que han sanado.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas o líneas endurecidas, secas o de color marrón, blanco o gris en los segmentos.\n" +
                "Áreas con textura diferente al resto de la planta, a veces algo hundidas o elevadas.\n" +
                "No suelen extenderse, pero pueden quedar como marcas permanentes.','Mantén la planta en un lugar protegido para evitar nuevos daños físicos.\n" +
                "Evita heridas al manipular o trasplantar el cactus.\n" +
                "Asegura un riego y ventilación adecuados para prevenir infecciones en heridas recientes.\n" +
                "Si aparecen signos de pudrición o infección en la cicatriz, recorta el área afectada con herramientas limpias y aplica fungicida.\n" +
                "Monitorea la planta regularmente; las cicatrices antiguas no requieren tratamiento si no avanzan.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'La pudrición del tallo en el cactus quebradizo es una descomposición de los tejidos del tallo, generalmente causada por exceso de humedad o heridas previas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Áreas blandas, oscuras o acuosas en el tallo.\n" +
                "Mal olor en la zona afectada.\n" +
                "Segmentos que se desprenden fácilmente o colapsan.\n" +
                "Progresión rápida de la descomposición si no se trata.','Elimina las partes afectadas cortando el tallo por encima de la zona dañada con herramientas limpias.\n" +
                "Deja secar la herida al aire durante varios días antes de volver a plantar.\n" +
                "Trasplanta en sustrato completamente seco y bien drenante.\n" +
                "Evita el riego hasta que el tallo cicatrice y se note firme.\n" +
                "Mantén la planta en un lugar ventilado y vigilado para prevenir recurrencias.',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Copilot said: **Descripción:** La insuficiencia de luz en\n" +
                "Descripción:\n" +
                "La insuficiencia de luz en el cactus quebradizo ocurre cuando la planta no recibe la cantidad de luz necesaria para mantener un crecimiento saludable.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Segmentos alargados, delgados o deformados.\n" +
                "Coloración pálida o verde muy clara.\n" +
                "Crecimiento lento o nulo.\n" +
                "Segmentos que se caen fácilmente o se quiebran.','Traslada la planta a un lugar más luminoso con luz indirecta intensa.\n" +
                "Si no hay luz natural suficiente, utiliza luz artificial (luz de crecimiento para plantas).\n" +
                "Gira la maceta periódicamente para asegurar un crecimiento uniforme.\n" +
                "Vigila los cambios y ajusta la ubicación según la respuesta de la planta.',35)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'La mancha bacteriana en el cactus quebradizo es una infección causada por bacterias que produce lesiones visibles en los segmentos de la planta.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas húmedas, translúcidas o aceitosas de color marrón o negro.\n" +
                "Bordes irregulares en las manchas.\n" +
                "Posible exudado pegajoso o mal olor en la zona afectada.\n" +
                "Segmentos afectados que se ablandan o colapsan.','Retira y elimina las partes afectadas con herramientas limpias y desinfectadas.\n" +
                "Deja secar las heridas al aire antes de regar nuevamente.\n" +
                "Aplica un bactericida específico siguiendo las indicaciones del producto.\n" +
                "Mantén la planta en un lugar ventilado y evita el exceso de humedad.\n" +
                "Desinfecta herramientas y manos después de manipular la planta.',36)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'La muerte regresiva en el\n" +
                "Descripción:\n" +
                "La muerte regresiva en el cactus quebradizo es un proceso en el que los segmentos más alejados del tallo principal comienzan a secarse y morir, avanzando progresivamente hacia la base de la planta.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Extremos de los tallos que se secan, arrugan y se tornan marrones o grises.\n" +
                "Segmentos que se desprenden fácilmente.\n" +
                "Progresión gradual de la muerte desde las puntas hacia el centro de la planta.','Corta y elimina todas las partes afectadas con herramientas limpias y desinfectadas.\n" +
                "Deja secar los cortes y aplica fungicida si es necesario.\n" +
                "Mejora el drenaje y evita el exceso de riego.\n" +
                "Coloca la planta en un lugar bien ventilado y con luz adecuada.\n" +
                "Monitorea la planta y repite el procedimiento si aparecen nuevos síntomas.',12)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Las mariquitas son insectos benéficos que suelen habitar en cactus quebradizos y otras plantas, alimentándose principalmente de plagas como pulgones y cochinillas.','No se requiere acción, ya que las mariquitas son beneficiosas para el cactus. Si deseas retirarlas, puedes trasladarlas suavemente a otra planta, pero se recomienda dejarlas para el control biológico de plagas.',6)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Las abejas son insectos polinizadores que pueden visitar el cactus quebradizo atraídas por sus flores, pero no ocasionan daños a la planta.','No se requiere ninguna acción, ya que las abejas son beneficiosas para la polinización y no afectan negativamente al cactus. Si es necesario retirarlas, ventila el área o aleja la planta de zonas de tránsito, evitando el uso de productos químicos.',38)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Las moscas blancas son pequeños insectos voladores que se alimentan de la savia del cactus quebradizo, debilitando la planta y favoreciendo la aparición de enfermedades.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Presencia de diminutos insectos blancos en el envés de los segmentos.\n" +
                "Polvillo blanco que se dispersa al mover la planta.\n" +
                "Segmentos amarillentos, debilitados o pegajosos por la melaza que producen.\n" +
                "Posible aparición de hongos negros (fumagina) sobre la melaza.','Retira manualmente las moscas blancas con un chorro suave de agua o paño húmedo.\n" +
                "Usa trampas adhesivas amarillas cerca de la planta para atraer y reducir la población.\n" +
                "Aplica jabón potásico o un insecticida específico para moscas blancas, siguiendo las indicaciones del producto.\n" +
                "Mejora la ventilación y evita el exceso de humedad.\n" +
                "Revisa las plantas cercanas y mantén la vigilancia para prevenir reinfestaciones.',39)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'Copilot said: **Descripción:** Los trips (o thrips) son\n" +
                "Descripción:\n" +
                "Los trips (o thrips) son pequeños insectos alargados que se alimentan de la savia del cactus quebradizo, causando daño en los tejidos y debilitando la planta.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas plateadas, blanquecinas o descoloridas en los segmentos.\n" +
                "Pequeños puntos negros (excrementos) en la superficie de la planta.\n" +
                "Segmentos deformados, arrugados o con crecimiento anormal.\n" +
                "Presencia de insectos delgados, de 1-2 mm, que se mueven rápidamente al ser molestados.','Elimina manualmente los trips con un paño húmedo o un chorro suave de agua.\n" +
                "Aísla la planta afectada para evitar el contagio a otras plantas.\n" +
                "Aplica jabón potásico o un insecticida específico para trips, siguiendo las indicaciones del producto.\n" +
                "Mejora la ventilación y evita el exceso de humedad.\n" +
                "Revisa y limpia regularmente las plantas cercanas para prevenir reinfestaciones.',40)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (140,'El moho en la fruta del cactus quebradizo es una infección fúngica que aparece sobre los frutos cuando hay alta humedad o mala ventilación, favoreciendo el crecimiento de hongos.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Presencia de manchas algodonosas, polvorientas o aterciopeladas de color blanco, gris, verde o negro sobre la fruta.\n" +
                "Frutas blandas, descompuestas o con mal olor.\n" +
                "Caída prematura de la fruta afectada.','Retira y desecha las frutas afectadas para evitar la propagación del hongo.\n" +
                "Mejora la ventilación alrededor de la planta y evita el exceso de humedad.\n" +
                "Si la infestación es severa, aplica un fungicida específico para cactus siguiendo las indicaciones del producto.\n" +
                "Monitorea el riego y solo riega cuando el sustrato esté completamente seco.\n" +
                "Limpia herramientas y manos después de manipular frutas enfermas.',41)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,' Las cicatrices en un cactus colgante suelen aparecer como marcas marrones, grises o blanquecinas en el tallo o los segmentos. Estas cicatrices pueden deberse a daños físicos (golpes, cortes, manipulación brusca), quemaduras solares, ataques de plagas, heridas por exceso de agua o infecciones fúngicas/bacterianas. En general, las cicatrices indican que la planta ha sufrido una lesión y está intentando sanar.','Prevención de daños físicos:\n" +
                "\n" +
                "Maneja la planta con cuidado, especialmente al trasplantar o moverla.\n" +
                "Evita que la planta cuelgue en lugares de alto tránsito donde pueda ser golpeada.\n" +
                "Protección solar:\n" +
                "\n" +
                "Si las cicatrices son por quemaduras solares, coloca el cactus colgante en un lugar con luz indirecta brillante (no sol directo durante las horas más intensas).\n" +
                "Acostumbra la planta gradualmente a la luz más intensa si la vas a mover de un interior a un exterior soleado.\n" +
                "Control de plagas:\n" +
                "\n" +
                "Revisa si hay cochinillas, ácaros o insectos, ya que pueden causar heridas. Si detectas plagas, retíralas manualmente y aplica un insecticida apto para cactus.\n" +
                "Manejo del riego:\n" +
                "\n" +
                "Evita el exceso de agua y asegúrate de que la maceta drene bien. El exceso de humedad puede favorecer infecciones que dejan cicatrices.\n" +
                "Tratamiento de heridas e infecciones:\n" +
                "\n" +
                "Si la cicatriz es reciente y sospechas de infección, puedes aplicar un fungicida suave o azufre en polvo sobre la herida.\n" +
                "Deja que las heridas sequen al aire; no las tapes ni las mojes en exceso.\n" +
                "Paciencia:\n" +
                "\n" +
                "Las cicatrices no suelen desaparecer, pero si cuidas bien a tu cactus colgante, la planta seguirá creciendo sana y las nuevas partes estarán libres de marcas.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,' El envejecimiento en el cactus colgante se manifiesta cuando los tallos o segmentos más viejos empiezan a verse arrugados, descoloridos, leñosos o pierden vigor. Es un proceso natural en plantas maduras, pero puede hacer que la planta luzca menos atractiva, con partes secas o muertas. Los tallos antiguos suelen dejar de crecer y pueden incluso caerse, mientras que los nuevos brotes crecen en los extremos.','Poda de tallos viejos:\n" +
                "\n" +
                "Retira cuidadosamente los segmentos o tallos que estén secos, arrugados o leñosos. Usa tijeras limpias y afiladas para evitar infecciones.\n" +
                "Eliminar estos tallos estimula el crecimiento de nuevos brotes y mantiene la planta más estética y saludable.\n" +
                "Estimulación del rebrote:\n" +
                "\n" +
                "Asegúrate de que la planta reciba suficiente luz indirecta y un riego adecuado (deja secar el sustrato entre riegos).\n" +
                "Puedes abonar en primavera/verano con fertilizante específico para cactus, lo que favorece la aparición de nuevos brotes vigorosos.\n" +
                "Renovación de la planta:\n" +
                "\n" +
                "Si la planta tiene muchas partes envejecidas, puedes cortar segmentos sanos y enraizarlos en sustrato para cactus. Así obtendrás una planta rejuvenecida.\n" +
                "Después de unos meses, los esquejes formarán raíces y comenzarán a crecer con fuerza.\n" +
                "Evita el estrés ambiental:\n" +
                "\n" +
                "Protege al cactus colgante de corrientes de aire frío, cambios bruscos de temperatura y exceso de humedad, que pueden acelerar el deterioro de los segmentos viejos.\n" +
                "Observa regularmente:\n" +
                "\n" +
                "Revisa la planta cada cierto tiempo para eliminar tallos envejecidos y detectar signos de plagas o enfermedades antes de que afecten a toda la planta.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'La falta de riego en el cactus colgante se manifiesta principalmente por tallos o segmentos arrugados, deshidratados, y más blandos de lo normal. Los brotes pueden perder firmeza y verse opacos, y en casos prolongados, pueden secarse y caerse. La planta deja de crecer y puede mostrar puntas secas o marrones. Aunque los cactus toleran la sequía, una falta de agua prolongada debilita mucho al cactus colgante, especialmente durante su época de crecimiento.','Riego adecuado:\n" +
                "\n" +
                "Riega la planta cuando el sustrato esté completamente seco al tacto, pero antes de que los tallos se arruguen excesivamente.\n" +
                "En primavera y verano (época de crecimiento), riega cada 10-15 días, ajustando según la temperatura y la humedad del ambiente.\n" +
                "En otoño e invierno, reduce la frecuencia: cada 3-4 semanas suele ser suficiente.\n" +
                "Rehidratación gradual:\n" +
                "\n" +
                "Si la planta está muy deshidratada, no la satures de agua de golpe. Haz un riego moderado y repite a los pocos días si ves que la planta lo absorbe bien.\n" +
                "Evita encharcar el sustrato, ya que las raíces podrían dañarse si pasan de mucha sequía a un exceso de humedad repentino.\n" +
                "Sustrato y drenaje:\n" +
                "\n" +
                "Asegúrate de que la maceta tenga buen drenaje y el sustrato sea poroso (mezcla para cactus o suculentas).\n" +
                "Si el sustrato está muy compacto, trasplanta a uno más ligero para que el agua llegue a las raíces y no se estanque.\n" +
                "Observa la recuperación:\n" +
                "\n" +
                "Tras recuperar el riego, los segmentos firmes y verdes volverán a aparecer en unas semanas.\n" +
                "Las partes muy arrugadas o secas pueden no recuperarse, pero la planta producirá nuevos brotes sanos si el problema se corrige.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'La insuficiencia de luz en el cactus colgante se manifiesta con tallos alargados y débiles (crecimiento etiolado), color más pálido o amarillento, y brotes que crecen hacia la fuente de luz. La planta puede dejar de florecer y su crecimiento será lento o deformado. En casos prolongados, los segmentos pueden volverse blandos y caer.','Aumentar la luz disponible:\n" +
                "\n" +
                "Coloca el cactus colgante en un lugar con abundante luz indirecta brillante. Una ventana orientada al este o al sur (protegida del sol directo fuerte) es ideal.\n" +
                "Si la luz natural es insuficiente, puedes complementar con una lámpara de cultivo LED adecuada para suculentas, colocándola a 20-30 cm de la planta, durante 10-12 horas al día.\n" +
                "Evita cambios bruscos:\n" +
                "\n" +
                "Si la planta ha estado mucho tiempo en poca luz, aumenta la exposición gradualmente para evitar quemaduras solares. Muévela poco a poco a un lugar más luminoso durante varios días.\n" +
                "Rotación de la maceta:\n" +
                "\n" +
                "Gira la maceta cada semana para que todos los lados reciban luz y evitar que la planta crezca torcida hacia un solo lado.\n" +
                "Poda de tallos etiolados:\n" +
                "\n" +
                "Si tiene segmentos muy alargados y débiles por falta de luz, pódalos con tijeras limpias. Esto estimula el crecimiento de nuevos brotes más compactos y vigorosos.\n" +
                "Observa la recuperación:\n" +
                "\n" +
                "En unas semanas, notarás nuevos brotes más verdes y fuertes si la planta recibe suficiente luz. Los segmentos deformados no se corregirán, pero los nuevos crecerán sanos.',35)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'La pudrición del tallo en el cactus colgante es una de las enfermedades más graves y comunes, causada principalmente por el exceso de humedad, mal drenaje, o infecciones fúngicas y bacterianas. Se manifiesta con tallos blandos, decolorados (marrones o negros), húmedos o con mal olor. Las zonas afectadas pueden colapsar y desintegrarse fácilmente al tocarlas. Si no se atiende rápidamente, la pudrición puede extenderse y matar toda la planta.','Detener el riego inmediatamente:\n" +
                "\n" +
                "Suspende cualquier riego hasta que el sustrato esté completamente seco.\n" +
                "Revisar y eliminar partes afectadas:\n" +
                "\n" +
                "Usa tijeras o cuchillos limpios y desinfectados para cortar todas las partes blandas o negras del cactus colgante, incluyendo el tallo afectado.\n" +
                "Asegúrate de cortar hasta encontrar tejido sano (firme y de color normal).\n" +
                "Desecha todo el material enfermo; no lo compostes.\n" +
                "Desinfectar las heridas:\n" +
                "\n" +
                "Espolvorea las zonas cortadas con canela en polvo, azufre o un fungicida específico para cactus para prevenir reinfecciones.\n" +
                "Mejorar el sustrato y drenaje:\n" +
                "\n" +
                "Si el sustrato está muy húmedo o compacto, trasplanta el cactus colgante a una mezcla específica para cactus y suculentas, bien aireada y con gran capacidad de drenaje.\n" +
                "Asegúrate de que la maceta tenga agujeros de drenaje.\n" +
                "Reiniciar la planta si es necesario:\n" +
                "\n" +
                "Si la mayoría de la planta está podrida, rescata segmentos sanos y déjalos secar (cicatrizar) 2-3 días antes de plantarlos en sustrato nuevo y seco.\n" +
                "Así podrás iniciar una nueva planta evitando la enfermedad.\n" +
                "Ajustar el riego futuro:\n" +
                "\n" +
                "Riega solo cuando el sustrato esté completamente seco, especialmente en épocas frías o húmedas.\n" +
                "Evita dejar agua acumulada en platos bajo la maceta.\n" +
                "Control preventivo:\n" +
                "\n" +
                "Puedes aplicar fungicida preventivo de vez en cuando, sobre todo tras podas o trasplantes.',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'Las cochinillas son una de las plagas más comunes en cactus colgantes. Se presentan como pequeños bultos algodonosos, blanquecinos o marrón claro, que se adhieren a los tallos y segmentos. Se alimentan de la savia de la planta, debilitándola y provocando zonas amarillentas, crecimiento lento, deformaciones e incluso la aparición de melaza (sustancia pegajosa que atrae hongos). Si no se controlan, pueden causar la caída de segmentos y la muerte de la planta.','Revisión y eliminación manual:\n" +
                "\n" +
                "Revisa frecuentemente el cactus colgante, especialmente en las uniones de los segmentos y zonas ocultas.\n" +
                "Retira las cochinillas manualmente con un algodón o bastoncillo empapado en alcohol de 70°.\n" +
                "Tratamiento con insecticida:\n" +
                "\n" +
                "Si la infestación es grande, aplica un insecticida específico para cochinillas, preferiblemente apto para cactus y suculentas. Sigue las instrucciones del fabricante.\n" +
                "Alternativamente, puedes usar jabón potásico diluido en agua para rociar la planta, repitiendo el tratamiento cada 5-7 días hasta eliminar la plaga.\n" +
                "Aislamiento:\n" +
                "\n" +
                "Aísla la planta afectada para evitar que la plaga se propague a otras plantas cercanas.\n" +
                "Mejorar condiciones de cultivo:\n" +
                "\n" +
                "Mantén la planta en un lugar bien ventilado y con suficiente luz, ya que ambientes oscuros y húmedos favorecen la aparición de cochinillas.\n" +
                "Evita el exceso de abono nitrogenado, que puede debilitar la planta y hacerla más susceptible.\n" +
                "Prevención:\n" +
                "\n" +
                "Inspecciona cualquier planta nueva antes de colocarla cerca del cactus colgante.\n" +
                "Realiza revisiones periódicas, sobre todo en primavera y verano, cuando las cochinillas son más activas.',13)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'Las manchas marrones en el cactus colgante pueden deberse a varias causas: quemaduras solares, exceso de riego (infecciones fúngicas o bacterianas), daños físicos, deficiencias nutricionales o ataques de plagas. Estas manchas suelen ser irregulares, pueden aparecer en cualquier parte del tallo y a veces se acompañan de zonas blandas o secas. Es importante observar si las manchas son hundidas, secas, húmedas, o si se extienden rápidamente, para identificar la causa exacta.','Identificar la causa:\n" +
                "\n" +
                "Observa si las manchas están en la parte de la planta expuesta al sol (posible quemadura solar) o en zonas con poca ventilación y sustrato húmedo (probable infección).\n" +
                "Revisa si hay signos de plagas o daño físico reciente.\n" +
                "Corrección de riego:\n" +
                "\n" +
                "Si el sustrato está húmedo y las manchas son blandas/húmedas, reduce el riego y asegúrate de que la maceta tenga buen drenaje.\n" +
                "Deja secar completamente el sustrato antes de volver a regar.\n" +
                "Tratamiento de infecciones:\n" +
                "\n" +
                "Si sospechas de hongos o bacterias (manchas acuosas o que se extienden), recorta las zonas afectadas con una herramienta limpia y aplica fungicida en los cortes.\n" +
                "No rocíes agua sobre la planta hasta que sane.\n" +
                "Evitar quemaduras solares:\n" +
                "\n" +
                "Si las manchas aparecen tras exponer la planta al sol directo, cambia el cactus colgante a un lugar con luz brillante pero indirecta.\n" +
                "Acostumbra gradualmente la planta a más luz si es necesario.\n" +
                "Control de plagas:\n" +
                "\n" +
                "Si hay pequeños insectos cerca de las manchas, trata la planta con un insecticida adecuado o jabón potásico.\n" +
                "Apoyo nutricional:\n" +
                "\n" +
                "Si la planta no ha sido abonada en mucho tiempo, aplica un fertilizante suave para cactus en primavera/verano.\n" +
                "Prevención y observación:\n" +
                "\n" +
                "Realiza revisiones periódicas para detectar manchas nuevas y actuar lo antes posible.\n" +
                "Mantén el ambiente ventilado y no amontones las plantas para evitar propagación de enfermedades.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'La mancha bacteriana en el cactus colgante es una enfermedad causada por bacterias patógenas que infectan los tejidos de la planta. Se caracteriza por la aparición de manchas marrones, negruzcas o translúcidas, a menudo con bordes amarillentos. Estas manchas pueden ser húmedas, blandas y a veces exudan un líquido pegajoso. El tejido afectado se debilita, puede desprenderse o pudrirse, y la enfermedad puede propagarse rápidamente si no se controla, especialmente en ambientes cálidos y húmedos.','Aislamiento y prevención:\n" +
                "\n" +
                "Separa inmediatamente la planta afectada de otras para evitar el contagio.\n" +
                "Eliminación de partes afectadas:\n" +
                "\n" +
                "Usa tijeras o cuchillos bien desinfectados para cortar todos los segmentos con manchas bacterianas.\n" +
                "Desinfecta la herramienta entre cada corte para no propagar la bacteria.\n" +
                "Tratamiento de heridas:\n" +
                "\n" +
                "Deja secar las heridas al aire durante 2-3 días en un lugar seco y ventilado.\n" +
                "Aplica canela en polvo, carbón activado o un bactericida específico sobre las heridas para prevenir infecciones secundarias.\n" +
                "Control del riego:\n" +
                "\n" +
                "Suspende el riego hasta que el sustrato esté bien seco y la planta haya cicatrizado.\n" +
                "Evita mojar los tallos al regar; hazlo directamente sobre el sustrato.\n" +
                "Mejora de condiciones ambientales:\n" +
                "\n" +
                "Asegura buena ventilación y evita el exceso de humedad ambiental.\n" +
                "Mantén la planta en un lugar con luz indirecta y temperatura estable.\n" +
                "Prevención futura:\n" +
                "\n" +
                "Esteriliza siempre las herramientas de poda.\n" +
                "No agrupes demasiado las plantas para favorecer la circulación de aire.\n" +
                "Evita el exceso de agua y asegúrate de que el sustrato drene bien.',36)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'Una flor marchita en el cactus colgante ocurre cuando la flor, después de abrirse, se seca rápidamente, se vuelve marrón y se cae prematuramente. Esto puede deberse a causas naturales (las flores de cactus suelen durar poco), pero también puede indicar problemas como falta o exceso de riego, cambios bruscos de temperatura, corriente de aire frío, insuficiente luz, estrés por trasplante o incluso ataques de plagas. Las flores marchitas pueden ser normales, pero si ocurre repetidamente antes de abrirse completamente o si la planta no logra mantener las flores, es señal de que algo no está bien en sus cuidados.','Revisión de riego:\n" +
                "\n" +
                "Asegúrate de mantener un riego moderado: ni exceso ni sequía. Durante la floración, el sustrato debe mantenerse apenas húmedo, nunca empapado ni completamente seco.\n" +
                "Condiciones ambientales estables:\n" +
                "\n" +
                "Evita cambios bruscos de temperatura y protege la planta de corrientes de aire frío o caliente.\n" +
                "Mantén la planta en un ambiente con temperatura estable, idealmente entre 18-25 °C.\n" +
                "Luz adecuada:\n" +
                "\n" +
                "Durante la floración, el cactus colgante necesita abundante luz indirecta. Una ventana muy luminosa (sin sol directo intenso) es lo ideal.\n" +
                "Evitar el estrés:\n" +
                "\n" +
                "No trasplantes ni cambies de ubicación la planta cuando esté en botón floral o floración, ya que esto puede causar la caída o marchitez de las flores.\n" +
                "Revisión de plagas:\n" +
                "\n" +
                "Verifica que no haya cochinillas ni otros insectos en los botones florales o flores, ya que pueden dañarlas y provocar su marchitamiento prematuro.\n" +
                "Retira flores marchitas:\n" +
                "\n" +
                "Elimina las flores secas cortándolas con una tijera limpia para evitar que se desarrollen hongos o infecciones.\n" +
                "Abonado adecuado:\n" +
                "\n" +
                "Aplica un fertilizante suave para cactus con bajo contenido en nitrógeno, preferiblemente durante la temporada de crecimiento, para favorecer una floración sana.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (150,'Las orugas pueden atacar el cactus colgante, aunque no es muy común. Se presentan como pequeños gusanos verdes, marrones o rayados que mastican los segmentos, dejando agujeros, bordes irregulares y restos de excrementos. Si la infestación es fuerte, pueden causar daños severos, debilitando la planta y favoreciendo la entrada de hongos y bacterias.','Inspección y eliminación manual:\n" +
                "\n" +
                "Examina el cactus colgante, especialmente en los pliegues y partes ocultas, para localizar orugas y huevos.\n" +
                "Retira manualmente las orugas usando guantes o pinzas y elimínalas.\n" +
                "Limpieza del entorno:\n" +
                "\n" +
                "Retira hojas secas, restos vegetales y flores marchitas donde puedan esconderse huevos o larvas.\n" +
                "Mantén el área alrededor de la planta limpia y libre de residuos.\n" +
                "Tratamiento con insecticida:\n" +
                "\n" +
                "Si la infestación persiste, utiliza un insecticida biológico como Bacillus thuringiensis (Bt), seguro para cactus y mascotas.\n" +
                "Aplica según las instrucciones del fabricante, asegurándote de cubrir bien toda la planta.\n" +
                "Prevención:\n" +
                "\n" +
                "Coloca la planta en lugares ventilados y revisa regularmente, sobre todo en primavera y verano cuando las orugas son más activas.\n" +
                "Si cultivas al aire libre, revisa tras lluvias o vientos fuertes que pueden traer huevos o larvas nuevas.\n" +
                "Monitoreo posterior:\n" +
                "\n" +
                "Vigila los segmentos dañados; si se ablandan o ennegrecen, córtalos con una herramienta limpia para evitar infecciones.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'Las cicatrices en el cactus catedral (Euphorbia trigona) son marcas que aparecen en el tallo, generalmente como resultado de heridas físicas, daños por plagas, quemaduras solares, antiguos ataques de hongos o bacterias, o incluso por el crecimiento natural. Las cicatrices suelen verse como líneas, parches secos, áreas corchosas o cambios de color (blanco, marrón o grisáceo). Aunque no siempre afectan la salud de la planta, pueden indicar que sufrió algún estrés o daño en el pasado.','Identificar la causa:\n" +
                "\n" +
                "Analiza si la cicatriz fue causada por golpes, podas, quemaduras, plagas (como cochinillas) o infecciones antiguas. Si la causa persiste, actúa para eliminarla.\n" +
                "Limpieza y desinfección:\n" +
                "\n" +
                "Si la cicatriz es reciente o se ve húmeda/blanda, recorta cuidadosamente el área afectada con una herramienta limpia y aplica canela en polvo o azufre para evitar infecciones.\n" +
                "Si la cicatriz está seca y corchosa, no requiere tratamiento; solo vigila que no se extienda ni aparezcan zonas blandas o húmedas.\n" +
                "Prevención de nuevas cicatrices:\n" +
                "\n" +
                "Manipula la planta con cuidado para evitar golpes y heridas.\n" +
                "Protege el cactus de la luz solar directa excesiva, especialmente si ha estado a la sombra.\n" +
                "Controla plagas y enfermedades de inmediato para evitar daños mayores.\n" +
                "Cuidados generales para la recuperación:\n" +
                "\n" +
                "Mantén un riego moderado, solo cuando el sustrato esté seco.\n" +
                "Usa un sustrato bien drenante y una maceta con buen drenaje.\n" +
                "Asegura buena ventilación y evita la humedad excesiva.\n" +
                "Valor estético:\n" +
                "\n" +
                "Las cicatrices antiguas no afectan la vida ni el crecimiento de la Euphorbia trigona y pueden considerarse parte de la “historia” de la planta.\n" +
                "Si la cicatriz es muy visible y te incomoda, puedes estimular el crecimiento lateral para que nuevas ramas la disimulen.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'Las manchas marrones en el cactus catedral (Euphorbia trigona) pueden deberse a varias causas, como quemaduras solares, exceso de riego, infecciones fúngicas o bacterianas, daños físicos o cicatrices por crecimiento. Estas manchas pueden ser secas y superficiales, hundidas y blandas, o incluso húmedas y extensibles. Identificar el tipo de mancha y su causa es clave para un tratamiento adecuado.','Identificar la causa:\n" +
                "\n" +
                "Si las manchas aparecen en el lado expuesto al sol y son secas o blanquecinas/marrones, probablemente sean quemaduras solares.\n" +
                "Si son húmedas, blandas, se extienden o huelen mal, puede tratarse de un problema de pudrición por hongos o bacterias.\n" +
                "Si son secas y corchosas, podrían ser cicatrices por daños antiguos o golpes.\n" +
                "Quemaduras solares:\n" +
                "\n" +
                "Cambia la planta a un lugar con luz brillante pero indirecta, especialmente si estuvo previamente en sombra.\n" +
                "Acostumbra poco a poco a mayor luz si es necesario.\n" +
                "Exceso de riego o infección:\n" +
                "\n" +
                "Suspende el riego y verifica que el sustrato drene bien.\n" +
                "Si la mancha es blanda/húmeda, corta la zona afectada con herramienta estéril hasta eliminar todo el tejido dañado.\n" +
                "Deja cicatrizar la herida 2-3 días antes de volver a regar.\n" +
                "Espolvorea canela en polvo, azufre o un fungicida sobre las heridas.\n" +
                "Daños físicos o cicatrices:\n" +
                "\n" +
                "Si las manchas no se agrandan y están secas, no requieren tratamiento, solo observación.\n" +
                "Mantén la planta en buen estado general para que siga creciendo sanamente.\n" +
                "Prevención:\n" +
                "\n" +
                "Usa siempre sustrato bien drenante y riega solo cuando esté completamente seco.\n" +
                "Evita golpes y cambios bruscos de luz o temperatura.\n" +
                "Controla plagas y enfermedades de inmediato.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'Copilot said: **Descripción del problema:** La falta de\n" +
                "Descripción del problema:\n" +
                "La falta de riego en el cactus catedral (Euphorbia trigona) se manifiesta principalmente por tallos arrugados, flácidos o delgados, pérdida de turgencia, color apagado y en casos severos, caída de hojas (si las tiene) o de brotes nuevos. Aunque es una planta muy resistente a la sequía, un déficit de agua prolongado puede afectar su crecimiento y apariencia.','Revisión del sustrato:\n" +
                "\n" +
                "Verifica que el sustrato esté completamente seco antes de volver a regar. Si está seco en profundidad, la planta necesita agua.\n" +
                "Riego adecuado:\n" +
                "\n" +
                "Riega de manera abundante pero espaciada, asegurándote de que el agua salga por los orificios de drenaje.\n" +
                "Espera a que el sustrato se seque completamente antes de volver a regar.\n" +
                "En primavera y verano, riega cada 2-3 semanas según el clima. En otoño e invierno, riega solo una vez al mes o menos.\n" +
                "Observa la recuperación:\n" +
                "\n" +
                "Después de regar, el cactus catedral recuperará su turgencia y color en pocos días si el problema era la falta de agua.\n" +
                "Si no mejora, verifica que el sustrato drene bien y que no haya otros problemas (raíz podrida, plagas, etc.).\n" +
                "Evita el exceso de riego repentino:\n" +
                "\n" +
                "No intentes compensar la sequía con riegos excesivos, ya que podrías provocar pudrición de raíces. Es mejor reanudar el riego normal poco a poco.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén una rutina de riego acorde a la estación y las condiciones ambientales.\n" +
                "Usa un sustrato para cactus bien aireado y una maceta con drenaje adecuado.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'En el cactus catedral (Euphorbia trigona), la floración es muy rara, especialmente en interiores, pero si llegara a florecer, una flor marchita suele ser normal, ya que la floración en esta especie es breve. Sin embargo, si notas que las flores o brotes florales se marchitan antes de abrirse, se caen prematuramente, o nunca prosperan, puede indicar algún problema ambiental o de cuidados.\n" +
                "\n" +
                "Causas comunes de flor marchita en cactus catedral:\n" +
                "\n" +
                "Estrés hídrico (falta o exceso de riego).\n" +
                "Cambios bruscos de temperatura o corrientes de aire frío/calor.\n" +
                "Falta de luz adecuada.\n" +
                "Plagas (como cochinillas o trips en los botones florales).\n" +
                "Estrés por trasplante o movimiento durante la floración.\n" +
                "Déficit nutricional.','Riego adecuado:\n" +
                "\n" +
                "Riega solo cuando el sustrato esté seco. El exceso o la falta de agua pueden hacer que los brotes florales aborten o se marchiten rápidamente.\n" +
                "Condiciones ambientales estables:\n" +
                "\n" +
                "Evita cambios bruscos de temperatura y corrientes de aire.\n" +
                "Mantén la planta en un ambiente templado (18–25 °C) y bien ventilado.\n" +
                "Luz suficiente:\n" +
                "\n" +
                "Asegura luz abundante e indirecta. Si está en interior, colócala cerca de una ventana luminosa, pero sin sol directo intenso.\n" +
                "Evitar el estrés:\n" +
                "\n" +
                "No cambies de lugar ni trasplantes la planta durante la floración o formación de botones.\n" +
                "Revisión de plagas:\n" +
                "\n" +
                "Examina los brotes y flores en busca de pequeños insectos o restos de melaza pegajosa. Si detectas plagas, trata con un insecticida apropiado.\n" +
                "Fertilización:\n" +
                "\n" +
                "Aplica un fertilizante suave para cactus en la época de crecimiento, pero evita abonar justo cuando la planta esté en flor.\n" +
                "Retira las flores marchitas:\n" +
                "\n" +
                "Corta con una herramienta limpia para evitar infecciones y estimular una posible nueva floración.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'Las orugas pueden atacar el cactus catedral (Euphorbia trigona), aunque no es muy común. Se presentan como pequeños gusanos de color verde, marrón o con rayas, que mastican los tallos y costillas del cactus, dejando agujeros, bordes irregulares y restos de excrementos. Una infestación severa puede debilitar la planta, favorecer la entrada de hongos y bacterias, y afectar su aspecto.','Inspección y eliminación manual:\n" +
                "\n" +
                "Examina cuidadosamente la planta, especialmente entre las costillas y partes ocultas, para localizar orugas y huevos.\n" +
                "Retira manualmente las orugas y restos usando guantes o pinzas.\n" +
                "Limpieza del entorno:\n" +
                "\n" +
                "Elimina restos vegetales, hojas secas y flores marchitas cerca de la planta, ya que pueden servir de refugio para huevos o larvas.\n" +
                "Tratamiento con insecticida biológico:\n" +
                "\n" +
                "Si la infestación persiste, usa un insecticida biológico como Bacillus thuringiensis (Bt), seguro para cactus y otras plantas.\n" +
                "Aplica siguiendo las indicaciones del fabricante, cubriendo toda la planta, especialmente los lugares donde encontraste daños.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén la planta en un sitio ventilado y revisa periódicamente, sobre todo en primavera y verano.\n" +
                "Si el cactus está al aire libre, aumenta la frecuencia de revisión tras lluvias o vientos fuertes.\n" +
                "Vigilancia posterior:\n" +
                "\n" +
                "Observa los segmentos afectados. Si se vuelven blandos o ennegrecen, recórtalos con una herramienta limpia y desinfectada para evitar infecciones.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'La fruta marchita en el cactus catedral (Euphorbia trigona) no es un problema frecuente, ya que esta especie rara vez produce frutos, especialmente en ambientes domésticos. Sin embargo, si tu planta ha generado frutos y estos se marchitan prematuramente, se secan, se caen o se pudren antes de madurar, puede deberse a diversas causas:\n" +
                "\n" +
                "Estrés hídrico (falta o exceso de riego)\n" +
                "Deficiencia de nutrientes\n" +
                "Plagas (insectos que atacan flores/frutos)\n" +
                "Condiciones ambientales inadecuadas (temperatura, humedad, luz)\n" +
                "Daños físicos o estrés por manipulación','Revisión del riego:\n" +
                "\n" +
                "Mantén un riego equilibrado, solo cuando el sustrato esté seco.\n" +
                "Evita el exceso de agua que puede favorecer la pudrición del fruto.\n" +
                "Condiciones ambientales:\n" +
                "\n" +
                "Asegura luz abundante e indirecta.\n" +
                "Mantén la temperatura estable (18–25 °C) y evita corrientes de aire frío/calor.\n" +
                "Nutrición:\n" +
                "\n" +
                "Abona en temporada de crecimiento con un fertilizante suave para cactus, especialmente si la planta produce frutos.\n" +
                "Control de plagas:\n" +
                "\n" +
                "Revisa los frutos y la planta en busca de pequeños insectos (cochinillas, trips, pulgones). Si encuentras plagas, trata con un insecticida suave.\n" +
                "Remoción de frutos marchitos:\n" +
                "\n" +
                "Retira cuidadosamente los frutos secos o podridos con una tijera limpia para evitar infecciones y estimular la salud general de la planta.\n" +
                "Evita el estrés:\n" +
                "\n" +
                "No cambies la planta de ubicación ni la trasplantes durante la fructificación.',32)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'El escarabajo de las hojas es una plaga ocasional en el cactus catedral (Euphorbia trigona). Estos insectos suelen tener el cuerpo ovalado, colores metálicos o marrones, y se alimentan de los tejidos tiernos de la planta, dejando pequeñas mordidas o perforaciones en los tallos, brotes jóvenes u hojas (si las hay). Las larvas también pueden causar daño al alimentarse de la parte inferior de la planta. Si no se controla, una infestación puede debilitar la planta y favorecer la entrada de patógenos.','Identificación y monitoreo:\n" +
                "\n" +
                "Observa la planta en busca de pequeños escarabajos brillantes o marrones y daños característicos (mordidas, agujeros).\n" +
                "Revisa en la base, entre las costillas y en las zonas de crecimiento nuevo.\n" +
                "Eliminación manual:\n" +
                "\n" +
                "Retira los escarabajos y larvas manualmente usando guantes o pinzas.\n" +
                "Sacude suavemente la planta para desalojar insectos y recógelos.\n" +
                "Limpieza del entorno:\n" +
                "\n" +
                "Retira restos vegetales, hojas secas y flores marchitas para evitar refugios de plagas.\n" +
                "Tratamiento con insecticida:\n" +
                "\n" +
                "Si la infestación es fuerte, aplica un insecticida específico para escarabajos, preferentemente biológico (como jabón potásico o piretrinas).\n" +
                "También puedes usar aceite de neem, siguiendo cuidadosamente las instrucciones del fabricante.\n" +
                "Evita mojar en exceso la planta; aplica en horas frescas y repite si es necesario.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén la planta en un lugar ventilado y revisa con regularidad, especialmente en primavera y verano.\n" +
                "Si el cactus está en exterior, coloca barreras físicas como mallas si la plaga es recurrente.\n" +
                "Vigilancia posterior:\n" +
                "\n" +
                "Observa los segmentos dañados por si se ablandan o ennegrecen, y córtalos con herramientas limpias si es necesario para evitar infecciones secundarias.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'En el cactus catedral (Euphorbia trigona), aunque la floración es muy poco común, puede ocurrir que tras la floración algunas partes de la planta se marchiten, se debiliten o incluso mueran. Este marchitamiento posfloración puede deberse a un proceso natural de desgaste, pero también puede estar relacionado con estrés, cuidados inadecuados o infecciones oportunistas.\n" +
                "\n" +
                "Causas comunes del marchitamiento después de la floración:\n" +
                "\n" +
                "Desgaste natural: En muchas suculentas y cactus, después de la floración, el segmento que floreció puede debilitarse o secarse porque la planta invierte mucha energía en la flor.\n" +
                "Estrés hídrico: El proceso de floración consume recursos, y si faltó agua durante este período, la planta puede mostrar signos de deshidratación después.\n" +
                "Exceso de riego: Si se aumentó el riego durante o después de la floración, puede haber pudrición.\n" +
                "Infecciones: Las heridas en el área de la flor o restos de flores marchitas pueden ser puerta de entrada para hongos o bacterias.\n" +
                "Déficit nutricional: Agotamiento de nutrientes tras el esfuerzo de la floración.','Poda de restos florales:\n" +
                "\n" +
                "Retira cuidadosamente las flores marchitas o restos secos con tijeras limpias para evitar infecciones.\n" +
                "Revisión del riego:\n" +
                "\n" +
                "Mantén un riego moderado, solo cuando el sustrato esté seco.\n" +
                "Evita encharcamientos y asegúrate de que la maceta tenga buen drenaje.\n" +
                "Vigilancia de síntomas de pudrición:\n" +
                "\n" +
                "Si el segmento que floreció se vuelve blando, oscuro o se hunde, corta la zona afectada con herramienta desinfectada hasta tejido sano.\n" +
                "Deja cicatrizar la herida antes de volver a regar.\n" +
                "Nutrición:\n" +
                "\n" +
                "Fertiliza suavemente en la temporada de crecimiento (primavera-verano) para ayudar a la recuperación, pero nunca abones justo después de la floración si la planta está estresada.\n" +
                "Condiciones ambientales:\n" +
                "\n" +
                "Mantén la planta en un lugar con luz abundante e indirecta y buena ventilación.\n" +
                "Evita cambios bruscos de temperatura y corrientes de aire.\n" +
                "Observación a largo plazo:\n" +
                "\n" +
                "Es normal que un segmento envejezca y se seque tras la floración. Mientras el resto de la planta esté sano, solo corta la parte seca si es antiestética.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (160,'El envejecimiento en el cactus catedral (Euphorbia trigona) es un proceso natural. Con el paso de los años, la planta puede mostrar signos de vejez, como segmentos basales leñosos o secos, pérdida de turgencia en las partes antiguas, cicatrices, coloración más opaca, y menor ritmo de crecimiento. En casos muy avanzados, los brotes viejos pueden secarse o morir, mientras que los brotes nuevos siguen creciendo en la parte superior o lateral.\n" +
                "\n" +
                "Signos normales de envejecimiento:\n" +
                "\n" +
                "Base del tallo se vuelve leñosa, corchosa o marrón.\n" +
                "Segmentos viejos desarrollan cicatrices o zonas resecas.\n" +
                "Algunas ramas antiguas se secan y pueden caerse.\n" +
                "Disminución del crecimiento en los extremos más viejos, mientras los nuevos crecen sanos.','Eliminación de partes viejas:\n" +
                "\n" +
                "Corta las ramas o segmentos que estén completamente secos o muertos con una herramienta limpia y desinfectada.\n" +
                "Esto mejora la estética, previene infecciones y estimula el crecimiento lateral.\n" +
                "Estimulación del crecimiento:\n" +
                "\n" +
                "Si la planta envejece mucho en la base, favorece la producción de brotes laterales o hijuelos podando ligeramente la punta de los tallos principales.\n" +
                "Cuidados de la base leñosa:\n" +
                "\n" +
                "No es necesario cortar la base leñosa si está firme y sin signos de pudrición. Es normal en plantas adultas.\n" +
                "Renovación de sustrato:\n" +
                "\n" +
                "Si la planta lleva muchos años en la misma maceta, considera trasplantar y renovar el sustrato para mejorar la disponibilidad de nutrientes.\n" +
                "Propagación:\n" +
                "\n" +
                "Si la parte superior está sana y la base muy deteriorada, puedes cortar un segmento sano y enraizarlo como nueva planta.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén una rutina de riego adecuada, buen drenaje y luz indirecta abundante para retrasar los efectos negativos del envejecimiento.',27)")

        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'La insuficiencia de luz es un problema común en el cactus cacahuete (Echinopsis chamaecereus), ya que es una especie que necesita mucha luz brillante para crecer sano y compacto. Cuando no recibe la cantidad adecuada de luz, presenta los siguientes síntomas:\n" +
                "\n" +
                "Síntomas de insuficiencia de luz en el cactus cacahuete:\n" +
                "\n" +
                "Tallos alargados, delgados y pálidos (“etoliados”).\n" +
                "Crecimiento desordenado o hacia un lado (buscando la luz).\n" +
                "Color verde pálido o amarillento.\n" +
                "Disminución o ausencia de floración.\n" +
                "Segmentos blandos o débiles que se rompen fácilmente.\n" +
                "Desarrollo de pocas ramificaciones.','Aumenta la exposición a la luz:\n" +
                "\n" +
                "Coloca el cactus cacahuete en el lugar más luminoso posible, idealmente junto a una ventana orientada al sur o al este.\n" +
                "Si lo trasladas desde un sitio muy oscuro, hazlo gradualmente para evitar quemaduras solares.\n" +
                "Luz artificial:\n" +
                "\n" +
                "Si no tienes suficiente luz natural, utiliza una lámpara de cultivo LED para suculentas y cactus, colocándola a 20-30 cm del cactus y encendiéndola 10-12 horas al día.\n" +
                "Gira la maceta regularmente:\n" +
                "\n" +
                "Rota la planta cada semana para que reciba luz de todos lados y crezca de manera uniforme.\n" +
                "Poda los tallos etiolados:\n" +
                "\n" +
                "Si los tallos están muy alargados y débiles, puedes podarlos y aprovechar los segmentos sanos para hacer esquejes, replantándolos cuando cicatricen.\n" +
                "Evita el exceso de riego:\n" +
                "\n" +
                "En condiciones de poca luz, la planta consume menos agua y es más susceptible a pudrición. Riega solo cuando el sustrato esté completamente seco.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén siempre tu cactus cacahuete en lugares bien iluminados, especialmente en primavera y verano.\n" +
                "En invierno, acércalo a la ventana o refuerza con luz artificial.',35)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'La pudrición del tallo es uno de los problemas más graves en el cactus cacahuete (Echinopsis chamaecereus). Suele ser causada por exceso de riego, sustrato mal drenado o infecciones por hongos y bacterias. Se manifiesta como zonas blandas, oscuras o acuosas en los tallos, mal olor, y colapso de segmentos enteros. Si no se trata a tiempo, puede extenderse y matar la planta.\n" +
                "\n" +
                "Síntomas de pudrición del tallo en Echinopsis chamaecereus:\n" +
                "\n" +
                "Tallos blandos, marrones, negros o translúcidos.\n" +
                "Mal olor en la zona afectada.\n" +
                "Segmentos que se desprenden fácilmente.\n" +
                "Avance rápido de la zona enferma, sobre todo después de riego o lluvias.','Actuar rápido:\n" +
                "\n" +
                "Retira la planta de la maceta y revisa el estado de los tallos y raíces.\n" +
                "Elimina con una herramienta limpia y esterilizada todos los segmentos afectados, cortando hasta tejido completamente sano.\n" +
                "Desecha todo el material blando, marrón o con mal olor.\n" +
                "Secado y desinfección:\n" +
                "\n" +
                "Deja cicatrizar los segmentos sanos en un lugar seco y sombreado durante varios días (2-7 días) hasta que la herida se endurezca.\n" +
                "Puedes espolvorear canela en polvo o azufre sobre las heridas para prevenir hongos.\n" +
                "Renueva el sustrato:\n" +
                "\n" +
                "Usa siempre sustrato para cactus y suculentas, suelto y con excelente drenaje.\n" +
                "Limpia o reemplaza la maceta si es posible.\n" +
                "Replantar:\n" +
                "\n" +
                "Replanta los segmentos sanos solo cuando las heridas estén bien cicatrizadas.\n" +
                "No riegues inmediatamente: espera unos días para que las raíces nuevas comiencen a formarse y luego riega muy ligeramente.\n" +
                "Prevención:\n" +
                "\n" +
                "Reduce el riego: solo riega cuando el sustrato esté completamente seco.\n" +
                "Evita mojar los tallos al regar.\n" +
                "Proporciónale buena ventilación y luz abundante, pero sin sol directo intenso tras el trasplante.\n" +
                "Nota:\n" +
                "Si la pudrición es muy extensa y no quedan segmentos sanos, lamentablemente es difícil salvar la planta. Siempre es recomendable propagar segmentos sanos en cuanto detectes el problema.',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'Las cicatrices en el cactus cacahuete (Echinopsis chamaecereus) son marcas visibles en los tallos, generalmente de color marrón, corcho, blanco o grisáceo. Estas pueden deberse a daños físicos, heridas por espinas, quemaduras solares, antiguos ataques de plagas, golpes, cortes, o incluso al envejecimiento natural de los segmentos más viejos. También pueden aparecer cicatrices después de que una herida sana correctamente.\n" +
                "\n" +
                "Causas comunes de cicatrices en el cactus cacahuete:\n" +
                "\n" +
                "Daños mecánicos: golpes, caídas, manejo brusco.\n" +
                "Hojas o tallos rotos.\n" +
                "Heridas por animales, insectos o plagas.\n" +
                "Cicatrización tras la poda o después de quitar segmentos podridos.\n" +
                "Quemaduras solares (marcas blanquecinas o corchosas).\n" +
                "Envejecimiento normal de los tallos.','No es necesario tratarlas:\n" +
                "Si la cicatriz es seca y dura, puedes dejarla como está. Es parte del proceso natural de sanación.\n" +
                "\n" +
                "Prevenir infecciones:\n" +
                "Si la herida fue reciente, asegúrate de que la zona esté seca. Puedes aplicar canela en polvo para prevenir hongos.\n" +
                "\n" +
                "Evita nuevas heridas:\n" +
                "Maneja tu cactus con cuidado y colócalo en un sitio donde no pueda ser golpeado o rozado fácilmente.\n" +
                "\n" +
                "Optimiza los cuidados:\n" +
                "Proporciona buena iluminación, riego adecuado y ventilación para fortalecer la planta y mejorar su capacidad de sanar.\n" +
                "\n" +
                "Elimina segmentos dañados solo si es necesario:\n" +
                "Si la zona cicatrizada se vuelve blanda, negra o húmeda, corta el segmento afectado con una herramienta desinfectada.\n" +
                "\n" +
                "Resumen:\n" +
                "Las cicatrices son comunes y, en la mayoría de los casos, no afectan la salud general del cactus cacahuete. Solo debes vigilar que no evolucionen a pudrición o infección.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'El envejecimiento en el cactus cacahuete (Echinopsis chamaecereus) es un proceso natural que se observa a medida que la planta madura y envejece. Aunque este cactus es de crecimiento rápido y tiende a ramificarse mucho, con el paso de los años es normal ver algunos signos de vejez, especialmente en los segmentos más antiguos.\n" +
                "\n" +
                "Signos normales de envejecimiento:\n" +
                "\n" +
                "Base leñosa o corchosa: Los tallos más viejos, cerca de la base, pueden volverse marrones, duros y secos, con textura de corcho.\n" +
                "Segmentos secos o deshidratados: Los “dedos” más antiguos pueden secarse, arrugarse o perder turgencia.\n" +
                "Cicatrices: Es común que los segmentos viejos muestren cicatrices o marcas antiguas.\n" +
                "Menor crecimiento en zonas viejas: La planta tiende a crecer vigorosamente por los extremos y ramificaciones nuevas, mientras que las partes antiguas pierden actividad.\n" +
                "¿Es un problema?\n" +
                "En la mayoría de los casos, el envejecimiento es un proceso natural y no representa un riesgo para la planta, siempre y cuando las zonas nuevas estén sanas y creciendo. Sin embargo, si la base envejecida comienza a pudrirse o a afectar el resto de la planta, sí puede volverse un problema.','Elimina segmentos muertos o secos:\n" +
                "Corta con cuidado los “dedos” completamente secos o deshidratados con una herramienta limpia y desinfectada.\n" +
                "\n" +
                "Favorece el crecimiento de nuevos brotes:\n" +
                "Mantén la planta con buena luz, riego adecuado y abona en temporada de crecimiento para estimular ramificaciones jóvenes.\n" +
                "\n" +
                "Renueva el sustrato:\n" +
                "Si la planta lleva años en la misma maceta, trasplanta y renueva el sustrato para mejorar nutrición y drenaje.\n" +
                "\n" +
                "Propagación:\n" +
                "Puedes cortar segmentos jóvenes sanos y enraizarlos para obtener nuevas plantas si la planta madre está muy envejecida en la base.\n" +
                "\n" +
                "Evita excesos de agua:\n" +
                "Los segmentos viejos, al estar secos o leñosos, pueden ser más propensos a pudrición si hay exceso de humedad.\n" +
                "\n" +
                "Resumen:\n" +
                "El envejecimiento es natural en el cactus cacahuete. Retira las partes secas, fomenta el crecimiento de nuevos brotes, y considera la propagación si la base está muy deteriorada. Así, tu planta se mantendrá joven y vigorosa.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'El marchitamiento en el cactus cacahuete (Echinopsis chamaecereus) puede deberse a varias causas y es importante identificar el motivo para aplicar la solución adecuada. Aquí tienes una guía para diagnosticar y tratar el problema:\n" +
                "\n" +
                "Causas comunes de marchitamiento en el cactus cacahuete:\n" +
                "\n" +
                "Exceso de riego / Pudrición de raíces o tallos\n" +
                "\n" +
                "Síntomas: Tallos blandos, oscuros, acuosos o con mal olor; segmentos que se desprenden fácilmente.\n" +
                "Solución: Saca la planta de la maceta, elimina partes podridas, deja secar los segmentos sanos y replanta en sustrato seco y bien drenante. Reduce el riego.\n" +
                "Falta de riego / Deshidratación\n" +
                "\n" +
                "Síntomas: Tallos arrugados, blandos pero sin zonas oscuras; pérdida de turgencia general.\n" +
                "Solución: Riega de manera moderada cuando el sustrato esté completamente seco. No inundes de golpe.\n" +
                "Insuficiencia de luz\n" +
                "\n" +
                "Síntomas: Tallos alargados, delgados, pálidos y débiles.\n" +
                "Solución: Coloca la planta en un sitio con más luz indirecta brillante. Evita el sol directo brusco si viene de un lugar oscuro.\n" +
                "Plagas (cochinillas, ácaros, etc.)\n" +
                "\n" +
                "Síntomas: Presencia de insectos, manchas pegajosas, “algodón” blanco, o puntos amarillos/rojos.\n" +
                "Solución: Elimina plagas manualmente y aplica insecticida específico para cactus.\n" +
                "Sustrato inadecuado o compactado\n" +
                "\n" +
                "Síntomas: Sustrato que no drena, se endurece o se mantiene húmedo mucho tiempo.\n" +
                "Solución: Cambia a sustrato para cactus y suculentas, aireado y con buen drenaje.\n" +
                "Estrés por trasplante o cambios bruscos\n" +
                "\n" +
                "Síntomas: Marchitamiento tras trasplante o cambio de ubicación.\n" +
                "Solución: Mantén la planta en condiciones estables y evita el riego excesivo tras el trasplante.','Observa los tallos: ¿Están blandos y oscuros (pudrición) o arrugados y secos (deshidratación)?\n" +
                "Revisa el sustrato: ¿Está empapado o muy seco?\n" +
                "Examina la luz: ¿La planta recibe suficiente luz?\n" +
                "Busca signos de plagas.\n" +
                "Acción rápida:\n" +
                "Si sospechas de pudrición, corta y salva segmentos sanos. Si es deshidratación, riega poco a poco. Si es falta de luz, traslada gradualmente a un sitio más luminoso.',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'Las abejas pueden interactuar con el cactus cacahuete (Echinopsis chamaecereus) principalmente durante la floración, ya que sus flores son muy atractivas para estos insectos polinizadores. Aquí tienes información relevante sobre este tema:\n" +
                "\n" +
                "¿Las abejas son un problema para el cactus cacahuete?\n" +
                "\n" +
                "No, en general las abejas no dañan al cactus cacahuete.\n" +
                "Su función principal es la polinización. Cuando tu cactus florece, las abejas visitan las flores para recolectar néctar y polen, ayudando a la planta a reproducirse.\n" +
                "No pican ni muerden los tallos ni causan daños a la estructura de la planta.\n" +
                "No transmiten enfermedades ni plagas a los cactus.\n" +
                "Beneficios de las abejas:\n" +
                "\n" +
                "Mejoran la polinización, lo que puede favorecer la formación de frutos y semillas si tienes varias plantas.\n" +
                "Indican un ecosistema saludable en tu espacio de cultivo o jardín.','¿Debo preocuparme si hay muchas abejas en mi cactus?\n" +
                "\n" +
                "No, es una señal positiva, sobre todo en época de floración.\n" +
                "Si hay enjambres (grandes grupos) cerca de tu casa y representan un riesgo para personas alérgicas, consulta a un apicultor para un manejo responsable.\n" +
                "¿Qué hacer si veo abejas en mi cactus cacahuete?\n" +
                "\n" +
                "Disfruta de la visita de estos polinizadores.\n" +
                "No utilices insecticidas cerca de la planta para no dañar a las abejas ni otros insectos útiles.',38)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'Cuando una flor del cactus cacahuete (Echinopsis chamaecereus) se marchita, es completamente normal: las flores de este cactus son efímeras y suelen durar solo uno o dos días. Aquí tienes recomendaciones y explicación sobre cómo manejar una flor marchita:\n" +
                "\n" +
                "¿Por qué se marchita la flor?\n" +
                "\n" +
                "Es parte natural del ciclo de vida de la flor.\n" +
                "Las flores suelen abrirse por la mañana y, tras un día o dos, se cierran y marchitan.\n" +
                "No indica un problema de salud en la planta.','Retira la flor marchita manualmente:\n" +
                "\n" +
                "Una vez que la flor se pone marrón, seca o blanda, puedes retirarla con cuidado tirando suavemente o cortando la base con tijeras limpias.\n" +
                "Esto previene el desarrollo de hongos o podredumbre en la zona donde estuvo la flor.\n" +
                "Deja cicatrizar la herida:\n" +
                "\n" +
                "Si al retirar la flor queda una pequeña herida, no riegues la planta por unos días para evitar infecciones.\n" +
                "Puedes espolvorear canela en polvo como antifúngico natural si lo deseas.\n" +
                "Revisión general:\n" +
                "\n" +
                "Aprovecha para revisar el resto de la planta y asegurarte de que no haya plagas ni otras flores o segmentos en mal estado.\n" +
                "¿Debo preocuparme si todas las flores se marchitan rápido?\n" +
                "\n" +
                "No, es normal. Si las flores no llegan a abrirse o caen siendo capullos, podría deberse a estrés por falta/exceso de agua, calor extremo o traslados, pero la marchitez posterior a la floración es natural.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'El tizón del pétalo es una enfermedad fúngica que afecta principalmente a las flores de cactus y otras suculentas, incluyendo el cactus cacahuete (Echinopsis chamaecereus). Se caracteriza por la aparición de manchas oscuras, marrones o negras en los pétalos, que llevan al marchitamiento prematuro y caída de las flores.\n" +
                "\n" +
                "Causas del tizón del pétalo\n" +
                "Hongos patógenos: Los más comunes son Botrytis cinerea, Alternaria y Cladosporium.\n" +
                "Alta humedad ambiental: El exceso de humedad y mala ventilación favorecen el desarrollo de estos hongos.\n" +
                "Riego por aspersión: Moja los pétalos y facilita la infección.\n" +
                "Restos de flores viejas: Pueden ser foco de esporas fúngicas.\n" +
                "Síntomas\n" +
                "Manchas marrones, negras o grises en los pétalos, a veces con un halo amarillo.\n" +
                "Marchitez y caída rápida de las flores.\n" +
                "Moho grisáceo sobre los pétalos en casos severos.\n" +
                "Las flores afectadas pueden desprender mal olor.','Elimina flores afectadas:\n" +
                "\n" +
                "Retira cuidadosamente las flores con síntomas y deséchalas lejos de tus plantas.\n" +
                "Mejora la ventilación:\n" +
                "\n" +
                "Coloca el cactus en un lugar aireado, evitando el hacinamiento con otras plantas.\n" +
                "Evita mojar las flores:\n" +
                "\n" +
                "Riega directamente el sustrato, nunca los tallos ni las flores.\n" +
                "Reduce la humedad:\n" +
                "\n" +
                "Si cultivas en interiores, considera usar un deshumidificador o ventila regularmente.\n" +
                "Fungicidas (en casos graves):\n" +
                "\n" +
                "Aplica un fungicida adecuado para plantas ornamentales con registro para uso en cactus, siguiendo las indicaciones del fabricante.\n" +
                "Alternativamente, puedes usar soluciones naturales como canela en polvo espolvoreada sobre las flores y el sustrato.\n" +
                "Retira restos de flores viejas:\n" +
                "\n" +
                "Mantén la base de la planta limpia de restos vegetales para evitar focos de infección.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'Copilot said: La **marchitación después de la floración** en\n" +
                "La marchitación después de la floración en el cactus cacahuete (Echinopsis chamaecereus) es completamente normal y forma parte del ciclo natural de la planta. Las flores de este cactus son muy efímeras, generalmente duran solo 1-2 días. Después de ese tiempo, los pétalos se marchitan y la flor se seca. Esto ocurre incluso si la planta está perfectamente sana.\n" +
                "\n" +
                "¿Por qué ocurre?\n" +
                "Ciclo de vida de la flor: Las flores de Echinopsis están diseñadas para abrirse, atraer polinizadores y marchitarse rápidamente.\n" +
                "No indica enfermedad: La floración breve y posterior marchitez es fisiológica.\n" +
                "Excepción: Si los capullos caen sin abrirse o la marchitez es acompañada de manchas negras, mal olor o pudrición, podría indicar un problema fúngico (como tizón del pétalo), exceso de riego o falta de nutrientes.','Eliminar las flores marchitas:\n" +
                "Retira suavemente las flores secas o marchitas. Esto previene la acumulación de humedad y posibles infecciones fúngicas.\n" +
                "\n" +
                "Evita mojar la zona floral:\n" +
                "Al regar, procura no mojar el área donde estuvo la flor para evitar infecciones.\n" +
                "\n" +
                "Revisa la planta:\n" +
                "Asegúrate de que el resto de la planta se vea sano, sin manchas extrañas ni zonas blandas.\n" +
                "\n" +
                "Fertiliza si es necesario:\n" +
                "Después de la floración, puedes añadir un fertilizante especial para cactus para estimular la próxima floración y el crecimiento.\n" +
                "\n" +
                "Resumen\n" +
                "La marchitación tras la floración es normal en el cactus cacahuete. Solo debes preocuparte si observas síntomas inusuales en los tallos o en los capullos (pudrición, manchas, caída sin abrirse).',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (170,'Las cochinillas son una de las plagas más comunes y problemáticas en el cactus cacahuete (Echinopsis chamaecereus). Estos insectos suelen verse como pequeños bultos blancos, algodonosos o marrones que se adhieren a los tallos y en las uniones entre segmentos.\n" +
                "\n" +
                "Síntomas de infestación por cochinillas\n" +
                "Manchas blancas algodonosas o bultitos marrones en los tallos, base o raíces.\n" +
                "Puntos pegajosos (“mielada”) en la superficie de la planta o alrededor de la maceta.\n" +
                "Amarillamiento, debilitamiento y marchitamiento de los segmentos.\n" +
                "Crecimiento detenido y caída de segmentos.\n" +
                "Presencia de hormigas (que suelen “cuidar” las cochinillas por la mielada).','Retirada manual:\n" +
                "\n" +
                "Usa un hisopo de algodón empapado en alcohol (etanol o isopropílico) para quitar las cochinillas visibles.\n" +
                "Puedes enjuagar suavemente con agua tibia las zonas afectadas después del tratamiento.\n" +
                "Aislamiento:\n" +
                "\n" +
                "Separa la planta infestada de otras para evitar contagios.\n" +
                "Control químico:\n" +
                "\n" +
                "Aplica un insecticida específico para cochinillas y seguro para cactus (puedes encontrar productos a base de aceite de neem o insecticidas sistémicos para plantas ornamentales).\n" +
                "Sigue siempre las instrucciones del fabricante.\n" +
                "Control orgánico:\n" +
                "\n" +
                "Rocía una mezcla de agua con jabón potásico o jabón neutro (unas gotas por litro de agua) sobre las zonas afectadas, dejando actuar unos minutos y luego enjuagando.\n" +
                "Revisión de raíces:\n" +
                "\n" +
                "Si la plaga persiste, revisa las raíces. Las cochinillas pueden esconderse ahí y requerir trasplante a sustrato nuevo.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén la planta ventilada y evita el exceso de humedad.\n" +
                "Revisa regularmente para detectar brotes tempranos.\n" +
                "Resumen\n" +
                "Las cochinillas pueden debilitar gravemente tu cactus cacahuete si no se controlan a tiempo. El tratamiento manual con alcohol es seguro y efectivo para casos leves; en infestaciones graves, combina métodos y repite el tratamiento cada semana hasta erradicar la plaga.',13)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'Las cicatrices en el cactus orquídea (Epiphyllum) son un fenómeno relativamente común y, en muchos casos, no representan un peligro serio para la planta. Aquí tienes una guía sobre causas, tipos y manejo:\n" +
                "\n" +
                "¿Por qué aparecen cicatrices en el cactus orquídea?\n" +
                "\n" +
                "Daños mecánicos: Golpes, cortes, rozaduras al mover la planta o durante el trasplante.\n" +
                "Plagas: Pasadas infestaciones de cochinillas, ácaros u otros insectos pueden dejar marcas o costras.\n" +
                "Enfermedades: Infecciones fúngicas o bacterianas pueden dejar cicatrices tras la recuperación.\n" +
                "Cicatrización natural: Después de podas, roturas o caída de segmentos, la planta genera tejido cicatricial.\n" +
                "Quemaduras solares: Exposición repentina al sol directo puede dejar marcas blanquecinas o corchosas.\n" +
                "Envejecimiento: Los tallos más viejos pueden mostrar marcas o costras como parte de su maduración.\n" +
                "¿Qué tipo de cicatrices debes vigilar?\n" +
                "\n" +
                "Cicatrices secas, duras y superficiales:\n" +
                "Son normales y suelen ser inofensivas si la planta sigue creciendo y no hay partes blandas o con mal olor.\n" +
                "Cicatrices húmedas, blandas, negras o que se extienden:\n" +
                "Pueden indicar infección o pudrición y requieren acción inmediata.','Observa el aspecto:\n" +
                "Si la cicatriz es seca, dura y no avanza, no es necesario hacer nada.\n" +
                "Mantén buenas prácticas de cultivo:\n" +
                "Evita golpes y no muevas la planta innecesariamente.\n" +
                "Antiséptico natural:\n" +
                "Si tienes que podar o si la cicatriz es reciente, puedes espolvorear canela en polvo para prevenir hongos.\n" +
                "Vigila la evolución:\n" +
                "Si la cicatriz cambia de color, se vuelve blanda, húmeda o aparece moho, corta la parte afectada con una herramienta estéril y deja cicatrizar.\n" +
                "Prevención:\n" +
                "Protege la planta de exposición solar directa y revisa regularmente para detectar plagas a tiempo.\n" +
                "Resumen:\n" +
                "Las cicatrices secas y duras son normales en el cactus orquídea. Solo intervén si la zona se reblandece, ennegrece o muestra signos de infección.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'La insuficiencia de luz es uno de los problemas más comunes en el cactus orquídea (Epiphyllum), ya que es una planta epífita que requiere buena iluminación para crecer y florecer adecuadamente. A continuación te explico cómo identificarla, sus efectos y cómo corregirla:\n" +
                "\n" +
                "Síntomas de insuficiencia de luz en Epiphyllum\n" +
                "Tallos alargados y delgados: Los segmentos tienden a estirarse (“etiolación”) buscando la luz, y pueden volverse débiles o quebradizos.\n" +
                "Color apagado: Los tallos pierden su color verde intenso y pueden verse amarillentos o pálidos.\n" +
                "Falta de floración: La planta no produce flores o los capullos abortan antes de abrirse.\n" +
                "Crecimiento lento o detenido: La planta deja de producir nuevos segmentos.\n" +
                "Segmentos planos y blandos: Los tallos pueden perder firmeza y textura.','Ubicación ideal:\n" +
                "\n" +
                "Coloca tu Epiphyllum en un lugar con luz indirecta brillante, como cerca de una ventana orientada al este o al oeste.\n" +
                "Evita el sol directo fuerte (especialmente en verano), ya que puede quemar los tallos.\n" +
                "Si solo tienes ventanas al norte, puedes complementar con luz artificial (tubos LED para plantas o focos de espectro completo).\n" +
                "Adaptación gradual:\n" +
                "\n" +
                "Si la planta ha estado en sombra, muévela poco a poco a sitios más luminosos para evitar quemaduras.\n" +
                "Evita lugares oscuros:\n" +
                "\n" +
                "No coloques el cactus orquídea en habitaciones sin ventanas, pasillos o rincones alejados de la luz natural.\n" +
                "Gira la maceta:\n" +
                "\n" +
                "Gira tu planta cada cierto tiempo para que todos los lados reciban luz y evitar crecimiento desigual.\n" +
                "¿Cuánto tarda en recuperarse?\n" +
                "Si corriges la iluminación, la planta puede tardar varias semanas o meses en mostrar mejora.\n" +
                "Los segmentos deformados no recuperarán su forma, pero los nuevos crecerán sanos y vigorosos.\n" +
                "Resumen\n" +
                "El cactus orquídea necesita abundante luz indirecta para crecer y florecer. Si detectas síntomas de falta de luz, reubica la planta gradualmente. Una vez que reciba la luz adecuada, su crecimiento y floración mejorarán notablemente.',35)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'El envejecimiento en el cactus orquídea (Epiphyllum) es un proceso natural que puede observarse a medida que la planta madura. Aquí te explico cómo reconocerlo, qué es normal y cómo manejarlo:\n" +
                "\n" +
                "¿Cómo se manifiesta el envejecimiento en Epiphyllum?\n" +
                "Segmentos viejos y leñosos: Los tallos más antiguos, especialmente cerca de la base, pueden volverse más duros, leñosos o corchosos.\n" +
                "Coloración: Los segmentos viejos pueden perder el color verde intenso y adquirir tonos marrones, grises o amarillentos.\n" +
                "Reducción del crecimiento: Las partes antiguas pueden dejar de producir nuevos brotes o flores.\n" +
                "Cicatrices y marcas: Es común ver cicatrices de antiguas heridas, podas, o zonas donde se desprendieron segmentos.\n" +
                "¿Es un problema?\n" +
                "Generalmente, el envejecimiento no es dañino si la planta sigue produciendo brotes nuevos y sanos. Sin embargo, los segmentos muy viejos pueden ser más susceptibles a enfermedades o plagas, y pueden secarse y morir con el tiempo.','Deja los segmentos si están sanos: Si los tallos viejos siguen firmes y no presentan signos de pudrición, puedes dejarlos en la planta.\n" +
                "Retira partes secas o dañadas: Si ves segmentos completamente secos, blandos, con moho, o que ya no aportan vigor, córtalos con una herramienta limpia y desinfectada.\n" +
                "Fomenta nuevos brotes: Mantén una buena iluminación, riego y nutrición para estimular el crecimiento de segmentos jóvenes.\n" +
                "Renueva la planta: Si la base está muy envejecida, puedes cortar y enraizar segmentos jóvenes para rejuvenecer tu Epiphyllum.\n" +
                "Resumen\n" +
                "El envejecimiento es parte normal del ciclo de vida del cactus orquídea. Retira solo los segmentos secos o enfermos y estimula el crecimiento de brotes nuevos para mantener una planta saludable y vigorosa.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'El marchitamiento en el cactus orquídea (Epiphyllum) puede deberse a varias causas. \n + " +
                "1. Exceso de riego / Pudrición de raíces \n" +
                "2. Falta de riego / Deshidratación \n" +
                "3. Insuficiencia de luz \n" +
                "4. Plagas \n " +
                "5. Sustrato inadecuado o compactado \n" +
                "6. Estrés por trasplante o cambios bruscos','Observa si los tallos están arrugados (falta de agua), blandos y oscuros (pudrición), o pálidos (falta de luz).\n" +
                "Revisa el sustrato: ¿está empapado o muy seco?\n" +
                "Examina la iluminación y presencia de plagas.\n" +
                "Ajusta el riego y la ubicación según el problema detectado.',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'En el cactus orquídea (Epiphyllum), el marchitamiento de los segmentos o de la planta después de la floración no es normal y suele indicar algún problema subyacente. Aquí tienes las causas más comunes: \n" +
                "1. Estres post-floracion \n" +
                "2. Exceso de riego \n" +
                "3. Falta de riego \n" +
                "4. Agotamiento de nutrientes \n" +
                "5. Plagas o enfermedades \n','Observa si los tallos están arrugados (falta de agua), blandos y oscuros (pudrición), o pálidos (falta de luz).\\n\" +\n" +
                "Revisa el sustrato: ¿está empapado o muy seco?\\n\" +\n" +
                "Examina la iluminación y presencia de plagas.\\n\" +\n" +
                "Ajusta el riego y la ubicación según el problema detectado.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'La falta de riego en el cactus orquídea (Epiphyllum) es una causa común de marchitamiento o deshidratación, especialmente durante periodos de crecimiento activo o floración. Este cactus, aunque tolera cierta sequía, necesita más agua que los cactus del desierto debido a su naturaleza epífita.\n" +
                "\n" +
                "Síntomas de falta de riego en Epiphyllum\n" +
                "Segmentos (tallos) arrugados y flácidos, pero no blandos ni con mal olor.\n" +
                "Pérdida de turgencia (firmeza) en los tallos.\n" +
                "Color apagado o amarillento.\n" +
                "Crecimiento lento o detenido.\n" +
                "Caída de capullos o falta de floración.','Riego adecuado:\n" +
                "\n" +
                "Riega cuando la capa superior del sustrato (2-3 cm) esté seca al tacto.\n" +
                "Usa agua a temperatura ambiente y riega hasta que salga por los orificios de drenaje.\n" +
                "En primavera y verano (época de crecimiento y floración), el riego debe ser más frecuente (aprox. 1 vez por semana, dependiendo de temperatura y ventilación).\n" +
                "En otoño e invierno, reduce la frecuencia (cada 2-3 semanas).\n" +
                "Evita el exceso de agua:\n" +
                "\n" +
                "No riegues si el sustrato sigue húmedo o si el clima está frío y húmedo.\n" +
                "El exceso de agua puede causar pudrición de raíces.\n" +
                "Recuperación después de sequía:\n" +
                "\n" +
                "Si la planta está muy seca, hidrátala gradualmente. No inundes de golpe.\n" +
                "Puedes sumergir la maceta en un recipiente con agua durante 15-20 minutos para que el sustrato se humedezca uniformemente (solo si está muy deshidratada).\n" +
                "Resumen\n" +
                "El cactus orquídea necesita riego regular, especialmente en temporada cálida. La falta de riego se manifiesta en tallos arrugados y caída de vigor, pero se recupera bien si se corrige a tiempo.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'Copilot said: La **pudrición del tallo** en el cactus\n" +
                "La pudrición del tallo en el cactus orquídea (Epiphyllum) es un problema serio que puede llevar rápidamente a la pérdida de la planta si no se trata a tiempo. Aquí tienes una guía completa para identificarla, sus causas, y cómo actuar:\n" +
                "\n" +
                "Síntomas de pudrición del tallo en Epiphyllum\n" +
                "Segmentos blandos, acuosos o que se hunden al presionarlos.\n" +
                "Cambio de color: áreas marrones, negras o grisáceas.\n" +
                "Mal olor (a podrido) al tocar o cortar la zona afectada.\n" +
                "Segmentos que se desprenden fácilmente.\n" +
                "En casos avanzados, la pudrición puede avanzar hacia las raíces o a lo largo de los tallos.\n" +
                "Causas principales\n" +
                "Exceso de riego y/o sustrato mal drenado (la causa más común).\n" +
                "Heridas por cortes, golpes o podas mal cicatrizadas.\n" +
                "Bajas temperaturas y humedad alta.\n" +
                "Infección por hongos o bacterias oportunistas (Fusarium, Phytophthora, Erwinia, etc.).\n','Aísla la planta para evitar contagio a otras plantas.\n" +
                "Corta las zonas afectadas:\n" +
                "Usa una herramienta afilada y desinfectada (alcohol o flama).\n" +
                "Elimina todo el tejido blando, oscuro o maloliente, cortando hasta encontrar tejido sano (firme y verde).\n" +
                "Desinfecta la herida:\n" +
                "Espolvorea canela en polvo o aplica carbón activado sobre el corte.\n" +
                "Deja secar la herida al aire al menos 1-3 días antes de replantar.\n" +
                "Revisa las raíces:\n" +
                "Si hay pudrición en las raíces, elimina todas las partes dañadas.\n" +
                "Si sólo quedan segmentos sanos, puedes enraizarlos como esquejes.\n" +
                "Replanta en sustrato seco y bien drenante:\n" +
                "Usa un sustrato para epífitas/cactus con buena aireación.\n" +
                "No riegues hasta una semana después del trasplante.\n" +
                "Ajusta el riego:\n" +
                "Riega solo cuando el sustrato esté casi seco.\n" +
                "Evita dejar agua estancada en el plato.\n" +
                "Prevención\n" +
                "Evita el exceso de riego y revisa que la maceta tenga buen drenaje.\n" +
                "Usa sustratos aireados (mezcla de corteza, perlita, turba, poca tierra).\n" +
                "No expongas la planta al frío y la humedad al mismo tiempo.\n" +
                "Desinfecta herramientas antes de podar o tomar esquejes.\n" +
                "Resumen\n" +
                "La pudrición del tallo es grave pero, actuando rápido, puedes salvar parte de tu planta. El corte de zonas afectadas y la prevención son clave.',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'El escarabajo de las hojas puede afectar ocasionalmente al cactus orquídea (Epiphyllum), aunque es una plaga mucho más común en plantas de jardín que en epífitas. Sin embargo, cuando estos insectos atacan, pueden causar daños significativos en los tallos (segmentos) de la planta.\n" +
                "\n" +
                "Síntomas de ataque de escarabajo de las hojas en Epiphyllum\n" +
                "Orificios irregulares o bordes mordidos en los segmentos (tallos) de la planta.\n" +
                "Manchas marrones donde han comido los escarabajos.\n" +
                "Presencia de pequeños escarabajos (generalmente de color marrón, verde o metálico) en la planta, especialmente al atardecer o de noche.\n" +
                "Excrementos pequeños y oscuros en la superficie de la planta o sustrato.','Inspección manual:\n" +
                "\n" +
                "Revisa la planta durante la tarde/noche y retira manualmente los escarabajos visibles.\n" +
                "Sacude suavemente la planta para hacer caer los insectos.\n" +
                "Limpieza de daños:\n" +
                "\n" +
                "Retira segmentos muy dañados o con signos de pudrición secundaria.\n" +
                "Las heridas pequeñas suelen cicatrizar solas si la planta está sana.\n" +
                "Control biológico y cultural:\n" +
                "\n" +
                "Mantén la planta en un sitio aireado y separado de plantas muy infestadas.\n" +
                "Atrae aves o introduce depredadores naturales si es posible (en jardines).\n" +
                "Control químico (si la plaga es grave):\n" +
                "\n" +
                "Utiliza insecticidas específicos para escarabajos, preferiblemente de origen orgánico (como piretrinas naturales o aceite de neem).\n" +
                "Aplica en horario vespertino y siempre siguiendo las instrucciones del fabricante.\n" +
                "Lava bien los segmentos con agua después de algunos días para evitar residuos.\n" +
                "Prevención:\n" +
                "\n" +
                "Mantén la planta fuerte con buena nutrición y riego adecuado.\n" +
                "Revisa regularmente para detectar daños tempranos.\n" +
                "Resumen\n" +
                "El escarabajo de las hojas puede causar daños visibles en tu cactus orquídea, pero el control manual y el uso de insecticidas suaves suelen ser efectivos. Si la plaga es recurrente, refuerza la vigilancia y considera mejorar las condiciones de cultivo para fortalecer la planta.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'Parece que quisiste decir “cochinillas”.\n" +
                "Las cochinillas son una plaga frecuente en muchas plantas, incluido el cactus orquídea (Epiphyllum). Son pequeños insectos que se adhieren a los tallos y hojas, alimentándose de la savia y debilitando la planta.\n" +
                "\n" +
                "¿Cómo reconocer cochinillas en el cactus orquídea?\n" +
                "Bultitos blancos algodonosos (cochinilla algodonosa) o marrones/planos (cochinilla acanalada o cochinilla de escudo) en tallos y zonas de unión.\n" +
                "Zonas pegajosas (“mielada”) en la planta o sustrato.\n" +
                "Hojas y tallos amarillentos, debilitados o deformados.\n" +
                "Presencia de hormigas (que “protegen” a las cochinillas por la mielada).','Eliminación manual:\n" +
                "\n" +
                "Usa un algodón o pincel embebido en alcohol (isopropílico o etílico) para limpiar las zonas afectadas.\n" +
                "Retira las cochinillas visibles con cuidado.\n" +
                "Aislar la planta:\n" +
                "\n" +
                "Separa la planta enferma de otras para evitar contagios.\n" +
                "Tratamiento orgánico:\n" +
                "\n" +
                "Pulveriza agua con jabón potásico o jabón neutro (unas gotas por litro de agua), deja actuar unos minutos y enjuaga bien.\n" +
                "Repite cada 7-10 días hasta erradicar la plaga.\n" +
                "Insecticidas específicos:\n" +
                "\n" +
                "Si la plaga es grave, usa un insecticida apto para epífitas y cochinillas (aceite de neem, insecticida sistémico suave).\n" +
                "Revisión de raíces:\n" +
                "\n" +
                "En infestaciones persistentes, revisa el sustrato y raíces, ya que pueden esconderse allí. Cambia el sustrato si es necesario.\n" +
                "Prevención\n" +
                "Mantén la planta ventilada y sin exceso de humedad.\n" +
                "Observa regularmente para detectar ataques tempranos.\n" +
                "Limpia las hojas y tallos periódicamente.',13)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (180,'Los pulgones (áfidos) son pequeños insectos que pueden atacar al cactus orquídea (Epiphyllum), aunque son menos frecuentes que cochinillas o ácaros. Sin embargo, cuando aparecen, pueden debilitar la planta y favorecer la aparición de hongos.\n" +
                "\n" +
                "¿Cómo reconocer pulgones en el cactus orquídea?\n" +
                "Pequeños insectos de color verde, negro, amarillo o marrón agrupados en brotes jóvenes, capullos florales o zonas tiernas.\n" +
                "Secreción pegajosa (“mielada”) sobre la planta y el sustrato.\n" +
                "Presencia de hormigas que se alimentan de la mielada.\n" +
                "Torsión, deformación o arrugamiento de segmentos jóvenes.\n" +
                "Capullos de flores que no abren o caen prematuramente.','Eliminación manual:\n" +
                "\n" +
                "Lava la planta con un chorro de agua suave para desalojar los pulgones.\n" +
                "Usa un pincel o algodón para retirarlos si la infestación es leve.\n" +
                "Tratamiento orgánico:\n" +
                "\n" +
                "Aplica agua jabonosa (jabón potásico o unas gotas de jabón neutro en 1 litro de agua) sobre los pulgones y enjuaga después de 15 minutos.\n" +
                "Repite cada 5-7 días hasta controlar la plaga.\n" +
                "Insecticidas naturales:\n" +
                "\n" +
                "Rocía con extracto de ajo, aceite de neem o infusión de tabaco (en casos leves a moderados).\n" +
                "Evita el aceite mineral en exceso, ya que puede dañar los segmentos tiernos de Epiphyllum.\n" +
                "Control biológico:\n" +
                "\n" +
                "Favorece la presencia de mariquitas (vaquitas), crisopas o sírfidos, que se alimentan de pulgones.\n" +
                "Insecticida químico (solo si la plaga es grave):\n" +
                "\n" +
                "Utiliza insecticidas específicos para pulgones y sigue las indicaciones del fabricante.\n" +
                "Aplica en horas frescas y nunca sobre flores abiertas.\n" +
                "Prevención\n" +
                "Mantén la planta ventilada y revisa regularmente brotes y capullos.\n" +
                "Evita el exceso de abono nitrogenado, que favorece el crecimiento de tejidos tiernos susceptibles a pulgones.\n" +
                "Controla las hormigas, ya que protegen y dispersan los pulgones.\n',42)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'La insuficiencia de luz es un problema común en cactus Mammillaria y otros cactus cultivados en interior o en lugares poco iluminados. Aquí te explico cómo identificarla, sus consecuencias y cómo corregirla:\n" +
                "\n" +
                "Síntomas de insuficiencia de luz en Mammillaria\n" +
                "Etiolación: El cactus crece alargado, delgado y débil, buscando la luz. Puede perder su forma globosa característica.\n" +
                "Color pálido: El verde se vuelve más claro o amarillento.\n" +
                "Espinas débiles o escasas: Las espinas se desarrollan cortas o muy finas.\n" +
                "Falta de floración: La planta no florece, incluso estando sana por lo demás.\n" +
                "Crecimiento lento o detenido.\n" +
                "Consecuencias\n" +
                "El cactus se debilita, es más susceptible a plagas y enfermedades.\n" +
                "Puede perder su forma estética y original.\n" +
                "En casos severos, puede morir si no se corrige.','Aumenta la luz gradualmente:\n" +
                "\n" +
                "Coloca la Mammillaria en un lugar donde reciba luz solar directa por la mañana o la tarde (evita el sol intenso de mediodía al principio).\n" +
                "Si está en interior, ubícala junto a una ventana orientada al sur o al este (en el hemisferio norte).\n" +
                "Puedes complementar con luz artificial (lámparas LED de espectro completo para plantas).\n" +
                "Evita cambios bruscos:\n" +
                "\n" +
                "Si la planta estuvo mucho tiempo en sombra, aumenta la exposición a la luz poco a poco para evitar quemaduras.\n" +
                "Gira la maceta regularmente:\n" +
                "\n" +
                "Así el cactus crece de forma pareja y no se inclina hacia la luz.\n" +
                "Prevención\n" +
                "Mantén un lugar muy luminoso, idealmente con varias horas de sol directo.\n" +
                "Revisa la luz a lo largo del año, ya que en otoño e invierno puede ser insuficiente.\n',35)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'En los cactus Mammillaria, una flor marchita es completamente normal después de la floración. Aquí tienes detalles para que sepas cuándo preocuparte y qué hacer:\n" +
                "\n" +
                "¿Por qué se marchita la flor en Mammillaria?\n" +
                "Ciclo natural: Las flores de Mammillaria suelen durar pocos días (a veces sólo 2-4), luego se cierran y marchitan.\n" +
                "Polinización: Tras la polinización (natural o manual), la flor se marchita y puede dar lugar a un fruto (pequeña baya).\n" +
                "Estrés ambiental: Si la flor se marchita prematuramente, puede deberse a calor excesivo, falta de agua, cambios bruscos de temperatura, o baja humedad.\n','Déjalas secar: Puedes dejar que la flor se seque completamente; si se formó un fruto, este será visible una vez que la flor se caiga.\n" +
                "Retíralas suavemente: Si deseas, puedes retirar las flores marchitas con las manos limpias o pinzas, evitando dañar la planta.\n" +
                "No riegues en exceso: No aumentes el riego sólo porque la flor se marchitó; sigue el ritmo habitual.\n" +
                "¿Cuándo preocuparse?\n" +
                "Si todas las flores se marchitan prematuramente cada temporada, revisa:\n" +
                "Luz: ¿Tiene suficiente luz solar?\n" +
                "Riego: ¿No hay exceso ni falta de agua?\n" +
                "Plagas: ¿No hay presencia de ácaros, pulgones o cochinillas?\n" +
                "Ambiente: ¿Hay cambios bruscos de temperatura o corrientes de aire?\n" +
                "Resumen:\n" +
                "La flor marchita en Mammillaria es normal tras la floración. Sólo preocúpate si las flores nunca llegan a abrirse, se marchitan demasiado rápido o hay otros síntomas en la planta. ¿Notas algún problema adicional o quieres saber cómo fomentar más floración?',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'Los cerambícidos (familia Cerambycidae), también conocidos como escarabajos longicornios, son una familia de escarabajos caracterizados por sus largas antenas, a menudo más largas que el cuerpo. Aquí tienes información relevante sobre ellos, especialmente en el contexto de cactus como Mammillaria, biznagas u otros géneros:\n" +
                "\n" +
                "Características principales de los cerambícidos\n" +
                "Antenas largas: Mucho más largas que el cuerpo en la mayoría de las especies.\n" +
                "Cuerpo alargado: Colores variados, desde marrón, negro, metálico o moteado.\n" +
                "Larvas xilófagas: Las larvas viven y se alimentan dentro de tallos o troncos (en plantas leñosas), causando galerías.\n" +
                "Cerambícidos y cactus\n" +
                "Aunque los cerambícidos atacan principalmente árboles y plantas leñosas, algunas especies pueden perforar y poner huevos en cactus grandes, especialmente en ambientes naturales.\n" +
                "Las larvas pueden excavar túneles en los tallos, debilitando la estructura, favoreciendo infecciones y pudriciones secundarias.\n" +
                "En cultivos domésticos, el ataque es poco frecuente, pero puede suceder en cactus grandes, viejos o debilitados.\n" +
                "Síntomas de ataque en cactus\n" +
                "Orificios con bordes secos en tallos o base de la planta.\n" +
                "Presencia de aserrín o restos secos en la superficie o en el sustrato (resultado de la actividad de las larvas).\n" +
                "Marchitez o debilitamiento inexplicable en segmentos alejados del orificio.\n" +
                "En casos avanzados, la planta puede colapsar por daño interno.','Inspección visual: Busca orificios y restos de aserrín en la base y costados del cactus.\n" +
                "Extracción manual: Si es posible, introduce un alambre delgado en el orificio para eliminar la larva.\n" +
                "Limpieza del orificio: Aplica canela en polvo o fungicida para evitar infecciones secundarias.\n" +
                "Prevención: Mantén las plantas sanas, evita heridas y revisa regularmente.\n" +
                "Control químico: El uso de insecticidas sistémicos puede ayudar en infestaciones graves, pero es poco común y sólo recomendado en casos extremos.\n" +
                "Resumen\n" +
                "Los cerambícidos pueden atacar cactus grandes, provocando daño interno severo. La mejor defensa es la prevención, revisando y manteniendo la planta sana. Si detectas daño, actúa rápido para eliminar la larva y proteger la planta de infecciones.',43)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'Las hormigas de fuego son una plaga potencialmente peligrosa para el cactus Mammillaria y otras plantas. Aquí tienes información relevante sobre ellas y cómo afectan a tu cactus:\n" +
                "\n" +
                "¿Qué son las hormigas de fuego?\n" +
                "Son hormigas del género Solenopsis (especialmente Solenopsis invicta), conocidas por su picadura dolorosa y comportamiento agresivo.\n" +
                "Son rojas o rojizas, de tamaño pequeño a mediano.\n" +
                "Forman grandes colonias y pueden invadir macetas y jardines.\n" +
                "¿Cómo afectan a los cactus Mammillaria?\n" +
                "No comen cactus directamente, pero pueden causar daño indirecto:\n" +
                "Protegen y “crían” plagas como pulgones y cochinillas, alimentándose de la “mielada” que éstas producen.\n" +
                "Remueven el sustrato y pueden exponer raíces, dañando la planta.\n" +
                "Su presencia puede dificultar el manejo y cuidado del cactus debido a sus picaduras.\n" +
                "Si la colonia está bajo la maceta, puede desestabilizar la planta y secar el sustrato rápidamente.','Ubica el nido:\n" +
                "\n" +
                "Busca si está en la maceta, bajo ella o cerca.\n" +
                "Cambia de maceta:\n" +
                "\n" +
                "Si la colonia está en la maceta, trasplanta el cactus a un sustrato nuevo y limpio, eliminando todas las hormigas y huevos posibles.\n" +
                "Trampas y cebos:\n" +
                "\n" +
                "Usa cebos comerciales para hormigas de fuego alrededor de la zona (nunca directamente sobre la planta).\n" +
                "Evita insecticidas líquidos sobre el cactus, pueden dañarlo.\n" +
                "Barrera física:\n" +
                "\n" +
                "Coloca la maceta en un plato con agua para evitar que suban las hormigas.\n" +
                "Control natural:\n" +
                "\n" +
                "Espolvorea tierra de diatomeas alrededor de la maceta (no sobre la planta).\n" +
                "Usa canela en polvo como barrera, aunque es menos efectiva para hormigas de fuego.\n" +
                "Prevención\n" +
                "Mantén el área limpia y libre de restos orgánicos.\n" +
                "Controla plagas de pulgones y cochinillas, ya que atraen a las hormigas.\n" +
                "Revisa periódicamente bajo y dentro de las macetas, especialmente en exterior.',44)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'En los cactus Mammillaria y otros similares, la fruta marchita (o fruto seco/marchito) es algo que puede ocurrir por diversas razones. Aquí tienes una guía para entender el proceso, saber cuándo es normal y cuándo puede indicar un problema:\n" +
                "\n" +
                "¿Por qué se marchita la fruta en Mammillaria?\n" +
                "Maduración o ciclo natural:\n" +
                "Después de la floración, si la polinización fue exitosa, Mammillaria produce una fruta (generalmente una baya alargada, roja, rosa o blanca). Cuando la fruta madura, seca y se marchita, es parte de su ciclo natural. En este estado, suele estar lista para liberar semillas.\n" +
                "\n" +
                "Falta de polinización:\n" +
                "Si la flor no fue polinizada correctamente, puede formarse una fruta pequeña y débil que se marchita antes de madurar.\n" +
                "\n" +
                "Estrés ambiental:\n" +
                "Exceso de calor, sequía, riegos irregulares o falta de nutrientes pueden hacer que el fruto se seque prematuramente.\n" +
                "\n" +
                "Plagas o enfermedades:\n" +
                "Cochinillas, pulgones o infecciones fúngicas pueden atacar el fruto, causando que se marchite o pudra.','Revisa si está madura:\n" +
                "Si la fruta está seca y fácil de retirar, probablemente ya cumplió su ciclo y puedes recolectar las semillas si te interesa.\n" +
                "Elimina frutas podridas o muy dañadas:\n" +
                "Usa pinzas limpias para retirarlas y evitar infecciones secundarias.\n" +
                "Observa el resto de la planta:\n" +
                "Si solo la fruta está marchita pero el cactus está sano, no hay problema.\n" +
                "Si hay manchas, blanduras o síntomas en la planta, revisa por plagas o exceso de humedad.\n" +
                "Prevención:\n" +
                "Mantén buena ventilación, riego adecuado y revisa periódicamente por plagas.\n" +
                "Resumen\n" +
                "La fruta marchita en Mammillaria es normal cuando el fruto madura o la flor no fue polinizada. Solo preocúpate si aparecen signos de pudrición, plagas o enfermedad en la planta principal.',32)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'El moho en la tierra (sustrato) donde tienes tu Mammillaria, o cualquier cactus, es un problema común, especialmente cuando hay exceso de humedad, poca ventilación, o materia orgánica en descomposición. Aquí tienes lo que debes saber y cómo actuar:\n" +
                "\n" +
                "¿Por qué aparece moho en la tierra de los cactus?\n" +
                "Exceso de riego o sustrato que retiene demasiada humedad.\n" +
                "Ventilación insuficiente (ambiente cerrado, poca circulación de aire).\n" +
                "Materia orgánica en la superficie (hojas secas, restos de flores/frutos, etc.).\n" +
                "Sombra constante o poca luz solar directa.\n" +
                "¿Es peligroso para la Mammillaria?\n" +
                "Un poco de moho superficial generalmente no daña al cactus adulto y sano, pero es señal de un problema ambiental.\n" +
                "Si el moho es abundante o persiste, puede favorecer la aparición de hongos patógenos o pudrición de raíces.','Retira el moho visible:\n" +
                "\n" +
                "Usa una cuchara o palillo para quitar la capa superficial de sustrato con moho.\n" +
                "Mejora la ventilación:\n" +
                "\n" +
                "Coloca la planta en un sitio más aireado.\n" +
                "Evita sitios cerrados o con humedad ambiental alta.\n" +
                "Reduce el riego:\n" +
                "\n" +
                "Riega solo cuando el sustrato esté completamente seco.\n" +
                "Asegúrate de que la maceta drene bien.\n" +
                "Aumenta la luz:\n" +
                "\n" +
                "Ubica la planta donde reciba más luz solar indirecta o directa (sin quemarla).\n" +
                "El sol ayuda a secar el sustrato y evita el moho.\n" +
                "Evita materia orgánica en la superficie:\n" +
                "\n" +
                "Retira hojas, flores o restos vegetales del sustrato.\n" +
                "Cambia el sustrato si es necesario:\n" +
                "\n" +
                "Si el moho reaparece constantemente, considera trasplantar con sustrato nuevo, seco y bien drenante.\n" +
                "Opcional: Espolvorea canela en polvo:\n" +
                "\n" +
                "La canela es un fungicida natural y ayuda a prevenir hongos superficiales.\n" +
                "Prevención\n" +
                "Revisa humedad antes de regar.\n" +
                "Usa sustrato específico para cactus, con buen drenaje.\n" +
                "Mantén la planta en un lugar luminoso y ventilado.\n',45)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'La pudrición del tallo es uno de los problemas más graves para cactus como Mammillaria, biznagas y otros. Aquí tienes una guía clara para identificar, tratar y prevenir este problema:\n" +
                "\n" +
                "¿Cómo reconocer la pudrición del tallo?\n" +
                "Cambio de color: El tallo se vuelve marrón oscuro, negro o amarillento.\n" +
                "Textura blanda: Al tocar, la zona afectada está blanda, hundida o con líquido.\n" +
                "Mal olor: Puede haber olor desagradable, típico de tejido descompuesto.\n" +
                "Separación de segmentos: En casos avanzados, la planta se colapsa o se separa por la base.\n" +
                "Manchas acuosas: A veces aparecen manchas translúcidas o acuosas antes de que el tejido se ponga negro.\n" +
                "Causas frecuentes\n" +
                "Exceso de riego o sustrato que retiene mucha humedad.\n" +
                "Falta de drenaje en la maceta.\n" +
                "Heridas en el tallo, que son puerta de entrada para hongos o bacterias.\n" +
                "Temperaturas frías y humedad alta.\n','Aísla la planta:\n" +
                "Separa la planta afectada de otras para evitar contagios.\n" +
                "\n" +
                "Corta la parte podrida:\n" +
                "\n" +
                "Usa una navaja/cuchillo bien afilado y desinfectado.\n" +
                "Retira TODO el tejido blando, oscuro o húmedo, hasta llegar a tejido sano (verde y firme).\n" +
                "Si la base está completamente podrida, intenta salvar solo la parte superior sana.\n" +
                "Desinfecta la herida:\n" +
                "\n" +
                "Deja secar la herida al aire, en sombra, durante varios días hasta que forme costra.\n" +
                "Espolvorea canela en polvo o fungicida sobre la herida para prevenir infecciones.\n" +
                "Enraiza de nuevo (si fue necesario cortar):\n" +
                "\n" +
                "Coloca el segmento sano sobre sustrato seco y bien drenante, sin regar hasta que salgan raíces (puede tardar semanas).\n" +
                "Descarta tejido irreparable:\n" +
                "\n" +
                "Si todo el tallo está blando y oscuro, lamentablemente la planta no se puede recuperar.\n" +
                "Prevención\n" +
                "Usa sustrato específico para cactus, muy drenante.\n" +
                "Riega solo cuando el sustrato esté completamente seco.\n" +
                "Asegura que la maceta tenga agujero de drenaje.\n" +
                "Evita mojar el tallo al regar.\n" +
                "Limpia las herramientas antes de usarlas.',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'Las cicatrices en cactus Mammillaria (y en otros cactus) son marcas o áreas endurecidas que quedan después de una lesión, enfermedad, ataque de plagas o incluso por daños ambientales. Aquí tienes una guía para entenderlas y saber cuándo preocuparte:\n" +
                "\n" +
                "¿Qué son las cicatrices en los cactus?\n" +
                "Son zonas de color marrón, gris, blanquecino o corcho duro en la superficie del cactus.\n" +
                "Se forman cuando el tejido se recupera tras una herida (por espinas caídas, corte, ataque de plaga, quemadura solar, o incluso por crecimiento natural).\n" +
                "Pueden ser superficiales o profundas, dependiendo de la causa y gravedad del daño.\n" +
                "Causas comunes de cicatrices en Mammillaria\n" +
                "Plagas:\n" +
                "Ataques previos de cochinillas, ácaros o pulgones.\n" +
                "Heridas físicas:\n" +
                "Golpes, cortes accidentales, espinas caídas.\n" +
                "Quemaduras solares:\n" +
                "Exposición repentina al sol intenso.\n" +
                "Cicatrización por pudrición:\n" +
                "Recuperación tras eliminar tejido podrido o enfermo.\n" +
                "Envejecimiento natural:\n" +
                "Algunas especies desarrollan un “corcho” natural en la base al envejecer.\n" +
                "¿Son peligrosas las cicatrices?\n" +
                "Si la zona está dura, seca y no aumenta de tamaño, la cicatriz es simplemente una señal de daño pasado y no afecta la salud del cactus.\n" +
                "Si está blanda, húmeda, oscura o sigue extendiéndose, puede indicar infección activa o pudrición (esto sí es grave).','No intentes quitarlas: No se pueden eliminar y son parte del tejido de la planta.\n" +
                "Observa: Si la cicatriz permanece igual y la planta sigue creciendo, no hay problema.\n" +
                "Vigila síntomas de infección: Si ves zonas blandas, manchas negras, mal olor o que la cicatriz crece, actúa rápido (puede ser pudrición o infección fúngica/bacteriana).\n" +
                "Prevención: Evita heridas, cambios bruscos de luz, exceso de agua y controla plagas.\n" +
                "Resumen\n" +
                "Las cicatrices en Mammillaria suelen ser inofensivas si son secas y duras. Solo preocúpate si hay signos de pudrición o infección. Son parte de la historia de la planta y no afectan la floración ni el crecimiento si el resto del cactus está sano.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'Las malformaciones de la fruta en cactus Mammillaria y otros cactus pueden deberse a varias causas. Aquí tienes una guía para identificar posibles motivos y soluciones:\n" +
                "\n" +
                "Causas comunes de malformaciones en la fruta de Mammillaria\n" +
                "Polinización incompleta o deficiente\n" +
                "\n" +
                "Si la flor no fue polinizada correctamente (por falta de insectos, viento, o polinización manual deficiente), la fruta puede formarse de manera irregular, pequeña o torcida.\n" +
                "Daño por plagas\n" +
                "\n" +
                "Insectos como trips, ácaros, pulgones o cochinillas pueden atacar la flor o el fruto en desarrollo, causando deformidades, manchas, abolladuras o crecimiento anómalo.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Hongos y bacterias pueden infectar la flor o el fruto joven, generando malformaciones, manchas oscuras, podredumbre o incluso abortos del fruto.\n" +
                "Condiciones ambientales adversas\n" +
                "\n" +
                "Exceso o falta de agua, calor extremo, frío repentino, humedad muy baja o muy alta pueden afectar el desarrollo normal del fruto.\n" +
                "Cambios bruscos de temperatura durante la floración o fructificación también pueden provocar deformidades.\n" +
                "Daño físico\n" +
                "\n" +
                "Golpes, manipulación brusca o contacto con objetos pueden deformar los frutos en desarrollo.\n" +
                "Deficiencia de nutrientes\n" +
                "\n" +
                "Falta de calcio, potasio u otros micronutrientes puede causar frutos mal formados o poco desarrollados.','Observa si el problema es general o puntual:\n" +
                "\n" +
                "Si solo uno o dos frutos están deformes, puede ser un incidente aislado.\n" +
                "Si es recurrente, revisa los factores anteriores.\n" +
                "Revisa por plagas:\n" +
                "\n" +
                "Examina flores y frutos jóvenes con lupa en busca de pequeños insectos o daños.\n" +
                "Aplica insecticida específico para cactus si detectas plaga.\n" +
                "Mejora la polinización:\n" +
                "\n" +
                "Si cultivas en interior, puedes probar polinización manual usando un pincel suave.\n" +
                "Mantén condiciones ambientales estables:\n" +
                "\n" +
                "Evita cambios bruscos de temperatura y riega solo cuando el sustrato esté seco.\n" +
                "Fertiliza correctamente:\n" +
                "\n" +
                "Usa fertilizante para cactus durante la temporada de crecimiento, siguiendo las dosis recomendadas.\n" +
                "Retira frutos deformes o enfermos:\n" +
                "\n" +
                "Para evitar la propagación de hongos o bacterias, retira de inmediato cualquier fruto con manchas negras, blanduras o mal olor.\n" +
                "Resumen\n" +
                "Las malformaciones en la fruta de Mammillaria suelen deberse a problemas de polinización, plagas, enfermedades o estrés ambiental. Si el cactus está sano y las malformaciones son ocasionales, no es grave. Si son frecuentes o graves, revisa por plagas, enfermedades y mejora el manejo de la planta.',46)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (190,'El tizón de fuego (“fire blight” en inglés) es una enfermedad bacteriana grave causada por la bacteria Erwinia amylovora. Afecta principalmente a plantas de la familia Rosaceae (manzanos, perales, membrilleros, etc.), pero NO es típica ni común en cactus Mammillaria ni en cactáceas en general.\n" +
                "\n" +
                "Sin embargo, aquí tienes información relevante si te refieres a:\n" +
                "\n" +
                "La enfermedad “tizón de fuego” en plantas de jardín/orchard,\n" +
                "O si observas síntomas similares en tu cactus y te preocupa una posible infección.\n" +
                "Tizón de fuego en plantas (Rosaceae):\n" +
                "Síntomas:\n" +
                "\n" +
                "Marchitez y ennegrecimiento rápido de flores, hojas y brotes jóvenes, como si hubieran sido quemados por fuego.\n" +
                "Exudado bacteriano viscoso y blanquecino.\n" +
                "Muerte descendente de ramas.','Eliminar y quemar ramas afectadas (desinfectando las herramientas entre cortes).\n" +
                "Evitar el riego por aspersión.\n" +
                "Aplicar productos bactericidas autorizados preventivamente.\n" +
                "Plantar variedades resistentes si es posible.\n" +
                "¿Puede el tizón de fuego afectar a Mammillaria o cactus?\n" +
                "- No. Los cactus no son hospedantes de Erwinia amylovora.\n" +
                "\n" +
                "Si observas síntomas de “pudrición negra”, manchas marrones, necrosis rápida o exudados en tu Mammillaria, probablemente se deba a hongos, bacterias oportunistas (como Erwinia spp. no amylovora, Pseudomonas, etc.) o exceso de humedad, pero no al tizón de fuego clásico.\n" +
                "¿Qué hacer si tu cactus muestra síntomas de necrosis o “quemado”?\n" +
                "Aísla la planta.\n" +
                "Corta el tejido afectado con herramienta limpia y desinfectada.\n" +
                "Deja secar la herida al aire y aplica canela en polvo o fungicida.\n" +
                "Corrige riego y ventilación: Reduce humedad y mejora la circulación de aire.\n" +
                "Evita mojar el tallo al regar.',47)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'Las manchas marrones en la Calathea majestica (White Star) son uno de los problemas más comunes. Aquí te explico las causas más frecuentes, cómo diferenciarlas y qué puedes hacer:\n" +
                "\n" +
                "Causas comunes de manchas marrones en Calathea majestica\n" +
                "Falta de humedad ambiental\n" +
                "\n" +
                "La causa más frecuente. Calathea necesita alta humedad (ideal: 60-80%). El aire seco provoca puntas y bordes marrones en las hojas.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Exceso de riego: Puede ocasionar manchas marrones irregulares, a veces con borde amarillo, y hojas blandas.\n" +
                "Falta de riego: Las hojas se secan desde las puntas o los bordes.\n" +
                "Uso de agua con cloro/cal\n" +
                "\n" +
                "El agua del grifo sin reposar puede causar manchas marrones o puntas quemadas por sensibilidad a químicos.\n" +
                "Luz directa o quemaduras solares\n" +
                "\n" +
                "Exposición al sol directo puede dejar manchas marrones secas o áreas blanqueadas con centro marrón.\n" +
                "Plagas (menos frecuente)\n" +
                "\n" +
                "Trips, ácaros o cochinillas pueden dejar pequeñas manchas marrones o puntos secos.\n" +
                "Fertilización excesiva\n" +
                "\n" +
                "Demasiado fertilizante puede “quemar” las raíces, reflejándose en manchas marrones en hojas.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Manchas marrones con halo amarillo o aspecto húmedo pueden indicar hongos o bacterias; menos común en interior, pero posible si hay exceso de humedad y poca ventilación.','Aumenta la humedad ambiental\n" +
                "\n" +
                "Usa humidificador, agrupa plantas, o coloca la maceta sobre una bandeja con piedras y agua (sin que toque el fondo de la maceta).\n" +
                "Revisa el riego\n" +
                "\n" +
                "Riega solo cuando la primera pulgada del sustrato esté seca. Usa agua filtrada o reposada.\n" +
                "Evita el sol directo\n" +
                "\n" +
                "Coloca la planta en luz indirecta brillante, nunca en ventana con sol directo.\n" +
                "Revisa por plagas\n" +
                "\n" +
                "Examina el envés de las hojas y los tallos; si ves insectos, trata con jabón potásico o insecticida específico para plantas de interior.\n" +
                "Recorta hojas afectadas\n" +
                "\n" +
                "Si hay hojas muy dañadas, recorta solo la parte marrón o la hoja entera si está muy afectada.\n" +
                "No fertilices en exceso\n" +
                "\n" +
                "Usa fertilizante cada 4-6 semanas en primavera-verano, diluido a la mitad de la dosis recomendada.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'La baja humedad ambiental es uno de los problemas más frecuentes para la Calathea majestica y otras calatheas. Estas plantas son nativas de selvas tropicales donde la humedad suele estar por encima del 60%–70%. Cuando la humedad es baja, suelen aparecer varios síntomas en la planta:\n" +
                "\n" +
                "Síntomas de baja humedad en Calathea majestica\n" +
                "Puntas marrones o secas en las hojas.\n" +
                "Bordes de las hojas secos o enrollados.\n" +
                "Hojas con aspecto “crujiente” o quebradizo.\n" +
                "Crecimiento lento o nuevas hojas deformes.\n" +
                "Las hojas pueden perder color y vitalidad.','Humidificador:\n" +
                "Es la forma más efectiva y controlada. Mantén el humidificador cerca de la planta.\n" +
                "\n" +
                "Agrupa plantas:\n" +
                "Junta varias plantas para crear un microclima más húmedo.\n" +
                "\n" +
                "Bandeja con piedras y agua:\n" +
                "Coloca la maceta sobre una bandeja con piedras y un poco de agua (sin que el fondo de la maceta toque el agua directamente).\n" +
                "\n" +
                "Rocía (con precaución):\n" +
                "Puedes rociar ligeramente las hojas con agua filtrada, aunque esto no aumenta mucho la humedad y puede favorecer hongos si no hay ventilación.\n" +
                "\n" +
                "Evita corrientes de aire y calefacción:\n" +
                "Radiadores, aires acondicionados y corrientes de aire resecan mucho el ambiente.\n" +
                "\n" +
                "Usa agua adecuada:\n" +
                "Riega con agua filtrada, destilada o de lluvia, ya que el agua del grifo puede contener sales que empeoran los síntomas.\n" +
                "\n" +
                "Consejos adicionales\n" +
                "Revisa la humedad con un higrómetro (puedes encontrar modelos económicos).\n" +
                "Si la humedad en tu casa baja mucho en invierno o verano, el humidificador es casi imprescindible para calatheas.\n" +
                "Recorta con tijeras limpias las puntas secas para mejorar el aspecto, pero el daño no es reversible.',48)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'El envejecimiento en la Calathea majestica (y en otras calatheas) es un proceso natural que puede causar ciertos cambios visibles en la planta. Aquí tienes lo más relevante para identificar y diferenciar el envejecimiento normal de posibles problemas:\n" +
                "\n" +
                "¿Cómo se ve el envejecimiento natural en Calathea majestica?\n" +
                "Hojas viejas amarillas o marrones:\n" +
                "Las hojas más bajas o antiguas se tornan amarillas, marrones o pierden color y firmeza, secándose poco a poco desde la punta hacia el pecíolo.\n" +
                "Desprendimiento de hojas:\n" +
                "Es normal que, con el tiempo, la calathea pierda hojas viejas de la parte inferior, mientras sigue sacando nuevas hojas del centro.\n" +
                "Hojas “sin brillo” o con color deslucido:\n" +
                "El color de las hojas más viejas puede desvanecerse y las franjas blancas o rosadas, volverse menos intensas.\n" +
                "¿Cuándo preocuparse?\n" +
                "Si muchas hojas (no solo las más viejas) amarillean o se secan al mismo tiempo, puede ser síntoma de riego inadecuado, baja humedad, plagas o enfermedades.\n" +
                "Si las hojas jóvenes también muestran problemas, no es envejecimiento: revisa luz, riego, humedad, plagas y raíces.\n" +
                "El envejecimiento natural suele afectar solo una o pocas hojas a la vez y la planta continúa produciendo hojas nuevas y sanas.','Córtalas con tijeras limpias lo más cerca posible de la base, para que la planta dirija su energía a las hojas nuevas.\n" +
                "No te preocupes si ocasionalmente ves hojas viejas deteriorándose: es parte del ciclo de vida.\n" +
                "Aprovecha para revisar la planta por si hay síntomas de otros problemas.\n" +
                "Resumen\n" +
                "El envejecimiento es un proceso normal en la Calathea majestica. Solo debes preocuparte si la pérdida de hojas es generalizada o afecta hojas jóvenes. Mantén buenos cuidados (humedad, riego, luz indirecta) y retira hojas viejas para estimular el crecimiento.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'El marchitamiento de la punta de las hojas en la Calathea majestica es un problema muy común y suele indicar que la planta está reaccionando a factores ambientales o de riego. Aquí tienes las causas más probables y cómo solucionarlas:\n" +
                "\n" +
                "Causas frecuentes del marchitamiento en las puntas de las hojas\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Es la causa más común. Calathea majestica necesita humedad alta (idealmente 60-80%). El aire seco hace que las puntas se sequen, se tornen marrones y se marchiten.\n" +
                "Calidad del agua\n" +
                "\n" +
                "El agua del grifo con cloro, sales o cal puede “quemar” las puntas. Prefiere agua filtrada, destilada o de lluvia.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Riego insuficiente: la planta sufre deshidratación y las puntas se secan.\n" +
                "Riego excesivo: puede dañar las raíces y reflejarse en puntas marrones o secas.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "Demasiado abono o fertilizante muy concentrado puede quemar las puntas.\n" +
                "Corrientes de aire/frío\n" +
                "\n" +
                "Las calatheas son sensibles a corrientes de aire, cambios bruscos de temperatura o frío, lo que puede dañar las puntas.\n" +
                "Luz directa\n" +
                "\n" +
                "La exposición al sol directo puede “quemar” las puntas y bordes.','Aumenta la humedad ambiental\n" +
                "\n" +
                "Usa humidificador, agrupa plantas, coloca la maceta sobre una bandeja con piedras y agua (sin que toque el fondo de la maceta).\n" +
                "Riega con agua adecuada\n" +
                "\n" +
                "Utiliza agua filtrada, destilada o de lluvia. Deja reposar el agua del grifo al menos 24 horas si no tienes otra opción.\n" +
                "Revisa el riego\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, nunca encharcado. Deja secar la capa superficial antes de volver a regar.\n" +
                "Evita fertilizar en exceso\n" +
                "\n" +
                "Fertiliza solo en primavera-verano, usando la mitad de la dosis recomendada.\n" +
                "Ubica la planta en lugar adecuado\n" +
                "\n" +
                "Luz indirecta brillante, alejada de sol directo y corrientes de aire.\n" +
                "Recorta las puntas secas\n" +
                "\n" +
                "Usa tijeras limpias y corta solo la parte seca, siguiendo la forma natural de la hoja.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'Las cicatrices en Calathea majestica suelen aparecer como marcas marrones, líneas o áreas endurecidas donde la hoja ha sufrido algún daño y luego se ha recuperado. Estas cicatrices pueden deberse a varias causas y, en la mayoría de los casos, no representan un peligro grave para la planta si el daño no sigue avanzando.\n" +
                "\n" +
                "Causas comunes de cicatrices en Calathea majestica\n" +
                "Daño físico:\n" +
                "Golpes, cortes accidentales, rozaduras al mover la planta o manipular las hojas.\n" +
                "Plagas:\n" +
                "Lesiones por trips, ácaros u otros insectos que después cicatrizan cuando la plaga se elimina.\n" +
                "Quemaduras solares:\n" +
                "Exposición accidental al sol directo puede dejar marcas blanquecinas o marrones que luego se endurecen.\n" +
                "Heridas por baja humedad:\n" +
                "Bordes secos por deshidratación que, tras corregir la humedad, dejan una línea marrón o corchosa.\n" +
                "Cicatrización por enfermedades:\n" +
                "Después de un hongo o bacteria, la hoja puede formar una costra o zona seca donde antes había una mancha activa.','No se pueden eliminar: Las cicatrices son permanentes en las hojas afectadas.\n" +
                "Recorta si es necesario: Si la zona cicatrizada es grande o poco estética, puedes recortar solamente la parte dañada o, si la hoja está muy fea, cortarla entera con tijeras limpias.\n" +
                "Observa: Si la cicatriz no crece y la hoja sigue verde y firme, no es necesario hacer nada. Si la cicatriz se extiende, hay humedad, olor o zonas blandas, podría ser una infección activa y habría que actuar.\n" +
                "Prevención: Mantén humedad alta, evita el sol directo, manipula la planta con cuidado y controla plagas.\n" +
                "Resumen\n" +
                "Las cicatrices en Calathea majestica indican daño pasado, pero si la hoja sigue sana y la cicatriz no avanza, no es motivo de alarma. Solo debes preocuparte si notas expansión, zonas húmedas o blandas, lo que puede indicar un problema activo.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'La pudrición de la hoja en Calathea majestica es un problema relativamente común y suele estar relacionada con exceso de humedad, problemas de riego o infecciones fúngicas/bacterianas. Aquí tienes una guía para identificar, tratar y prevenir este problema:\n" +
                "\n" +
                "¿Cómo reconocer la pudrición de la hoja?\n" +
                "Manchas marrones oscuras o negras que se extienden rápidamente.\n" +
                "Zonas blandas, húmedas o con mal olor en la hoja.\n" +
                "Hojas que se vuelven translúcidas o muestran un aspecto aguado.\n" +
                "A veces la pudrición inicia en el borde o la base de la hoja y avanza hacia el centro.\n" +
                "Causas frecuentes\n" +
                "Exceso de riego\n" +
                "Mantener el sustrato siempre mojado o encharcado provoca que las raíces y hojas se pudran.\n" +
                "Falta de drenaje\n" +
                "Maceta sin orificios o sustrato muy compacto retiene demasiada agua.\n" +
                "Humedad ambiental muy alta sin ventilación\n" +
                "Puede favorecer la aparición de hongos.\n" +
                "Riego sobre las hojas\n" +
                "Dejar agua estancada en las hojas o el centro de la planta puede causar pudrición.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "Ocurren especialmente si la planta ya está debilitada.','Aísla la planta\n" +
                "\n" +
                "Separa de otras plantas para evitar contagios.\n" +
                "Retira hojas afectadas\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas para cortar todo el tejido blando, negro o con mal olor.\n" +
                "Desinfecta las tijeras entre cada corte.\n" +
                "Mejora el drenaje y reduce el riego\n" +
                "\n" +
                "Asegúrate de que la maceta tenga orificios y usa sustrato suelto, aireado.\n" +
                "Deja secar la capa superior del sustrato antes de volver a regar.\n" +
                "Ventila el ambiente\n" +
                "\n" +
                "Mantén buena circulación de aire, sin corrientes frías.\n" +
                "Aplica fungicida\n" +
                "\n" +
                "Si la pudrición fue extensa, puedes rociar un fungicida suave apto para plantas de interior.\n" +
                "Evita mojar las hojas\n" +
                "\n" +
                "Riega directamente el sustrato, nunca sobre las hojas.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'El punto negro en las hojas de Calathea majestica generalmente es señal de algún tipo de problema localizado, y puede tener varias causas. Aquí tienes una guía para identificar y actuar según el origen más común:\n" +
                "\n" +
                "Causas frecuentes de puntos negros en Calathea majestica\n" +
                "Plagas\n" +
                "\n" +
                "Trips, ácaros o cochinillas pueden dejar pequeños puntos negros (excrementos o lesiones).\n" +
                "Revisa el envés de las hojas y los tallos con lupa; si ves insectos o puntos móviles, probablemente es plaga.\n" +
                "Manchas fúngicas\n" +
                "\n" +
                "Algunos hongos producen manchas o puntos negros, a veces con un halo amarillento alrededor.\n" +
                "Suele aparecer si hay exceso de humedad, mala ventilación, o agua sobre las hojas.\n" +
                "Daños mecánicos\n" +
                "\n" +
                "Un golpe, roce o pinchazo puede dejar un punto negro seco y duro, que no se expande.\n" +
                "Quemaduras o necrosis\n" +
                "\n" +
                "A veces, una gota de agua bajo luz intensa actúa como lupa y quema un pequeño punto negro en la hoja.','Revisa por plagas:\n" +
                "\n" +
                "Examina la hoja (especialmente el envés) y los tallos. Si encuentras plaga, limpia la hoja, y aplica jabón potásico o insecticida para plantas de interior.\n" +
                "Mejora ventilación y evita mojar las hojas:\n" +
                "\n" +
                "Si crees que es un hongo, corta la hoja afectada, mejora la circulación de aire y riega solo el sustrato.\n" +
                "Vigila el avance:\n" +
                "\n" +
                "Si es solo un punto y no crece, probablemente fue daño mecánico o quemadura.\n" +
                "Si aparecen más puntos, puede ser plaga o enfermedad, y deberías tratar toda la planta.\n" +
                "No te alarmes por un solo punto:\n" +
                "\n" +
                "Si la planta está sana y solo hay un punto negro, obsérvala unos días. Si se multiplica, actúa según lo anterior.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'El oídio es una enfermedad fúngica bastante común en plantas ornamentales, incluidas las calatheas (aunque es más frecuente en otras especies). Se reconoce fácilmente y puede tratarse si se detecta a tiempo.\n" +
                "\n" +
                "¿Qué es el oídio?\n" +
                "El oídio es causado por varios hongos (géneros como Erysiphe, Oidium, etc.) que producen una especie de “polvo” o capa blanquecina sobre las hojas, tallos y a veces flores. Es más común en ambientes con poca ventilación, humedad alta, y temperaturas moderadas.\n" +
                "\n" +
                "Síntomas de oídio en Calathea majestica\n" +
                "Manchas blancas o grisáceas, de aspecto harinoso, en la superficie de las hojas.\n" +
                "Las hojas pueden amarillear, deformarse o secarse si la infección avanza.\n" +
                "En casos severos, la planta puede debilitarse y perder hojas.\n" +
                "¿Por qué aparece el oídio?\n" +
                "Alta humedad ambiental combinada con poca ventilación.\n" +
                "Hojas mojadas durante mucho tiempo.\n" +
                "Temperaturas templadas (15-25 °C).\n','Retira las hojas muy afectadas\n" +
                "\n" +
                "Usa tijeras limpias y corta las hojas que tengan mucha presencia de oídio.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Coloca la planta en un sitio con buena circulación de aire, pero sin corrientes frías.\n" +
                "Evita mojar las hojas al regar\n" +
                "\n" +
                "Riega solo el sustrato.\n" +
                "Reduce la humedad si es posible, sin bajar de 50% (¡calatheas siguen necesitando humedad ambiental!).\n" +
                "\n" +
                "Tratamiento casero:\n" +
                "\n" +
                "Puedes pulverizar las hojas con una mezcla de agua y bicarbonato de sodio (1 g por litro de agua) o leche diluida (10 partes de agua por 1 de leche). Hazlo cada 7 días, siempre en horas de poca luz y prueba solo en una hoja primero.\n" +
                "También puedes usar fungicidas específicos para oídio, aptos para plantas de interior (consulta la etiqueta).\n" +
                "Limpia las hojas\n" +
                "\n" +
                "Puedes retirar el polvo blanco con un paño húmedo antes de aplicar cualquier tratamiento.\n" +
                "Prevención\n" +
                "Mantén buena ventilación.\n" +
                "Evita mojar las hojas y no amontones las plantas.\n" +
                "Revisa periódicamente para detectar los primeros síntomas.',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'La clorosis venal en Calathea majestica, o en cualquier planta, es una condición donde las hojas muestran un color amarillo entre las venas, mientras que las venas permanecen verdes. Es un síntoma claro de que algo no va bien y puede tener varias causas. Aquí te explico cómo identificarla, las causas más comunes y qué puedes hacer:\n" +
                "\n" +
                "¿Cómo identificar la clorosis venal?\n" +
                "Las hojas presentan un amarillamiento generalizado, pero las venas permanecen verdes.\n" +
                "Puede afectar hojas jóvenes o viejas, dependiendo de la causa.\n" +
                "Las hojas pueden perder vigor, volverse más delgadas o deformarse.\n" +
                "Principales causas de clorosis venal en Calathea majestica\n" +
                "Deficiencia de micronutrientes\n" +
                "\n" +
                "Hierro: La causa más común; suele aparecer en hojas jóvenes.\n" +
                "Magnesio o manganeso: Puede afectar hojas más viejas.\n" +
                "Suele deberse a sustrato agotado, pH alto o riego con agua dura (mucha cal).\n" +
                "pH del sustrato inadecuado\n" +
                "\n" +
                "Un sustrato demasiado alcalino bloquea la absorción de hierro y otros micronutrientes.\n" +
                "Riego con agua calcárea\n" +
                "\n" +
                "El agua del grifo muy dura (con mucha cal) eleva el pH del sustrato y favorece la clorosis.\n" +
                "Exceso de riego\n" +
                "\n" +
                "Raíces asfixiadas no absorben bien nutrientes, causando clorosis.\n" +
                "Enfermedades o plagas\n" +
                "\n" +
                "Raíces dañadas por hongos o nematodos pueden también causar clorosis, aunque es menos común.','Revisa y ajusta el riego\n" +
                "\n" +
                "No encharques el sustrato; deja secar la capa superior antes de volver a regar.\n" +
                "Mejora la calidad del agua\n" +
                "\n" +
                "Usa agua filtrada, destilada o de lluvia para evitar acumulación de sales y cal.\n" +
                "Aplica quelato de hierro\n" +
                "\n" +
                "Si el amarilleo sigue, usa un abono foliar o riego con quelato de hierro, siguiendo las instrucciones del producto.\n" +
                "Fertiliza adecuadamente\n" +
                "\n" +
                "Aporta un fertilizante equilibrado para plantas de interior, preferiblemente uno que incluya micronutrientes (Fe, Mg, Mn, Zn).\n" +
                "Renueva o mejora el sustrato\n" +
                "\n" +
                "Si el sustrato está agotado o apelmazado, trasplanta tu calathea usando tierra nueva, aireada y ligeramente ácida.\n" +
                "Resumen\n" +
                "La clorosis venal en Calathea majestica casi siempre se debe a un problema de nutrientes (especialmente hierro) o agua inadecuada. Corrigiendo el riego, la calidad del agua y aportando hierro suele resolverse. Si puedes, sube una foto o describe si afecta hojas jóvenes o viejas para un diagnóstico aún más exacto.',51)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (200,'Los insectos chupadores de savia son una de las plagas más comunes que pueden afectar a la Calathea majestica y otras plantas de interior. Estos insectos se alimentan perforando los tejidos de la planta y succionando los líquidos internos, lo que debilita la planta y puede causar diversos síntomas.\n" +
                "\n" +
                "Principales insectos chupadores de savia en Calathea majestica\n" +
                "Pulgones\n" +
                "\n" +
                "Pequeños, de cuerpo blando, suelen agruparse en brotes y el envés de las hojas.\n" +
                "Excretan una sustancia pegajosa llamada melaza.\n" +
                "Cochinillas\n" +
                "\n" +
                "Pueden ser algodonosas (blancas y esponjosas) o de caparazón marrón.\n" +
                "Se adhieren a tallos y nervaduras.\n" +
                "Araña roja (ácaros)\n" +
                "\n" +
                "Muy pequeños, casi invisibles; dejan telarañas finas en el envés de la hoja.\n" +
                "Provocan manchas amarillas, puntitos y debilitamiento.\n" +
                "Trips\n" +
                "\n" +
                "Insectos alargados y muy pequeños, suelen moverse rápido.\n" +
                "Dejan manchas plateadas o marrones, y puntos negros (excrementos).\n" +
                "Mosca blanca\n" +
                "\n" +
                "Pequeños insectos blancos que vuelan al mover la planta.\n" +
                "Se alimentan en el envés de la hoja.\n" +
                "Síntomas de infestación\n" +
                "Hojas con manchas amarillas, plateadas o decoloradas.\n" +
                "Bordes enrollados o secos.\n" +
                "Puntos negros (excrementos), melaza pegajosa o presencia de hongos negros (fumagina).\n" +
                "Hojas deformes o crecimiento lento.\n" +
                "Presencia visible de insectos en tallos y hojas.\n','Aisla la planta\n" +
                "\n" +
                "Para evitar el contagio a otras plantas.\n" +
                "Lava las hojas\n" +
                "\n" +
                "Usa agua tibia y un poco de jabón neutro. No mojes en exceso el sustrato.\n" +
                "Elimina manualmente\n" +
                "\n" +
                "Usa un paño húmedo o bastoncillo con alcohol para quitar cochinillas y pulgones visibles.\n" +
                "Aplica tratamiento\n" +
                "\n" +
                "Jabón potásico: Pulveriza toda la planta, especialmente el envés de las hojas, cada 4-7 días por 2-3 semanas.\n" +
                "Aceite de neem: Es efectivo contra pulgón, cochinilla y trips. Aplica según instrucciones.\n" +
                "Insecticida específico: Si la plaga es muy fuerte, usa productos aptos para interior y sigue las instrucciones.\n" +
                "Mejora el ambiente\n" +
                "\n" +
                "Ventila bien y evita el exceso de sequedad, ya que los ácaros prefieren ambientes secos.\n" +
                "Revisa regularmente\n" +
                "\n" +
                "Inspecciona cada semana para detectar reinfestaciones.\n" +
                "Prevención\n" +
                "Mantén la planta limpia y revisa las hojas (especialmente el envés) periódicamente.\n" +
                "Aumenta humedad, pero con buena ventilación.\n" +
                "Separa plantas nuevas o sospechosas antes de juntarlas con las demás.',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'Copilot said: Las **manchas marrones** en las hojas de\n" +
                "Las manchas marrones en las hojas de la Calathea bella pueden tener varias causas, y es importante observar el aspecto de las manchas y el estado general de la planta para un diagnóstico preciso. Aquí tienes las causas más frecuentes y cómo actuar:\n" +
                "\n" +
                "Causas comunes de manchas marrones en Calathea bella\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Las calatheas son muy sensibles al aire seco. La baja humedad provoca puntas y bordes marrones, que pueden evolucionar a manchas irregulares en la hoja.\n" +
                "Solución: Usa humidificador, agrupa plantas, coloca una bandeja con piedras y agua bajo la maceta.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Falta de agua: manchas secas, marrón claro, desde el borde hacia el centro.\n" +
                "Exceso de agua: manchas marrón oscuro, a veces con halo amarillo, que pueden ser blandas.\n" +
                "Solución: Mantén el sustrato ligeramente húmedo, no encharcado. Usa agua filtrada o reposada.\n" +
                "Calidad del agua\n" +
                "\n" +
                "El agua con cloro, cal o sales puede quemar las hojas y causar manchas marrones.\n" +
                "Solución: Riega con agua filtrada, destilada o de lluvia.\n" +
                "Plagas\n" +
                "\n" +
                "Trips, ácaros o cochinillas pueden causar pequeñas manchas marrones o negruzcas y deformar las hojas.\n" +
                "Solución: Revisa el envés de las hojas y aplica jabón potásico o aceite de neem si detectas plagas.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Manchas marrón oscuro, a veces con halo amarillo, pueden deberse a hongos o bacterias, especialmente si el ambiente es muy húmedo y sin ventilación.\n" +
                "Solución: Retira las hojas afectadas, mejora la ventilación y, si es necesario, aplica un fungicida suave.\n" +
                "Daños físicos o quemaduras por sol\n" +
                "\n" +
                "Golpes, roces, o exposición a sol directo producen manchas marrones secas e irregulares.\n" +
                "Solución: Evita el sol directo y manipula la planta con cuidado.','Recorta las partes dañadas con tijeras limpias si las manchas son grandes o antiestéticas.\n" +
                "Ajusta el riego y la humedad ambiental.\n" +
                "Cambia el agua de riego si usas agua del grifo.\n" +
                "Observa si aparecen plagas o si las manchas se expanden rápidamente (en ese caso, podría ser un hongo o bacteria).',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'La baja humedad es una de las causas más frecuentes de problemas en la Calathea bella (y en todas las calatheas). Aquí tienes cómo se manifiesta, por qué es un problema y cómo solucionarlo:\n" +
                "\n" +
                "¿Cómo afecta la baja humedad a la Calathea bella?\n" +
                "Puntas y bordes marrones:\n" +
                "Las hojas comienzan a secarse desde las puntas o bordes, que se vuelven marrones y quebradizos.\n" +
                "Manchas secas:\n" +
                "Pueden aparecer manchas marrones irregulares en la superficie de las hojas.\n" +
                "Hojas enrolladas:\n" +
                "Para evitar perder agua, la planta puede enrollar sus hojas sobre sí mismas.\n" +
                "Crecimiento lento y hojas opacas:\n" +
                "La planta puede dejar de sacar nuevas hojas o las hojas nuevas salen más pequeñas y sin brillo.\n" +
                "¿Por qué la baja humedad es un problema?\n" +
                "La Calathea bella es originaria de ambientes tropicales, donde la humedad relativa es alta (60-90%). En interiores, especialmente en climas secos o con calefacción/aire acondicionado, la humedad puede caer por debajo del 40%, lo que estresa mucho a la planta.\n" +
                "\n','Usa un humidificador:\n" +
                "Es la forma más efectiva y estable de mantener alta la humedad ambiental.\n" +
                "\n" +
                "Agrupa plantas:\n" +
                "Juntar varias plantas crea un microclima más húmedo alrededor de ellas.\n" +
                "\n" +
                "Bandeja con piedras y agua:\n" +
                "Coloca la maceta sobre una bandeja con piedras y algo de agua (sin que la base toque el agua directamente).\n" +
                "\n" +
                "Pulveriza agua (con moderación):\n" +
                "Puedes rociar agua en el aire alrededor de la planta, pero no sobre las hojas directamente, ya que puede favorecer hongos.\n" +
                "\n" +
                "Evita corrientes de aire y aparatos que resequen el ambiente:\n" +
                "Aléjala de radiadores, aire acondicionado y ventanas muy secas.\n" +
                "\n" +
                "Resumen\n" +
                "La baja humedad causa puntas marrones, manchas secas y debilitamiento en Calathea bella. Sube la humedad con un humidificador o trucos caseros para que la planta luzca sana y vibrante. Si tienes síntomas específicos en tu planta, descríbelos para una recomendación más personalizada.',48)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'El envejecimiento en la Calathea bella, como en la mayoría de las plantas, es un proceso natural en el que las hojas más viejas comienzan a deteriorarse, mostrando síntomas específicos antes de caer. Es importante saber reconocer cuándo una hoja está envejeciendo de forma normal y cuándo el daño se debe a otros factores.\n" +
                "\n" +
                "¿Cómo reconocer el envejecimiento natural?\n" +
                "Afecta a las hojas más viejas (inferiores):\n" +
                "Las hojas nuevas siguen viéndose sanas y vigorosas, mientras que las más antiguas cambian de color.\n" +
                "\n" +
                "Síntomas graduales:\n" +
                "Las hojas viejas empiezan a amarillear, pueden mostrar manchas marrones en las puntas o bordes, secarse lentamente y finalmente caer.\n" +
                "\n" +
                "Sin expansión rápida:\n" +
                "El daño no avanza rápidamente a otras hojas, ni afecta a las hojas jóvenes.\n" +
                "\n" +
                "No hay otros síntomas:\n" +
                "No hay presencia de plagas, deformidades graves, pudriciones ni malos olores.','Retira hojas secas o muy dañadas:\n" +
                "Usa tijeras limpias para cortar las hojas completamente secas o con más del 50% de daño. Esto mejora la apariencia y la planta puede concentrar energía en las hojas sanas.\n" +
                "\n" +
                "No te preocupes si solo son unas pocas hojas:\n" +
                "Es completamente normal que una Calathea bella pierda de vez en cuando alguna hoja vieja.\n" +
                "\n" +
                "¿Cuándo preocuparte?\n" +
                "Si muchas hojas (incluidas las nuevas) muestran síntomas.\n" +
                "Si el amarilleo o las manchas avanzan rápidamente.\n" +
                "Si hay mal olor, zonas blandas o presencia de plagas.\n" +
                "En esos casos, el daño no suele deberse al envejecimiento, sino a un problema ambiental, de riego, plagas o enfermedades.\n" +
                "\n" +
                "Resumen\n" +
                "El envejecimiento en la Calathea bella es normal y natural. Solo debes intervenir cortando hojas completamente secas o muy dañadas. Si el problema afecta muchas hojas o avanza rápido, investiga otras causas. Si tienes una foto o puedes describir los síntomas, puedo ayudarte a diferenciar envejecimiento de otros problemas.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'El marchitamiento de las puntas de las hojas en la Calathea bella es uno de los problemas más habituales y suele indicar que alguna condición ambiental no es óptima. Aquí tienes un resumen de las causas más frecuentes, cómo identificarlas y cómo solucionarlas:\n" +
                "\n" +
                "Causas comunes del marchitamiento de puntas en Calathea bella\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Es la causa más frecuente. Las calatheas necesitan humedad alta (idealmente 60-80%). Si el aire es seco, las puntas se secan y marchitan.\n" +
                "Solución: Usa humidificador, agrupa plantas, pon una bandeja con agua y piedras bajo la maceta, mantén la planta lejos de calefacciones o aire acondicionado.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Falta de agua: El sustrato muy seco provoca puntas secas y hojas lacias.\n" +
                "Exceso de agua: Puede asfixiar las raíces, también causando daño en las puntas.\n" +
                "Solución: Mantén el sustrato ligeramente húmedo, sin encharcar. Usa agua filtrada o destilada.\n" +
                "Calidad del agua\n" +
                "\n" +
                "El agua con cloro, cal o sales puede quemar las puntas.\n" +
                "Solución: Riega con agua filtrada, destilada o de lluvia.\n" +
                "Corrientes de aire o cambios bruscos de temperatura\n" +
                "\n" +
                "El aire frío o caliente, o los cambios rápidos de temperatura, dañan las puntas.\n" +
                "Solución: Sitúa la planta en un lugar protegido, sin corrientes y con temperatura estable.\n" +
                "Acumulación de sales o fertilizante\n" +
                "\n" +
                "El exceso de fertilizante o de sales en el sustrato puede quemar las puntas.\n" +
                "Solución: Fertiliza solo en temporada de crecimiento y en dosis bajas; si sospechas acumulación, realiza un riego abundante para lavar el sustrato.\n" +
                "Envejecimiento natural\n" +
                "\n" +
                "Las hojas más viejas pueden marchitarse en las puntas de forma natural antes de secarse por completo y caer.','Recorta solo las puntas secas con tijeras limpias, siguiendo la forma natural de la hoja (no cortes en línea recta).\n" +
                "Revisa y ajusta la humedad ambiental y el riego.\n" +
                "Cambia el agua de riego si usas agua del grifo.\n" +
                "Mantén la planta alejada de corrientes y calefacciones.\n" +
                "Si el resto de la hoja está sano y el problema no avanza, no te preocupes: es común en calatheas, sobre todo en ambientes secos.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'Copilot said: Las **cicatrices** en las hojas de la\n" +
                "Las cicatrices en las hojas de la Calathea bella suelen referirse a marcas, líneas o zonas donde la hoja muestra un tejido diferente, como una especie de \"herida vieja\" que ha sanado. No son manchas frescas ni zonas blandas, sino marcas secas, a veces de color marrón claro o blanquecino, que pueden aparecer por varias razones:\n" +
                "\n" +
                "Causas comunes de cicatrices en Calathea bella\n" +
                "Daños físicos previos\n" +
                "\n" +
                "Golpes, roces, o cortes durante el transporte, manipulación o poda pueden dejar cicatrices permanentes en las hojas.\n" +
                "A veces ocurren al mover la planta, al tocar una hoja accidentalmente, o al rozar con otras macetas.\n" +
                "Antiguas quemaduras o deshidratación\n" +
                "\n" +
                "Si la hoja sufrió desecación o quemadura por sol, esa zona puede quedar con aspecto cicatrizado después de secarse.\n" +
                "Ataques de plagas o enfermedades superadas\n" +
                "\n" +
                "Una lesión antigua causada por plagas (trips, ácaros, cochinilla) o alguna enfermedad puede dejar marcas tras la recuperación.\n" +
                "Daños por riego sobre las hojas\n" +
                "\n" +
                "El agua estancada en las hojas durante mucho tiempo puede provocar una herida que, al secarse, deja una cicatriz.\n" +
                "¿Es grave tener cicatrices en la Calathea bella?\n" +
                "No, las cicatrices en sí mismas no son peligrosas, solo afectan la estética. Si la hoja está verde, firme y la planta sigue creciendo bien, no hay de qué preocuparse. No hay manera de “curar” una cicatriz; esa zona quedará así hasta que la hoja envejezca y caiga de forma natural.','No es necesario cortar la hoja si solo tiene pequeñas cicatrices y el resto está sano.\n" +
                "Puedes recortar la zona dañada si la cicatriz es grande y antiestética, usando tijeras limpias y siguiendo la forma natural de la hoja.\n" +
                "Evita más daños: Manipula la planta con cuidado y evita exponerla a sol directo, corrientes de aire o golpes.\n" +
                "Prevención\n" +
                "Coloca la planta en un sitio donde no sufra roces ni golpes.\n" +
                "Evita mojar las hojas y usa agua de buena calidad.\n" +
                "Mantén un ambiente estable y revisa regularmente para actuar rápido ante plagas o problemas.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'La podrición de la hoja en Calathea bella es un problema serio y suele estar relacionada con condiciones de exceso de humedad, mal drenaje, falta de ventilación o infecciones fúngicas/bacterianas. Aquí te explico cómo identificarla, las causas más comunes y qué hacer para salvar tu planta:\n" +
                "\n" +
                "¿Cómo identificar la podrición de la hoja?\n" +
                "Manchas blandas, oscuras y húmedas:\n" +
                "Las zonas afectadas se sienten blandas al tacto, a menudo de color marrón oscuro o negro.\n" +
                "Mal olor:\n" +
                "Si hay mal olor, es signo de infección bacteriana avanzada.\n" +
                "Hojas marchitas, caídas o pegajosas:\n" +
                "La hoja puede estar lacia y fácil de desprender.\n" +
                "Podrición que avanza rápidamente:\n" +
                "El daño se extiende de manera más veloz que manchas por sequedad o envejecimiento.\n" +
                "Afecta hojas bajas primero:\n" +
                "Suele comenzar en las hojas más cercanas al sustrato.\n" +
                "Causas principales de la podrición de hoja en Calathea bella\n" +
                "Exceso de riego\n" +
                "\n" +
                "El sustrato siempre húmedo o encharcado favorece hongos y bacterias.\n" +
                "Mal drenaje del sustrato\n" +
                "\n" +
                "Maceta sin agujeros, sustrato muy compacto o apelmazado.\n" +
                "Falta de ventilación\n" +
                "\n" +
                "Ambientes muy cerrados y húmedos favorecen el desarrollo de patógenos.\n" +
                "Infecciones fúngicas o bacterianas\n" +
                "\n" +
                "Hongos (Pythium, Phytophthora, Fusarium) y bacterias prosperan en ambientes húmedos y pueden atacar hojas y raíces.\n" +
                "Agua sobre las hojas\n" +
                "\n" +
                "Dejar agua estancada en las hojas favorece enfermedades.\n','Retira las hojas afectadas:\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas. Elimina toda hoja o parte blanda y podrida. No toques otras hojas con las tijeras sin limpiarlas.\n" +
                "Revisa el sustrato:\n" +
                "\n" +
                "Si está encharcado, trasplanta de inmediato a un sustrato nuevo, suelto y aireado. Verifica que la maceta tenga buen drenaje.\n" +
                "Reduce los riegos:\n" +
                "\n" +
                "Deja secar la capa superficial del sustrato antes de volver a regar.\n" +
                "Mejora la ventilación:\n" +
                "\n" +
                "Asegura circulación de aire, pero sin corrientes frías directas.\n" +
                "Evita mojar las hojas al regar:\n" +
                "\n" +
                "Siempre riega al pie de la planta.\n" +
                "Desinfecta herramientas y manos:\n" +
                "\n" +
                "Para no propagar patógenos.\n" +
                "Considera un tratamiento fúngico o bactericida:\n" +
                "\n" +
                "Si la podrición es severa y avanza rápido, utiliza un fungicida adecuado para plantas de interior (consulta en vivero).\n" +
                "Prevención\n" +
                "Usa sustrato suelto y maceta con buen drenaje.\n" +
                "Riega solo cuando la capa superior esté seca.\n" +
                "Mantén buena ventilación.\n" +
                "No mojes las hojas ni dejes agua en los platos bajo la maceta.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'Un punto negro en la hoja de una Calathea bella puede tener varias causas. La interpretación depende de su tamaño, si es único o hay varios, si la hoja muestra otros síntomas, y si el punto está seco, hundido, elevado o pegajoso. Aquí tienes los escenarios más frecuentes y qué hacer:\n" +
                "\n" +
                "Causas comunes de puntos negros en Calathea bella\n" +
                "Plagas (Trips, araña roja, cochinilla)\n" +
                "\n" +
                "Los trips y ácaros dejan pequeños puntos negros (excrementos) en el envés de la hoja.\n" +
                "Busca también manchas plateadas, hojas deformes o telarañas finas.\n" +
                "Solución: Revisa el envés, limpia y trata con jabón potásico o aceite de neem si hay plaga.\n" +
                "Hongos\n" +
                "\n" +
                "Algunos hongos producen puntos negros, a veces rodeados de un halo amarillo o marrón (mancha foliar).\n" +
                "Suele aparecer en ambientes muy húmedos o con poca ventilación.\n" +
                "Solución: Retira la hoja afectada, mejora ventilación, reduce riegos, considera fungicida suave.\n" +
                "Bacterias\n" +
                "\n" +
                "Manchas negras hundidas, a veces con borde amarillo y aspecto húmedo.\n" +
                "Puede avanzar rápido si el ambiente es cálido y húmedo.\n" +
                "Solución: Retira la hoja, desinfecta herramientas, evita mojar las hojas al regar.\n" +
                "Daños físicos\n" +
                "\n" +
                "Un golpe, corte o pellizco puede dejar una cicatriz oscura, normalmente seca y sin expandirse.\n" +
                "Solución: No es necesario hacer nada si la hoja es sana y el punto no crece.','Observa si hay otros síntomas:\n" +
                "¿Puntos negros en más hojas? ¿Manchas, deformidades, pegajosidad, telarañas?\n" +
                "\n" +
                "Revisa el envés de la hoja:\n" +
                "Si hay plaga, verás movimiento, puntos negros múltiples o telarañas.\n" +
                "\n" +
                "Aísla la planta si sospechas enfermedad:\n" +
                "Retira hojas muy afectadas y mejora ventilación.\n" +
                "\n" +
                "No mojes las hojas al regar para evitar hongos y bacterias.\n" +
                "\n" +
                "Cuándo preocuparse\n" +
                "Si los puntos aumentan rápido o aparecen en varias hojas.\n" +
                "Si hay síntomas como manchas amarillas, bordes blandos, mal olor o deformidades.\n',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'El oídio es una enfermedad fúngica común que puede afectar a la Calathea bella (y a muchas otras plantas de interior y exterior). Se reconoce fácilmente y, aunque rara vez mata a la planta, sí afecta su vigor y aspecto si no se trata.\n" +
                "\n" +
                "¿Cómo identificar el oídio?\n" +
                "Manchas blancas o grises en polvo:\n" +
                "Aparecen en la superficie de las hojas, tallos y a veces en brotes jóvenes.\n" +
                "Crecimiento lento y hojas deformadas:\n" +
                "Las zonas afectadas pueden arrugarse, amarillear y deformarse con el tiempo.\n" +
                "Apariencia polvorienta:\n" +
                "Si frotas la mancha, el polvo blanco suele desprenderse fácilmente.\n" +
                "Se propaga rápidamente:\n" +
                "En ambientes cálidos, húmedos y con poca ventilación.\n" +
                "Causas principales\n" +
                "Humedad alta con mala ventilación:\n" +
                "El oídio prospera cuando hay humedad en el aire pero las hojas no se secan rápido.\n" +
                "Ambientes sobrepoblados:\n" +
                "Muchas plantas juntas facilitan la transmisión.\n" +
                "Corrientes de aire frío tras riego:\n" +
                "Pueden favorecer el desarrollo del hongo.\n','Aísla la planta afectada:\n" +
                "Para evitar contagiar a otras plantas.\n" +
                "\n" +
                "Retira las hojas muy afectadas:\n" +
                "Usa tijeras limpias y desinfectadas.\n" +
                "\n" +
                "Mejora la ventilación:\n" +
                "Asegúrate de que el aire circule bien alrededor de la planta.\n" +
                "\n" +
                "Reduce la humedad ambiental si es excesiva:\n" +
                "Aunque la Calathea aprecia humedad, el exceso sin ventilación favorece el hongo.\n" +
                "\n" +
                "Tratamiento natural:\n" +
                "\n" +
                "Pulveriza con una solución de agua y bicarbonato de sodio (1 litro de agua + 1 cucharadita de bicarbonato + unas gotas de jabón neutro como adherente), cada 5-7 días.\n" +
                "También puedes usar leche diluida (20% leche, 80% agua) para rociar las hojas.\n" +
                "Tratamiento químico:\n" +
                "\n" +
                "Si el oídio es severo, usa un fungicida específico para oídio, apto para plantas de interior (consulta en vivero y sigue las instrucciones del fabricante).\n" +
                "Limpia el entorno:\n" +
                "\n" +
                "Retira hojas caídas y restos vegetales de la maceta.\n" +
                "Prevención\n" +
                "Mantén buena ventilación.\n" +
                "Evita mojar las hojas al regar.\n" +
                "No sobrepobles el área de tus plantas.\n" +
                "Observa regularmente para detectar los primeros síntomas.',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'La clorosis venal en la Calathea bella (y otras plantas) se refiere a un síntoma donde el tejido entre las venas de la hoja se vuelve amarillento (clorosis), mientras que las venas permanecen verdes y destacadas. Este patrón es diferente del amarilleamiento general de la hoja y suele indicar problemas específicos.\n" +
                "\n" +
                "Causas más frecuentes de clorosis venal\n" +
                "Deficiencia de hierro (ferroclorosis)\n" +
                "\n" +
                "Es la causa más común en plantas de interior.\n" +
                "Ocurre cuando la planta no puede absorber suficiente hierro, aunque esté en el sustrato.\n" +
                "Suele empezar en hojas jóvenes: venas verdes, resto de la hoja amarillo.\n" +
                "Solución: Aplica quelato de hierro (puedes encontrarlo en viveros) y asegúrate de que el pH del sustrato no sea demasiado alto (alcalino).\n" +
                "Deficiencia de otros micronutrientes\n" +
                "\n" +
                "Deficiencia de manganeso, zinc o magnesio también puede causar clorosis venal, pero es menos frecuente.\n" +
                "Solución: Usa un fertilizante equilibrado con micronutrientes.\n" +
                "pH del sustrato inadecuado\n" +
                "\n" +
                "Un pH demasiado alto (>7) bloquea la absorción de hierro y otros micronutrientes.\n" +
                "Solución: Revisa el pH del sustrato y corrígelo si es necesario (ideal para Calathea: 5,5-6,5).\n" +
                "Exceso de riego o mal drenaje\n" +
                "\n" +
                "El sustrato encharcado reduce la capacidad de la planta para absorber nutrientes.\n" +
                "Solución: Mejora el drenaje, deja secar la capa superficial antes de volver a regar.\n" +
                "Raíces dañadas o compactadas\n" +
                "\n" +
                "Raíces enfermas o apretadas no absorben bien los nutrientes.\n" +
                "Solución: Revisa el sistema radicular y trasplanta si es necesario.\n','Asegúrate de que el riego y drenaje sean correctos.\n" +
                "Comprueba el pH del sustrato (kits disponibles en viveros).\n" +
                "Aplica quelato de hierro siguiendo instrucciones del fabricante.\n" +
                "Usa fertilizante con micronutrientes si sospechas más carencias.\n" +
                "Evita agua con alto contenido de cal (usa agua filtrada, destilada o de lluvia).\n" +
                "¿Cuándo mejora?\n" +
                "Si el problema es por deficiencia de hierro y actúas rápido, las hojas nuevas saldrán verdes y sanas.\n" +
                "Las hojas viejas con clorosis pueden no recuperarse, pero no empeorarán si corriges la causa.',51)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (210,'Los insectos chupadores de savia son una plaga común en plantas de interior como la Calathea bella. Estos insectos se alimentan perforando los tejidos de la planta y succionando la savia, lo que puede debilitarla y causar diversos síntomas visibles.\n" +
                "\n" +
                "Principales insectos chupadores de savia en Calathea bella\n" +
                "Pulgones\n" +
                "\n" +
                "Pequeños, blandos, de varios colores (verde, negro, amarillo).\n" +
                "Se agrupan en brotes tiernos y el envés de las hojas.\n" +
                "Cochinillas\n" +
                "\n" +
                "Pueden verse como pequeños bultos algodonosos (cochinilla algodonosa) o costras marrones/transparentes (cochinilla de escudo).\n" +
                "Suelen estar en nervaduras y tallos.\n" +
                "Trips\n" +
                "\n" +
                "Muy pequeños y alargados, difíciles de ver a simple vista.\n" +
                "Dejan manchas plateadas, puntos negros y deforman las hojas.\n" +
                "Ácaros (araña roja)\n" +
                "\n" +
                "No son insectos, pero también chupan savia.\n" +
                "Dejan punteado amarillento, telarañas finas y sequedad en las hojas.\n" +
                "Mosca blanca\n" +
                "\n" +
                "Pequeños insectos blancos que vuelan al mover la planta.\n" +
                "Sus larvas succionan savia en el envés de las hojas.\n" +
                "Síntomas de la presencia de insectos chupadores\n" +
                "Manchas amarillas o plateadas.\n" +
                "Hojas deformadas, enrolladas o pegajosas (debido a la melaza que secretan).\n" +
                "Presencia de puntos negros (excrementos).\n" +
                "Crecimiento lento o debilitamiento general.\n" +
                "Aparición de moho negro (fumagina) sobre la melaza.','Aísla la planta para evitar contagios.\n" +
                "Revisa el envés de las hojas y tallos con lupa si es posible.\n" +
                "Elimina manualmente los insectos visibles con un paño húmedo o bastoncillo.\n" +
                "Trata con jabón potásico o aceite de neem:\n" +
                "Pulveriza bien ambas caras de las hojas cada 5-7 días durante 2-3 semanas.\n" +
                "Repite el tratamiento hasta eliminar la plaga.\n" +
                "Mejora la ventilación y revisa otras plantas cercanas.\n" +
                "Prevención\n" +
                "Mantén la planta sana y en condiciones óptimas.\n" +
                "Revisa periódicamente el envés de las hojas.\n" +
                "Evita el exceso de humedad y el aire estancado.\n',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Las manchas marrones pueden deberse a muchas causas: deshidratación, quemaduras, exceso de fertilizante, infecciones o daños físicos.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas marrones de tamaño y forma variable, secas (bordes nítidos) o húmedas (bordes difusos).\n" +
                "Las manchas pueden aparecer en puntas, bordes o en el centro de la hoja.\n" +
                "En ocasiones, tienen halo amarillo.\n" +
                "Causas:\n" +
                "\n" +
                "Falta de humedad: puntas y bordes marrones, secos.\n" +
                "Quemaduras solares: manchas secas en zonas expuestas al sol directo.\n" +
                "Exceso de fertilizante: sales acumuladas queman zonas de la hoja.\n" +
                "Enfermedades fúngicas o bacterianas: manchas marrones con halo amarillo, a veces húmedas y blandas, pueden extenderse rápidamente.\n" +
                "Daños físicos: golpes, cortes, roces.','Ajusta la humedad ambiental y el riego.\n" +
                "Evita el sol directo sobre la planta.\n" +
                "Fertiliza solo en dosis bajas y en época de crecimiento; si hay exceso de sales, enjuaga el sustrato.\n" +
                "Si sospechas hongo o bacteria, retira hojas afectadas y usa fungicida si avanza rápido.\n" +
                "Si es daño físico, solo recorta si afecta la estética.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'La Calathea roseopicta es una planta tropical que necesita ambientes húmedos. Cuando la humedad es baja (menos del 50%), la planta sufre, ya que transpira más agua de la que puede absorber por las raíces, lo que afecta la salud de las hojas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Puntas de las hojas marrones, secas y a veces quebradizas.\n" +
                "Bordes de las hojas también pueden verse deshidratados y marrones.\n" +
                "Hojas que se enrollan hacia adentro como mecanismo de defensa.\n" +
                "Hojas opacas, menos brillantes.\n" +
                "Crecimiento lento o detenido.\n" +
                "Causas:\n" +
                "\n" +
                "Uso de calefacción o aire acondicionado.\n" +
                "Corrientes de aire frío o cálido.\n" +
                "Falta de humidificador o de agrupación con otras plantas.\n" +
                "Ubicación lejos de fuentes de humedad (baños, cocinas).','Coloca un humidificador cerca de la planta para mantener la humedad sobre el 60%.\n" +
                "Junta varias plantas para crear un microclima húmedo.\n" +
                "Pon la maceta sobre una bandeja con guijarros y agua (sin que la base de la maceta toque el agua).\n" +
                "Rocía ligeramente el aire (no las hojas) alrededor de la planta si el ambiente es muy seco.\n" +
                "Aleja la planta de radiadores, estufas y corrientes de aire.\n',48)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Todas las hojas tienen un ciclo de vida. Las hojas más viejas de la Calathea roseopicta, que suelen ser las más bajas, eventualmente mueren para dar paso a hojas nuevas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Una o dos hojas viejas (las inferiores) comienzan a amarillear.\n" +
                "Progresivamente se vuelven marrones y se secan desde la punta hacia la base.\n" +
                "El resto de la planta está sana, con hojas nuevas creciendo.\n" +
                "Causas:\n" +
                "\n" +
                "Proceso natural de recambio foliar.\n" +
                "No está relacionado con enfermedades ni deficiencias si ocurre en pocas hojas y el resto de la planta está sana.','Espera a que la hoja esté completamente seca y retírala con tijeras limpias.\n" +
                "No te preocupes si ocurre de forma aislada.\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Las puntas de las hojas son la parte más vulnerable a la deshidratación y acumulación de sales. Se marchitan y secan si la planta no recibe condiciones óptimas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Puntas marrones, secas y ásperas al tacto.\n" +
                "Puede acompañarse de bordes marrones o secos.\n" +
                "En casos severos, la sequedad avanza hacia el centro de la hoja.\n" +
                "Causas:\n" +
                "\n" +
                "Humedad ambiental insuficiente.\n" +
                "Riego inadecuado (demasiada o poca agua).\n" +
                "Uso de agua dura (con mucha cal o cloro).\n" +
                "Exceso de fertilizante que quema las puntas.\n" +
                "Acumulación de sales en el sustrato.','Sube la humedad ambiental.\n" +
                "Riega solo cuando la capa superior del sustrato esté seca, evitando encharcar.\n" +
                "Usa agua filtrada, destilada o de lluvia.\n" +
                "Lava el sustrato de vez en cuando (riega en exceso para arrastrar sales).\n" +
                "Fertiliza solamente en época de crecimiento (primavera-verano) y con dosis reducidas.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Las cicatrices en las hojas son marcas permanentes debidas a daños pasados, ya sean mecánicos, por quemaduras o plagas que ya no están activas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas secas, marrones o blanquecinas, a veces en líneas o formas irregulares.\n" +
                "No aumentan de tamaño con el tiempo.\n" +
                "No hay tejido blando, húmedo ni mal olor.\n" +
                "Causas:\n" +
                "\n" +
                "Golpes o roces al mover la planta.\n" +
                "Quemaduras solares antiguas.\n" +
                "Daños de plagas o podas previas.','No requieren tratamiento, sólo afectan a la estética.\n" +
                "Puedes recortar la parte dañada si lo deseas.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'La podrición de la hoja suele deberse a exceso de agua, mal drenaje y proliferación de hongos o bacterias. Es una emergencia, ya que puede extenderse muy rápido y matar la planta.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas blandas, oscuras y húmedas, a veces de color marrón muy oscuro o negro.\n" +
                "Hojas lacias, caídas, pueden desprender mal olor.\n" +
                "Las manchas se expanden rápidamente.\n" +
                "Puede aparecer moho blanco o gris en la superficie afectada.\n" +
                "Causas:\n" +
                "\n" +
                "Sustrato encharcado o compactado.\n" +
                "Maceta sin agujeros de drenaje.\n" +
                "Ambiente cerrado y muy húmedo.\n" +
                "Riego excesivo.\n" +
                "Agua acumulada sobre las hojas.','Retira inmediatamente toda hoja o parte afectada con tijeras desinfectadas.\n" +
                "Cambia el sustrato si está demasiado húmedo o con mal olor.\n" +
                "Usa sustrato suelto y maceta con buen drenaje.\n" +
                "Reduce los riegos, deja secar la capa superior entre uno y otro.\n" +
                "Mejora la ventilación.\n" +
                "Aplica fungicida si la podrición es extensa o avanza rápido.\n',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Los puntos negros en las hojas pueden deberse a plagas, infecciones o daños físicos. Su gravedad depende de si se expanden o si están acompañados de otros síntomas.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Punto negro pequeño, seco o húmedo, a veces rodeado de halo claro o amarillo.\n" +
                "Puede estar hundido o elevado.\n" +
                "Puede haber varios puntos dispersos en la hoja.\n" +
                "Causas:\n" +
                "\n" +
                "Plagas como trips y ácaros (dejan excrementos o pequeñas heridas negras).\n" +
                "Hongos (punto negro que puede crecer y tener halo).\n" +
                "Bacterias (punto negro hundido con borde amarillo).\n" +
                "Daño físico puntual (golpe, raspón).','Revisa el envés de la hoja y otras hojas para detectar plagas.\n" +
                "Si hay plaga, trata con jabón potásico o aceite de neem.\n" +
                "Si el punto crece o se humedece, retira la hoja afectada.\n" +
                "Si es único, seco y no crece, no es preocupante.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Oídio es un hongo que produce un polvo blanco o gris sobre las hojas, afectando la fotosíntesis y debilitando la planta.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas blancas o grises en polvo en la superficie de las hojas.\n" +
                "Hojas que se arrugan, amarillean o deforman.\n" +
                "El polvo se desprende al frotar con el dedo.\n" +
                "El crecimiento se ralentiza.\n" +
                "Causas:\n" +
                "\n" +
                "Humedad ambiental alta sin ventilación.\n" +
                "Plantas muy juntas.\n" +
                "Cambios bruscos de temperatura.','Aísla la planta para evitar contagio.\n" +
                "Retira hojas muy afectadas con tijeras desinfectadas.\n" +
                "Mejora la ventilación.\n" +
                "Rocia con agua + bicarbonato de sodio (1 cucharadita/litro) + unas gotas de jabón neutro, o con leche diluida (20% leche y 80% agua).\n" +
                "Si es necesario, usa fungicida específico para oídio.\n',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Clorosis venal es cuando el tejido entre las venas de la hoja se vuelve amarillo, pero las venas se mantienen verdes, señal de problemas en la absorción de nutrientes.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Hojas (sobre todo nuevas) con venas verdes y el resto de la hoja amarilla.\n" +
                "La hoja puede lucir pálida y débil.\n" +
                "Las hojas viejas suelen estar menos afectadas.\n" +
                "Causas:\n" +
                "\n" +
                "Deficiencia de hierro (ferroclorosis), la más común.\n" +
                "Deficiencia de otros micronutrientes: manganeso, zinc, magnesio.\n" +
                "pH del sustrato demasiado alto (alcalino), que bloquea la absorción de hierro.\n" +
                "Exceso de riego o mal drenaje.\n" +
                "Raíces dañadas, compactadas o asfixiadas.','Revisa que el sustrato drene bien y no esté siempre húmedo.\n" +
                "Usa quelato de hierro según indicaciones.\n" +
                "Aplica fertilizante con micronutrientes.\n" +
                "Comprueba el pH del sustrato (ideal: 5,5–6,5).\n" +
                "Riega con agua filtrada, destilada o de lluvia.',51)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (220,'Son plagas que se alimentan perforando las hojas y succionando la savia, debilitando a la planta y transmitiendo enfermedades.\n" +
                "\n" +
                "Síntomas:\n" +
                "\n" +
                "Manchas amarillas o plateadas.\n" +
                "Hojas deformadas, enrolladas o pegajosas por la melaza que secretan.\n" +
                "Puntos negros (excrementos).\n" +
                "Crecimiento lento y debilitamiento general.\n" +
                "Aparición de moho negro (fumagina) sobre la melaza.\n" +
                "Plagas comunes:\n" +
                "\n" +
                "Pulgones: pequeños, blandos, en brotes y envés de hojas.\n" +
                "Cochinillas: bultos algodonosos o costras marrones.\n" +
                "Trips: alargados, dejan manchas plateadas y puntos negros.\n" +
                "Araña roja: punteados amarillos, telarañas finas.\n" +
                "Mosca blanca: pequeños insectos blancos voladores.','Aísla la planta para evitar contagio.\n" +
                "Revisa el envés de las hojas y tallos (usa lupa si puedes).\n" +
                "Limpia manualmente los insectos con bastoncillo o paño húmedo.\n" +
                "Pulveriza con jabón potásico o aceite de neem cada 5-7 días durante al menos 2-3 semanas.\n" +
                "Mejora ventilación y revisa otras plantas cercanas.',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'Las manchas marrones en Calathea lutea pueden tener distintos orígenes y, según su aspecto, ubicación y evolución, nos orientan sobre el problema subyacente. Esta planta tropical, de hojas grandes y robustas, es sensible a varios factores ambientales y de cuidado que pueden manifestarse con manchas marrones de diversa apariencia.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas de color marrón claro, marrón oscuro o casi negro.\n" +
                "Pueden ser secas (bordes nítidos, textura quebradiza) o húmedas (bordes difusos, tacto blando).\n" +
                "Ubicación: pueden aparecer en las puntas, los bordes o en el centro de la hoja.\n" +
                "A veces rodeadas por un halo amarillento o blanquecino.\n" +
                "Evolución: pueden quedarse estables, crecer lentamente o extenderse rápidamente.\n" +
                "En casos severos, pueden causar que la hoja entera se seque o se deteriore desde el punto de la mancha hacia fuera.\n" +
                "Causas principales y cómo diferenciarlas\n" +
                "1. Falta de humedad ambiental\n" +
                "Características:\n" +
                "Manchas marrones secas en puntas y bordes de las hojas.\n" +
                "A menudo acompañadas de hojas enrolladas o secas.\n" +
                "Explicación:\n" +
                "Calathea lutea necesita humedad ambiental alta (mínimo 50-60%). El aire seco seca los extremos de las hojas.\n" +
                "Solución:\n" +
                "Incrementa la humedad con humidificador, bandeja con agua y piedras, o agrupando plantas.\n" +
                "2. Quemaduras solares\n" +
                "Características:\n" +
                "Manchas marrones secas o blanquecinas, a veces con aspecto “crujiente”.\n" +
                "Aparecen en zonas de la hoja que reciben luz directa.\n" +
                "Explicación:\n" +
                "La luz solar directa quema los tejidos, especialmente en las horas centrales del día.\n" +
                "Solución:\n" +
                "Reubica la planta en un lugar con luz brillante pero filtrada o semisombra.\n" +
                "3. Exceso de fertilizante y acumulación de sales\n" +
                "Características:\n" +
                "Manchas marrones o amarillas irregulares, a veces desde las puntas y bordes.\n" +
                "Sustrato con costras blancas (sales).\n" +
                "Explicación:\n" +
                "El exceso de abono o fertilizante “quema” las raíces y se manifiesta en las hojas.\n" +
                "Solución:\n" +
                "Suspende la fertilización temporalmente, riega en abundancia para “lavar” el sustrato y retoma con menos dosis en el futuro.\n" +
                "4. Riego inadecuado\n" +
                "Características:\n" +
                "Manchas marrones secas (falta de agua) o húmedas/oscurecidas (exceso de agua).\n" +
                "Hojas lacias o con bordes blandos si hay exceso de riego.\n" +
                "Explicación:\n" +
                "La falta de agua seca partes de la hoja; el encharcamiento asfixia raíces y favorece hongos.\n" +
                "Solución:\n" +
                "Mantén el sustrato ligeramente húmedo, nunca encharcado. Usa maceta con buen drenaje.\n" +
                "5. Enfermedades fúngicas o bacterianas\n" +
                "Características:\n" +
                "Manchas marrones con borde difuso, a veces húmedas, pueden crecer rápidamente.\n" +
                "Pueden estar acompañadas de olor desagradable, moho o exudado.\n" +
                "Explicación:\n" +
                "El exceso de humedad y poca ventilación favorecen la aparición de hongos y bacterias.\n" +
                "Solución:\n" +
                "Retira hojas afectadas, mejora ventilación, usa fungicida si la mancha crece rápido.\n" +
                "6. Daños físicos\n" +
                "Características:\n" +
                "Manchas marrones secas o cicatrices en zonas donde la hoja fue golpeada, doblada o cortada.\n" +
                "La mancha no crece ni cambia.\n" +
                "Explicación:\n" +
                "Manipulación, rozaduras, transporte o mascotas pueden dañar las hojas.\n" +
                "Solución:\n" +
                "No requieren acción, solo recorta si es antiestético.','Observa el aspecto de la mancha:\n" +
                "¿Es seca o húmeda? ¿Tiene halo amarillo? ¿Está en el borde, centro o punta?\n" +
                "Evalúa las condiciones ambientales:\n" +
                "¿Hay humedad suficiente? ¿Luz directa? ¿Ventilación?\n" +
                "Revisa el sustrato y riego:\n" +
                "¿Está seco, encharcado, con costras blancas?\n" +
                "Descarta plagas:\n" +
                "Aunque las manchas marrones suelen ser por factores ambientales, revisa el envés de las hojas por si ves insectos o daños adicionales.\n" +
                "Actúa según la causa sospechada (ver arriba).\n" +
                "Retira hojas muy dañadas:\n" +
                "Usa tijeras desinfectadas para evitar que el problema se propague.\n" +
                "Monitorea:\n" +
                "Si aparecen más manchas o crecen rápido, considera usar fungicida de amplio espectro.\n" +
                "Prevención\n" +
                "Mantén siempre la humedad ambiental alta (humidificador, bandeja, agrupar plantas).\n" +
                "Ubica la Calathea lutea en luz brillante pero indirecta.\n" +
                "Riega de forma regular pero sin encharcar.\n" +
                "Fertiliza solo en época de crecimiento y con dosis bajas.\n" +
                "Evita cambios bruscos de temperatura y corrientes de aire.\n" +
                "Mantén el sustrato aireado y la maceta con buen drenaje.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'Las cicatrices en las hojas de Calathea lutea son marcas permanentes que aparecen tras un daño físico, ambiental o como consecuencia de plagas o enfermedades ya superadas. A diferencia de manchas activas, las cicatrices no se expanden ni presentan tejido blando o húmedo, y suelen afectar solo la estética, no la salud general de la planta.\n" +
                "\n" +
                "Síntomas\n" +
                "Presencia de marcas lineales, irregulares o zonas secas de color marrón claro, beige, blanquecino o grisáceo.\n" +
                "Textura seca o endurecida al tacto, nunca blanda ni pegajosa.\n" +
                "No hay crecimiento de la lesión: las cicatrices permanecen igual con el tiempo.\n" +
                "Pueden estar hundidas o en relieve dependiendo del tipo de daño.\n" +
                "A veces se acompañan de bordes ligeramente decolorados en la zona de la cicatriz.\n" +
                "No hay mal olor, ni zonas acuosas.\n" +
                "Causas\n" +
                "Daño físico o mecánico\n" +
                "Golpes o roces al mover la planta, manipulación brusca, hojas dobladas accidentalmente.\n" +
                "Daños durante el transporte, poda no cuidadosa, contacto con objetos duros o cortantes.\n" +
                "Quemaduras solares antiguas\n" +
                "Exposición breve a sol directo que quemó tejido, sobre todo en hojas jóvenes.\n" +
                "Se forman “parches” secos después de la quemadura.\n" +
                "Daños por plagas previas\n" +
                "Ácaros, trips u otros insectos que perforan o raspan la hoja.\n" +
                "Tras eliminar la plaga, la herida cicatriza y deja una marca permanente.\n" +
                "Daños por estrés ambiental\n" +
                "Cambios bruscos de temperatura o sequías puntuales que dañaron la hoja.\n" +
                "Cicatrices por enfermedades superadas\n" +
                "Manchas fúngicas o bacterianas antiguas que ya no están activas, pero dejaron una marca.','No requieren tratamiento especial, ya que la cicatriz es un daño cerrado y estable.\n" +
                "Recorte estético:\n" +
                "Si la marca es muy grande o antiestética y solo afecta una parte de la hoja, puedes recortar la zona dañada siguiendo el contorno natural de la hoja con tijeras desinfectadas.\n" +
                "Retiro de hoja completa:\n" +
                "Si la hoja tiene muchas cicatrices o está mayormente dañada y seca, puedes retirarla para que la planta centre su energía en hojas sanas.\n" +
                "Prevención:\n" +
                "Manipula la planta con cuidado durante cambios de maceta o limpieza.\n" +
                "Mantén la Calathea lutea alejada de zonas de paso o donde pueda recibir golpes.\n" +
                "Evita exponerla a sol directo para prevenir quemaduras.\n" +
                "Controla plagas desde sus primeras fases para evitar que dejen cicatrices.\n" +
                "Mantén una humedad ambiental estable y evita cambios bruscos de ambiente.\n" +
                "¿Cuándo preocuparse?\n" +
                "Solo si la cicatriz crece, cambia de color, se vuelve blanda/húmeda, o aparecen síntomas alrededor (mal olor, manchas nuevas), podría haber un problema activo (hongo, bacteria, plaga) y deberías revisar más a fondo.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'El envejecimiento es un proceso natural en todas las plantas, incluyendo la Calathea lutea. Cada hoja tiene un ciclo de vida: nace, crece, cumple su función y finalmente muere para dar paso a hojas nuevas y sanas. Este fenómeno no se asocia a enfermedades, plagas ni problemas de cuidado, siempre que ocurra de forma aislada y en hojas viejas.\n" +
                "\n" +
                "Síntomas\n" +
                "Afecta principalmente a las hojas más viejas, que suelen estar en la parte inferior o exterior de la planta.\n" +
                "Cambio de color:\n" +
                "Las hojas comienzan a perder color, pasando de verde intenso a tonos amarillentos, ocres o marrón claro.\n" +
                "Secado progresivo:\n" +
                "El secado inicia en la punta y en los bordes de la hoja, avanzando hacia el centro y la base.\n" +
                "Textura:\n" +
                "La hoja se vuelve cada vez más seca, quebradiza y arrugada.\n" +
                "Caída natural:\n" +
                "La hoja se desprende sola o con un suave tirón una vez está completamente seca.\n" +
                "No afecta a las hojas jóvenes ni al crecimiento general de la planta.\n" +
                "Causas\n" +
                "Es simplemente el ciclo vital de la hoja.\n" +
                "El envejecimiento puede acelerarse levemente por cambios bruscos de temperatura, trasplantes o adaptación a un nuevo ambiente, pero sigue siendo un proceso normal.','No requiere tratamiento ni productos especiales.\n" +
                "Retira las hojas completamente secas con tijeras limpias y desinfectadas para evitar acumulación de material muerto y mantener la estética.\n" +
                "En plantas de gran porte como la Calathea lutea, puedes dejar la hoja hasta que esté seca por completo, ya que la planta puede reutilizar algunos nutrientes antes de desprenderla.\n" +
                "No recortes hojas parcialmente secas si la mayor parte aún está verde y funcional.\n" +
                "Vigila que el número de hojas afectadas sea bajo y que siempre haya hojas nuevas creciendo. Si muchas hojas se amarillean rápidamente, podría haber otro problema (riego, humedad, luz, etc.).\n" +
                "Prevención y consejos\n" +
                "No te alarmes si de vez en cuando una hoja vieja se seca: es un signo de que la planta sigue su ciclo vital normal.\n" +
                "Mantén los cuidados generales óptimos para que el envejecimiento solo afecte a las hojas más viejas y no a toda la planta.\n" +
                "Aprovecha para revisar el estado general de la planta cada vez que retires hojas secas.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'El marchitamiento o secado de las puntas de las hojas es uno de los problemas más frecuentes en Calathea lutea, sobre todo en ambientes de interior. Es una señal de que las hojas están sufriendo estrés, generalmente por factores ambientales o de manejo. Si bien puede ser solo un problema estético, si no se corrige la causa puede llegar a avanzar por toda la hoja o afectar a nuevas hojas.\n" +
                "\n" +
                "Síntomas\n" +
                "Las puntas de las hojas se vuelven marrones, secas y quebradizas.\n" +
                "El tejido afectado está deshidratado y puede sentirse áspero al tacto.\n" +
                "En algunas ocasiones, la sequedad avanza desde la punta hacia los bordes laterales de la hoja.\n" +
                "El resto de la hoja suele mantener un aspecto saludable, especialmente si el problema es leve o reciente.\n" +
                "En casos severos, el marchitamiento puede extenderse más allá de la punta y afectar zonas más amplias.\n" +
                "La planta puede lucir menos vigorosa o con crecimiento más lento si el problema persiste mucho tiempo.\n" +
                "Causas\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Calathea lutea es una planta tropical que requiere humedad ambiental alta (idealmente 60-80%). El aire seco provoca que las puntas se deshidraten primero.\n" +
                "Uso de calefacción, aire acondicionado, o ambientes cerrados sin humidificador.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Falta de agua: el sustrato se seca demasiado entre riegos, lo que lleva a que la planta no pueda hidratar adecuadamente las hojas.\n" +
                "Exceso de agua: aunque menos común, puede dificultar la absorción de agua por pudrición radicular.\n" +
                "Calidad del agua\n" +
                "\n" +
                "Agua del grifo con exceso de cloro, sales o cal puede acumularse en el sustrato y dañar las raíces y puntas de las hojas.\n" +
                "Acumulación de sales/fertilizantes\n" +
                "\n" +
                "Fertilización excesiva o acumulación de sales minerales en el sustrato pueden causar quemadura en las puntas.\n" +
                "Corrientes de aire o cambios bruscos de temperatura\n" +
                "\n" +
                "Corrientes frías, aire de ventiladores o cambios de temperatura pueden estresar las hojas y provocar el secado de puntas.\n" +
                "Envejecimiento natural\n" +
                "\n" +
                "Las hojas más viejas son más propensas a mostrar puntas secas como parte de su ciclo de vida.\n',' Mejorar la humedad ambiental\n" +
                "\n" +
                "Usa un humidificador cerca de la planta para mantener la humedad por encima del 60%.\n" +
                "Agrupa la Calathea lutea con otras plantas para crear un microclima húmedo.\n" +
                "Coloca la maceta sobre una bandeja con piedras y agua (sin que la base toque el agua directamente).\n" +
                "Rocía el aire alrededor de la planta ocasionalmente (sin mojar en exceso las hojas).\n" +
                "2. Ajustar el riego\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado.\n" +
                "Deja secar la capa superficial (1-2 cm) del sustrato antes de volver a regar.\n" +
                "Usa siempre macetas con buen drenaje.\n" +
                "3. Mejorar la calidad del agua\n" +
                "\n" +
                "Riega con agua filtrada, destilada o de lluvia si es posible.\n" +
                "Si solo tienes agua de grifo, déjala reposar 24 horas para que se evapore el cloro.\n" +
                "4. Controlar la fertilización\n" +
                "\n" +
                "Fertiliza solo en primavera y verano, en dosis bajas, y nunca sobre sustrato seco.\n" +
                "Si sospechas exceso de sales, riega abundantemente para “lavar” el sustrato y elimina el agua sobrante.\n" +
                "5. Evitar corrientes de aire\n" +
                "\n" +
                "Coloca la planta lejos de ventanas abiertas, puertas, radiadores y aires acondicionados.\n" +
                "6. Recorte estético\n" +
                "\n" +
                "Si las puntas secas son muy notorias, recórtalas siguiendo la forma natural de la hoja usando tijeras limpias y desinfectadas.\n" +
                "No recortes la parte verde sana.\n" +
                "Prevención\n" +
                "Mantén la humedad y el riego constantes.\n" +
                "Usa agua de buena calidad y fertiliza con prudencia.\n" +
                "Coloca la Calathea lutea en un lugar protegido de corrientes y cambios bruscos de temperatura.\n" +
                "Observa regularmente tu planta para detectar a tiempo cualquier síntoma y actuar antes de que se generalice.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'Las orugas son las larvas de mariposas o polillas y pueden aparecer ocasionalmente en Calathea lutea, especialmente si la planta se mantiene en exterior, en patios, terrazas o jardines, o si se ventila frecuentemente en interior. Estas larvas son voraces y pueden causar daños notables en poco tiempo, ya que se alimentan masticando el tejido de las hojas.\n" +
                "\n" +
                "Síntomas\n" +
                "Hojas con agujeros irregulares:\n" +
                "Las orugas comen el tejido de las hojas, dejando orificios de bordes desiguales, a veces grandes y a veces pequeños, dependiendo del tamaño y especie de la oruga.\n" +
                "Mordidas en los bordes:\n" +
                "Puede observarse que los bordes de las hojas están “comidos” o presentan secciones faltantes.\n" +
                "Presencia visible de orugas:\n" +
                "En ocasiones puedes ver directamente a las orugas en el envés de las hojas, en los tallos o en el sustrato cercano.\n" +
                "Excrementos:\n" +
                "Pequeñas bolitas negras o marrón oscuro (frass) en las hojas, tallos o la base de la planta.\n" +
                "Hojas parcialmente enrolladas o dañadas:\n" +
                "Algunas orugas enrollan las hojas para refugiarse y alimentarse dentro.\n" +
                "Daño rápido:\n" +
                "Si no se actúa, los daños pueden aparecer de un día para otro y afectar varias hojas.\n" +
                "Causas\n" +
                "Exposición exterior:\n" +
                "La Calathea lutea ubicada en exteriores o cerca de ventanas abiertas es más propensa a que las mariposas o polillas depositen huevos en ella.\n" +
                "Ambiente húmedo y protegido:\n" +
                "Las orugas prefieren lugares protegidos, húmedos y con buena cantidad de follaje.\n" +
                "Plantas cercanas infestadas:\n" +
                "Si tienes otras plantas en el entorno, las orugas pueden migrar de una a otra.','Inspección visual regular:\n" +
                "Revisa cuidadosamente el envés de las hojas, tallos y base de la planta en busca de orugas, huevos o excrementos.\n" +
                "Eliminación manual:\n" +
                "Si encuentras orugas, retíralas manualmente con guantes (o con pinzas) y deséchalas lejos de la planta.\n" +
                "Limpieza:\n" +
                "Retira los excrementos y hojas muy dañadas, ya que pueden atraer más plagas o favorecer hongos.\n" +
                "Lava la planta:\n" +
                "Puedes lavar suavemente las hojas con agua para eliminar huevos y residuos.\n" +
                "Tratamiento biológico:\n" +
                "Utiliza Bacillus thuringiensis (Bt), un insecticida biológico específico para orugas, seguro para personas y mascotas. Pulveriza siguiendo las instrucciones del fabricante.\n" +
                "Insecticidas naturales:\n" +
                "Jabón potásico o aceite de neem pueden ayudar a repeler orugas jóvenes, pero no son tan efectivos como el Bt para eliminar las ya presentes.\n" +
                "Prevención:\n" +
                "Mantén las ventanas con mosquitera si tienes la planta en interior.\n" +
                "Inspecciona cualquier planta nueva antes de introducirla cerca de tu Calathea lutea.\n" +
                "Si el problema es recurrente, revisa también plantas vecinas.\n" +
                "Prevención a futuro\n" +
                "Realiza inspecciones semanales, sobre todo en primavera y verano.\n" +
                "Mantén la zona limpia y libre de restos vegetales en descomposición.\n" +
                "Si la planta está en exterior, revisa tras lluvias o períodos de viento, ya que estos favorecen la llegada de huevos y larvas.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'La Calathea lutea es conocida por sus grandes hojas y también por sus inflorescencias en forma de espiga, que suelen ser de color amarillo pálido, crema o verdoso, y pueden durar varias semanas. Sin embargo, es común que las flores se marchiten, especialmente cuando han completado su ciclo de vida o si la planta ha sufrido algún tipo de estrés. La aparición de flores marchitas puede deberse tanto a causas naturales como a problemas de cultivo.\n" +
                "\n" +
                "Síntomas\n" +
                "Las flores, inicialmente firmes y coloridas, comienzan a perder color y vitalidad.\n" +
                "Los pétalos o estructuras florales se tornan blandos, translúcidos, marrones o se secan.\n" +
                "La flor puede colgarse, caerse o descomponerse parcialmente.\n" +
                "A veces, la base de la espiga floral se vuelve blanda o presenta mal olor si hay humedad excesiva.\n" +
                "El resto de la planta (hojas y tallos) puede lucir sana, pero la inflorescencia está claramente deteriorada.\n" +
                "Causas\n" +
                "Ciclo natural de la flor\n" +
                "\n" +
                "Las flores de Calathea lutea tienen una vida limitada y, tras unos días o semanas, se marchitan de forma natural.\n" +
                "Después de la polinización, la flor pierde su función y la planta centra su energía en el desarrollo de hojas y raíces.\n" +
                "Falta o exceso de agua\n" +
                "\n" +
                "Un riego inadecuado puede acelerar el marchitamiento de la flor.\n" +
                "Exceso de agua puede provocar pudrición en la base floral.\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "La sequedad ambiental puede hacer que las flores se sequen y marchiten antes de tiempo.\n" +
                "Estrés por temperatura\n" +
                "\n" +
                "Exposición a corrientes de aire frío, calor excesivo o cambios bruscos de temperatura pueden afectar la duración de la flor.\n" +
                "Falta de nutrientes\n" +
                "\n" +
                "Si la planta no tiene acceso a suficientes nutrientes, las flores pueden no desarrollarse plenamente y marchitarse más rápido.\n" +
                "Ataque de hongos o bacterias\n" +
                "\n" +
                "En ambientes muy húmedos y poco ventilados, la flor puede pudrirse o mostrar manchas marrones/negras y mal olor.\n','Retira flores marchitas\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas para cortar la flor marchita desde la base del tallo floral.\n" +
                "Esto mejora la estética y previene la aparición de hongos en restos vegetales en descomposición.\n" +
                "Ajusta el riego\n" +
                "\n" +
                "Mantén el sustrato húmedo pero nunca encharcado.\n" +
                "Evita mojar directamente la base de la flor o dejar agua estancada en la roseta.\n" +
                "Mantén la humedad ambiental alta\n" +
                "\n" +
                "Usa humidificador, bandejas de agua o agrupa las plantas.\n" +
                "Aporta nutrientes en época de floración\n" +
                "\n" +
                "Un abono equilibrado durante la primavera-verano favorecerá una floración más duradera y saludable.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Evita ambientes cerrados y mal ventilados, sobre todo si la flor muestra signos de pudrición.\n" +
                "Revisa la planta\n" +
                "\n" +
                "Si hay mal olor, manchas blandas o negras, revisa que no haya hongos o bacterias y considera usar un fungicida adecuado.\n" +
                "Prevención\n" +
                "Mantén los cuidados constantes: riego controlado, buena humedad ambiental, luz filtrada y abono regular.\n" +
                "Retira siempre flores y hojas marchitas para evitar plagas y enfermedades.\n" +
                "Observa el ciclo natural de tu Calathea lutea y no te alarmes si la flor se marchita tras varias semanas: es parte de la vida normal de la planta.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (230,'Las quemaduras de hojas en Calathea lutea son daños visibles en el tejido foliar causados principalmente por exposición a luz solar directa o a fuentes de calor intenso. Esta especie, nativa de ambientes húmedos y sombreados, es especialmente sensible a la radiación solar intensa, que puede provocar lesiones rápidas y a veces irreversibles en sus hojas grandes y decorativas.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas marrones, blanquecinas o amarillas en la superficie de la hoja, especialmente en las zonas más expuestas a la luz.\n" +
                "Bordes de las hojas secos, quebradizos o con aspecto “crujiente”.\n" +
                "Áreas translúcidas o descoloridas en el centro o bordes de la hoja.\n" +
                "Hojas que pierden su brillo natural y aparecen opacas o con textura áspera.\n" +
                "En casos severos, la hoja se seca parcial o totalmente y puede caer antes de tiempo.\n" +
                "Las quemaduras aparecen de manera rápida, a veces en cuestión de horas tras la exposición.\n" +
                "Causas\n" +
                "Exposición a luz solar directa\n" +
                "La causa más común. Calathea lutea tolera luz intensa, pero siempre filtrada o indirecta. El sol directo, especialmente en las horas centrales del día, quema el tejido foliar.\n" +
                "Reflejo de luz a través de cristales\n" +
                "Ventanas sin cortinas, tragaluces o reflejos pueden concentrar la luz y dañar la hoja.\n" +
                "Proximidad a fuentes de calor\n" +
                "Calefactores, radiadores o lámparas muy cercanas pueden causar quemaduras localizadas.\n" +
                "Riego en horas de sol\n" +
                "Si caen gotas de agua sobre la hoja y la planta está al sol, el efecto lupa de las gotas puede intensificar la quemadura.\n" +
                "Cambios bruscos de ubicación\n" +
                "Mover la planta de un lugar sombreado a uno muy luminoso sin aclimatación.','Retira las hojas muy dañadas\n" +
                "\n" +
                "Si la quemadura afecta solo una parte, recorta la zona quemada siguiendo el contorno natural de la hoja con tijeras limpias y desinfectadas.\n" +
                "Si la hoja está muy afectada, corta toda la hoja por la base para evitar infecciones y desviar la energía de la planta a hojas sanas.\n" +
                "Reubica la planta\n" +
                "\n" +
                "Coloca la Calathea lutea en un lugar con luz brillante pero filtrada, nunca sol directo.\n" +
                "Usa cortinas translúcidas, estores o coloca la planta a varios metros de la ventana si la orientación es sur/este.\n" +
                "Evita fuentes de calor directo\n" +
                "\n" +
                "Mantén la planta alejada de radiadores, estufas y lámparas intensas.\n" +
                "Riega en las horas adecuadas\n" +
                "\n" +
                "Evita mojar las hojas durante las horas de máxima luz y calor.\n" +
                "Si es necesario limpiar las hojas, hazlo en la mañana temprano o al atardecer.\n" +
                "Aclimata progresivamente\n" +
                "\n" +
                "Si vas a cambiar la planta de sitio, hazlo gradualmente, aumentando poco a poco la exposición a la luz.\n" +
                "Prevención\n" +
                "La mejor prevención es evitar la exposición directa al sol, especialmente en primavera y verano.\n" +
                "Mantén la humedad ambiental alta para que las hojas sean más resistentes.\n" +
                "Revisa las condiciones de luz en distintos momentos del día para asegurarte de que la planta no reciba rayos directos.\n" +
                "Si usas lámparas de crecimiento, asegúrate de que sean aptas para plantas de sombra y no estén demasiado cerca.\n" +
                "¿Cuándo preocuparse o consultar más?\n" +
                "Si varias hojas muestran quemaduras repentinamente, revisa la orientación y la intensidad de la luz donde está ubicada la planta.\n" +
                "Si después de ajustar la ubicación siguen apareciendo quemaduras, podría haber un problema de riego, ventilación o temperatura.\n" +
                "Si tienes dudas sobre si una mancha es quemadura o enfermedad, puedes enviar una foto o describirla con más detalle.',21)")

        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'Las manchas marrones en Calathea makoyana son un síntoma frecuente y pueden indicar varias causas, desde factores ambientales hasta enfermedades o errores de riego. Identificar el tipo, ubicación y aspecto de la mancha es clave para diagnosticar el problema y aplicar la solución adecuada.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas marrones de diferentes tamaños y formas, secas o húmedas.\n" +
                "Pueden estar en puntas, bordes o el centro de la hoja.\n" +
                "A veces acompañadas de halo amarillo o decoloración alrededor.\n" +
                "El tejido afectado puede estar seco, quebradizo, o, si es una infección, blando y húmedo.\n" +
                "En casos severos, la mancha se expande y afecta grandes zonas de la hoja, que puede terminar secándose y cayendo.\n" +
                "Causas y cómo diferenciarlas\n" +
                "1. Humedad ambiental baja\n" +
                "Manchas marrones secas en puntas y bordes.\n" +
                "Hojas pueden verse enrolladas o arrugadas.\n" +
                "Ocurre más en invierno o con calefacción/aire acondicionado.\n" +
                "Solución: Eleva la humedad ambiental (humidificador, bandeja con agua y guijarros, agrupa plantas).\n" +
                "2. Riego inadecuado\n" +
                "Falta de agua: Manchas secas en bordes y puntas, hojas lacias.\n" +
                "Exceso de agua: Manchas marrones oscuras, a menudo húmedas y blandas; puede haber mal olor.\n" +
                "Solución: Mantén el sustrato ligeramente húmedo, nunca encharcado. Usa maceta con buen drenaje.\n" +
                "3. Quemadura solar\n" +
                "Manchas marrones o blanquecinas, a veces translúcidas, en zonas expuestas al sol directo.\n" +
                "El daño aparece rápido tras exposición a luz intensa.\n" +
                "Solución: Coloca la planta en luz brillante filtrada, nunca sol directo.\n" +
                "4. Exceso de fertilizante\n" +
                "Manchas marrones irregulares, bordes quemados, a veces costras blancas en el sustrato.\n" +
                "Solución: Suspende abono, riega para “lavar” sales y reduce dosis en el futuro.\n" +
                "5. Enfermedades fúngicas o bacterianas\n" +
                "Manchas marrones con borde amarillo, húmedas, que pueden crecer rápido.\n" +
                "Tejido blando, a veces con mal olor o moho.\n" +
                "Solución: Retira hojas afectadas, mejora ventilación y aplica fungicida si se extiende.\n" +
                "6. Daño físico\n" +
                "Manchas marrones secas en zonas dobladas, cortadas o golpeadas.\n" +
                "No crecen ni cambian con el tiempo.\n" +
                "Solución: Solo recorta por estética.','Observa la ubicación y aspecto de la mancha (seca/húmeda, punta/borde/centro, halo…).\n" +
                "Revisa humedad ambiental y riego.\n" +
                "Observa la exposición a la luz.\n" +
                "Inspecciona por exceso de abono o sustrato compactado.\n" +
                "Descarta plagas (menos frecuente, pero revisa el envés y tallos).\n" +
                "Retira hojas muy afectadas y ajusta el cuidado según la causa.\n" +
                "Prevención\n" +
                "Mantén humedad ambiental alta (60-80%).\n" +
                "Usa agua filtrada o de lluvia.\n" +
                "Luz brillante pero siempre filtrada.\n" +
                "Fertiliza solo en primavera-verano y en baja dosis.\n" +
                "Vigila el sustrato: aireado y con buen drenaje.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'La Calathea makoyana es una planta tropical que requiere altos niveles de humedad ambiental (idealmente entre 60% y 80%) para mantenerse sana y lucir su característico follaje decorativo. La baja humedad es una de las causas más frecuentes de problemas en esta especie, especialmente en interiores y durante temporadas de calefacción o aire acondicionado.\n" +
                "\n" +
                "Síntomas de baja humedad\n" +
                "Puntas y bordes marrones:\n" +
                "Las puntas y los bordes de las hojas se tornan marrones, secas y quebradizas. Es el síntoma más típico.\n" +
                "Hojas arrugadas o enrolladas:\n" +
                "Las hojas pueden empezar a enrollarse hacia adentro o a mostrar un aspecto “arrugado”.\n" +
                "Desecación general:\n" +
                "El follaje pierde brillo, las hojas parecen apagadas y menos turgentes.\n" +
                "Crecimiento lento:\n" +
                "La planta puede ralentizar su crecimiento y sacar hojas nuevas más pequeñas o deformes.\n" +
                "Aumento de plagas:\n" +
                "El ambiente seco favorece la aparición de ácaros, que prefieren condiciones de baja humedad.\n" +
                "Causas\n" +
                "Uso de calefacción o aire acondicionado.\n" +
                "Clima seco, especialmente en invierno.\n" +
                "Ubicación en habitaciones poco ventiladas o sin otras plantas alrededor.\n" +
                "No usar métodos de humidificación.','1. Humidificador:\n" +
                "\n" +
                "Es la forma más efectiva y estable de elevar la humedad. Sitúa el humidificador cerca de la planta y mantén la humedad por encima del 60%.\n" +
                "2. Bandeja con guijarros y agua:\n" +
                "\n" +
                "Coloca la maceta sobre una bandeja con piedras y agua, asegurándote de que el fondo de la maceta no toque el agua directamente.\n" +
                "3. Agrupa plantas:\n" +
                "\n" +
                "Juntar varias plantas crea un microclima local más húmedo.\n" +
                "4. Rociado ocasional:\n" +
                "\n" +
                "Pulveriza ligeramente el aire alrededor de la planta (no la hoja directamente) con agua tibia, especialmente en días secos. Hazlo por la mañana para evitar hongos.\n" +
                "5. Evita corrientes de aire seco:\n" +
                "\n" +
                "Aleja la Calathea makoyana de radiadores, aires acondicionados y corrientes de aire caliente o frío.\n" +
                "6. Usa agua de calidad:\n" +
                "\n" +
                "Riega y humedece solo con agua filtrada o de lluvia, ya que el agua con cal o cloro puede agravar los daños en las hojas.\n" +
                "Consejos adicionales\n" +
                "Controla la humedad con un higrómetro cerca de la planta.\n" +
                "Si las puntas marrones ya aparecieron, puedes recortarlas con tijeras limpias siguiendo la forma natural de la hoja.\n" +
                "Mantén el sustrato húmedo pero nunca encharcado, pues el exceso de riego puede traer otros problemas.',48)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'El envejecimiento es un proceso natural y esperado en la Calathea makoyana. Cada hoja tiene un ciclo vital: nace, crece, cumple su función y, finalmente, muere para dar paso a hojas nuevas y más vigorosas. El envejecimiento NO indica enfermedad ni mal cuidado si ocurre de manera aislada y en hojas viejas.\n" +
                "\n" +
                "Síntomas del envejecimiento natural\n" +
                "Afecta principalmente hojas viejas (las de la parte externa o inferior de la planta).\n" +
                "Cambio de color:\n" +
                "Las hojas pierden el verde brillante y se tornan amarillas, ocres o marrón claro.\n" +
                "Secado progresivo:\n" +
                "El secado suele comenzar en la punta y bordes de la hoja, avanzando lentamente hacia el centro y el pecíolo.\n" +
                "Textura:\n" +
                "La hoja se vuelve seca, quebradiza y, a veces, arrugada.\n" +
                "Caída natural:\n" +
                "La hoja completamente seca se desprende sola o con un leve tirón.\n" +
                "No afecta hojas nuevas ni el crecimiento general de la planta.\n" +
                "Causas\n" +
                "Ciclo vital normal:\n" +
                "Es el motivo principal. Cada hoja tiene una vida útil y, tras meses de servicio, se va secando y muere.\n" +
                "Factores ambientales:\n" +
                "Cambios de estación, trasplante o adaptación a un nuevo ambiente pueden acelerar ligeramente el envejecimiento de hojas viejas, pero no deben afectar a las jóvenes.','No requiere tratamiento.\n" +
                "El envejecimiento es natural y no hay que preocuparse.\n" +
                "Retira hojas completamente secas con tijeras limpias y desinfectadas para mejorar la apariencia y prevenir hongos.\n" +
                "Si la hoja está parcialmente seca, puedes recortar solo la parte marrón siguiendo la silueta natural, pero solo si la mayor parte sigue verde y funcional.\n" +
                "No retires hojas verdes o parcialmente sanas: la planta todavía puede aprovechar nutrientes de ellas.\n" +
                "Prevención y buenas prácticas\n" +
                "Mantén los cuidados óptimos (humedad alta, riego regular, luz filtrada) para que el envejecimiento afecte solo a hojas viejas.\n" +
                "Si muchas hojas se secan a la vez o aparecen síntomas en hojas jóvenes, revisa que no haya problemas de riego, luz, humedad o plagas.\n" +
                "Aprovecha la retirada de hojas viejas para revisar el estado general de la planta y limpiar el sustrato.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'El secado o marchitamiento de las puntas de las hojas es uno de los problemas más frecuentes en Calathea makoyana. Aunque no suele ser grave, es un indicador de que la planta está sufriendo algún tipo de estrés ambiental o de manejo, generalmente relacionado con la humedad, el riego o la calidad del agua.\n" +
                "\n" +
                "Síntomas\n" +
                "Las puntas de las hojas se vuelven marrones, secas y quebradizas.\n" +
                "El tejido afectado puede estar deshidratado y áspero al tacto.\n" +
                "En casos más avanzados, la sequedad puede expandirse a los bordes laterales.\n" +
                "Las partes verdes de la hoja permanecen sanas, aunque si el problema persiste, el daño puede avanzar.\n" +
                "El crecimiento nuevo suele salir sano si la causa se corrige.\n" +
                "Causas Principales\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Es la causa más frecuente. Calathea makoyana necesita ambientes húmedos (60-80%).\n" +
                "Calefacción, aire acondicionado o clima seco favorecen este daño.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Falta de agua: las raíces no logran hidratar bien las hojas.\n" +
                "Exceso de agua: puede causar problemas radiculares que se reflejan en las puntas.\n" +
                "Agua de mala calidad\n" +
                "\n" +
                "Agua con cloro, sales o cal daña las raíces y se manifiesta en las puntas secas.\n" +
                "Acumulación de fertilizantes o sales\n" +
                "\n" +
                "El exceso de abono o sales en el sustrato puede “quemar” las puntas.\n" +
                "Corrientes de aire seco\n" +
                "\n" +
                "Corrientes frías o calientes, ventiladores o proximidad a ventanas abiertas.\n" +
                "Envejecimiento natural de la hoja\n" +
                "\n" +
                "Las hojas viejas a veces muestran puntas secas antes de caer.','Aumenta la humedad ambiental\n" +
                "\n" +
                "Usa humidificador, agrupa plantas, coloca bandejas con agua y guijarros.\n" +
                "Ajusta el riego\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, no encharcado. Deja secar la capa superficial antes de volver a regar.\n" +
                "Usa agua adecuada\n" +
                "\n" +
                "Riega con agua filtrada, destilada o de lluvia. Si usas agua de grifo, déjala reposar 24h para evaporar el cloro.\n" +
                "Reduce fertilización\n" +
                "\n" +
                "Fertiliza solo en primavera-verano y en dosis bajas. Si sospechas acumulación de sales, riega abundantemente para lavar el sustrato.\n" +
                "Evita corrientes de aire\n" +
                "\n" +
                "Coloca la planta en un sitio protegido de corrientes y cambios bruscos de temperatura.\n" +
                "Recorte estético\n" +
                "\n" +
                "Puedes recortar las puntas secas con tijeras limpias siguiendo la forma natural de la hoja. No cortes la parte verde.\n" +
                "Prevención\n" +
                "Controla la humedad ambiental con un higrómetro.\n" +
                "Mantén una rutina de riego y fertilización adecuada.\n" +
                "No expongas la planta a cambios de temperatura ni corrientes.\n" +
                "Usa siempre agua de buena calidad.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'Las cicatrices en las hojas de Calathea makoyana son marcas permanentes que aparecen como resultado de daños previos en el tejido foliar. Estas marcas no suelen representar un problema activo, sino que son el “recuerdo” visible de algún factor de estrés, daño físico, plaga, enfermedad o manejo inadecuado ocurrido en el pasado.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas o líneas marrones, grisáceas o blanquecinas, con bordes definidos.\n" +
                "Áreas de la hoja con aspecto “hundido”, engrosado o diferente textura.\n" +
                "Pueden aparecer en cualquier parte de la hoja, pero son más frecuentes en bordes o zonas donde hubo heridas.\n" +
                "No se expanden ni cambian de tamaño con el tiempo.\n" +
                "El resto de la hoja puede lucir sano, y el crecimiento nuevo aparece normal si la causa ha sido corregida.\n" +
                "Causas comunes de cicatrices\n" +
                "Daño físico\n" +
                "Golpes al mover la planta, roces con objetos, doblado de hojas, cortes accidentales.\n" +
                "Daños por plagas\n" +
                "Ataques pasados de trips, ácaros, orugas o cochinillas pueden dejar cicatrices tras curar el tejido.\n" +
                "Quemaduras solares o químicas\n" +
                "Exposición al sol directo o contacto con fertilizante, limpiadores o agua muy caliente.\n" +
                "Heridas por recorte\n" +
                "Cortes o podas mal ejecutadas pueden dejar bordes cicatrizados.\n" +
                "Enfermedades superadas\n" +
                "Infecciones fúngicas o bacterianas que sanaron pueden dejar una marca permanente.\n" +
                "¿Debo preocuparme?\n" +
                "Si la cicatriz es estática y el resto de la hoja crece sano, no es un problema activo.\n" +
                "Si aparecen cicatrices en hojas nuevas, hay que buscar una causa activa (plaga, luz, riego, etc.).\n" +
                "¿Qué hacer con las cicatrices?\n" +
                "No hay manera de eliminar una cicatriz ya formada.\n" +
                "Si la marca es muy antiestética y la hoja está mayormente dañada, puedes recortarla.\n" +
                "Mantén los cuidados óptimos para evitar nuevos daños:\n" +
                "Buena humedad ambiental.\n" +
                "Riego adecuado.\n" +
                "Luz filtrada.\n" +
                "Manipulación cuidadosa.\n" +
                "Revisiones regulares para descartar plagas.\n" +
                "Prevención\n" +
                "Manipula la Calathea makoyana con cuidado al limpiar, trasplantar o mover.\n" +
                "Manténla lejos de zonas de paso o donde pueda recibir golpes.\n" +
                "No expongas a sol directo ni a productos químicos agresivos.\n" +
                "Usa siempre tijeras limpias y afiladas para podar.','No hay manera de eliminar una cicatriz ya formada.\n" +
                "Si la marca es muy antiestética y la hoja está mayormente dañada, puedes recortarla.\n" +
                "Mantén los cuidados óptimos para evitar nuevos daños:\n" +
                "Buena humedad ambiental.\n" +
                "Riego adecuado.\n" +
                "Luz filtrada.\n" +
                "Manipulación cuidadosa.\n" +
                "Revisiones regulares para descartar plagas.\n" +
                "Prevención\n" +
                "Manipula la Calathea makoyana con cuidado al limpiar, trasplantar o mover.\n" +
                "Manténla lejos de zonas de paso o donde pueda recibir golpes.\n" +
                "No expongas a sol directo ni a productos químicos agresivos.\n" +
                "Usa siempre tijeras limpias y afiladas para podar.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'La pudrición de la hoja es un problema serio en Calathea makoyana, generalmente causado por exceso de humedad, mal drenaje, o infecciones fúngicas/bacterianas. Se caracteriza por áreas blandas, húmedas, oscuras y a menudo malolientes en las hojas, que pueden extenderse rápidamente y afectar la salud general de la planta.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas oscuras, húmedas y blandas en cualquier parte de la hoja (más común en base o zonas bajas).\n" +
                "Mal olor proveniente de la hoja o la base.\n" +
                "Tejido que se descompone fácilmente al tocarlo.\n" +
                "Hojas que se caen con facilidad, a veces con el pecíolo blando.\n" +
                "Avance rápido: la mancha crece en pocos días y puede afectar hojas cercanas.\n" +
                "Si la pudrición es severa, puede extenderse al tallo o raíces.\n" +
                "Causas principales\n" +
                "Exceso de riego\n" +
                "El sustrato permanece empapado, privando a las raíces de oxígeno.\n" +
                "Mal drenaje\n" +
                "Maceta sin agujeros, sustrato compacto o que retiene mucha agua.\n" +
                "Ambiente demasiado húmedo y poco ventilado\n" +
                "Favorece la proliferación de hongos y bacterias.\n" +
                "Infección fúngica o bacteriana\n" +
                "Patógenos como Pythium, Phytophthora, Fusarium, Erwinia, etc.\n" +
                "Salpicaduras frecuentes de agua en hojas\n" +
                "Sobre todo si el ambiente es frío o la hoja nunca se seca del todo.','Aísla la planta\n" +
                "\n" +
                "Evita el contacto con otras plantas para prevenir contagio.\n" +
                "Retira hojas afectadas\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas (alcohol o llama) para cortar hojas y tallos blandos o podridos. Corta siempre en zona sana.\n" +
                "Revisa raíces y base\n" +
                "\n" +
                "Si el problema es grave, saca la planta de la maceta y revisa raíces: elimina todas las partes blandas, negras o malolientes.\n" +
                "Cambia el sustrato\n" +
                "\n" +
                "Si el sustrato está empapado o huele mal, reemplázalo por uno nuevo, aireado y con buen drenaje.\n" +
                "Desinfecta la maceta\n" +
                "\n" +
                "Lava con agua caliente y jabón, o usa una solución de lejía suave (10%).\n" +
                "Reduce el riego\n" +
                "\n" +
                "Riega solo cuando la capa superficial esté seca al tacto (2-3 cm). No dejes agua en el plato bajo la maceta.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Coloca la planta en un sitio con buena circulación de aire (pero sin corrientes frías).\n" +
                "Fungicida/Bactericida\n" +
                "\n" +
                "Si la pudrición fue extensa o recurrente, aplica un fungicida sistémico o bactericida específico siguiendo las instrucciones del producto.\n" +
                "Prevención\n" +
                "Usa siempre macetas con drenaje y sustrato aireado.\n" +
                "Riega con moderación y adapta la frecuencia según la estación del año.\n" +
                "Evita mojar las hojas y la base frecuentemente.\n" +
                "Mantén buena ventilación, sobre todo en ambientes muy húmedos.\n" +
                "Desinfecta herramientas antes de podar o recortar.\n" +
                "¿Cuándo preocuparse?\n" +
                "Si la pudrición avanza muy rápido o afecta varios tallos, es necesario actuar de inmediato para salvar la planta.\n" +
                "Si el problema persiste tras aplicar los consejos, podrías enviar una foto para diagnóstico más preciso.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'Los puntos negros en las hojas de Calathea makoyana suelen ser pequeñas manchas oscuras, redondeadas y bien delimitadas, que pueden aparecer aisladas o en grupos. Su presencia indica generalmente un problema de ambiente, riego, enfermedad o plagas.\n" +
                "\n" +
                "Síntomas\n" +
                "Puntos negros pequeños, circulares y definidos.\n" +
                "Pueden estar en el haz o el envés de la hoja.\n" +
                "A veces rodeados por un halo amarillento o marrón claro.\n" +
                "El tejido alrededor de los puntos puede verse sano o mostrar signos de estrés.\n" +
                "Si el problema progresa, los puntos pueden expandirse o unirse, formando manchas más grandes.\n" +
                "Principales causas\n" +
                "Enfermedad fúngica (mancha foliar)\n" +
                "\n" +
                "Diversos hongos pueden causar puntos negros (Ej: Cercospora, Phyllosticta).\n" +
                "Suelen aparecer tras exceso de humedad, mala ventilación o riego sobre las hojas.\n" +
                "Pueden tener halo amarillo/marrón alrededor.\n" +
                "Solución: Retira hojas afectadas, mejora ventilación, evita mojar las hojas, aplica fungicida si avanza.\n" +
                "Plagas (cochinillas, trips, ácaros)\n" +
                "\n" +
                "Algunas plagas dejan puntos negros como excrementos o lesiones.\n" +
                "Busca signos de insectos en el envés de la hoja o cerca de los puntos.\n" +
                "Solución: Inspecciona bien, limpia hojas, usa insecticida específico de ser necesario.\n" +
                "Gotas de agua y sol directo\n" +
                "\n" +
                "El agua en las hojas bajo luz intensa puede causar quemaduras puntuales, que se ven como puntos negros.\n" +
                "Solución: No mojes las hojas ni expongas a sol directo.\n" +
                "Daño físico\n" +
                "\n" +
                "Golpes, roces o heridas pequeñas pueden dejar pequeños puntos oscuros tras cicatrizar.\n" +
                "No crecen ni se expanden.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "Puede causar necrosis puntual (puntos negros) en el tejido foliar.','Observa cuántos puntos hay, si crecen, si están secos/húmedos, y si hay halo o mal olor.\n" +
                "Revisa el envés de la hoja y tallos en busca de plagas.\n" +
                "Retira con tijeras limpias las hojas muy afectadas.\n" +
                "Asegura buena ventilación y evita mojar el follaje.\n" +
                "Si hay varios puntos nuevos cada semana, aplica un fungicida apto para plantas de interior (ej: a base de cobre o extracto de cola de caballo).\n" +
                "Revisa tus rutinas de riego y fertilización.\n" +
                "Prevención\n" +
                "Mantén humedad ambiental alta, pero evita el estancamiento y la falta de ventilación.\n" +
                "Riega el sustrato, no las hojas.\n" +
                "Usa agua filtrada.\n" +
                "Fertiliza solo en dosis bajas.\n" +
                "Inspecciona la planta regularmente.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'El oídio es una enfermedad fúngica causada por varios hongos (género Erysiphe, Oidium, entre otros) que afecta a muchas plantas ornamentales y de interior, incluida la Calathea makoyana. Se reconoce fácilmente por su aspecto de “polvo blanco” o grisáceo sobre las hojas.\n" +
                "\n" +
                "Síntomas en Calathea makoyana\n" +
                "Manchas blancas o grisáceas en el haz de las hojas, como si estuvieran espolvoreadas con harina.\n" +
                "Polvillo que se puede retirar parcialmente al frotar suavemente la hoja.\n" +
                "En fases avanzadas, las manchas se extienden, cubren gran parte de la hoja y pueden volverse amarillentas o marrones por debajo.\n" +
                "Hojas deformadas o debilitadas, en casos graves.\n" +
                "Crecimiento más lento y hojas jóvenes afectadas.\n" +
                "Causas y condiciones favorables\n" +
                "Alta humedad ambiental pero con poca ventilación.\n" +
                "Temperaturas moderadas (15-25 °C).\n" +
                "Hojas mojadas por mucho tiempo.\n" +
                "Plantas muy juntas o en espacios cerrados.\n" +
                "Débil circulación de aire.\n','1. Aísla la planta afectada.\n" +
                "Evita el contacto con otras plantas para frenar la propagación.\n" +
                "\n" +
                "2. Elimina hojas muy afectadas.\n" +
                "Usa tijeras limpias y desinfectadas para retirar las partes con mucho oídio.\n" +
                "\n" +
                "3. Mejora la ventilación.\n" +
                "Coloca la Calathea en un lugar con buena circulación de aire, pero evita corrientes frías o secas.\n" +
                "\n" +
                "4. Evita mojar el follaje.\n" +
                "Riega solo el sustrato, nunca las hojas.\n" +
                "\n" +
                "5. Aplica un fungicida adecuado:\n" +
                "\n" +
                "Fungicidas a base de azufre o cobre (siempre siguiendo instrucciones y probando en una sola hoja antes de tratar toda la planta).\n" +
                "Alternativa casera: diluye 1 cucharadita de bicarbonato de sodio en 1 litro de agua con una gota de detergente y pulveriza solo el área afectada (prueba en una hoja antes).\n" +
                "Repite el tratamiento semanalmente hasta que desaparezcan los síntomas.\n" +
                "6. Mantén la humedad alta pero nunca encharques ni mojes las hojas.\n" +
                "\n" +
                "Prevención\n" +
                "Buena ventilación sin corrientes de aire bruscas.\n" +
                "Evita el hacinamiento de plantas.\n" +
                "Riega por la base, nunca por aspersión.\n" +
                "Limpia regularmente las hojas con paño seco o apenas húmedo.\n" +
                "¿Cuándo preocuparte?\n" +
                "Si el oídio avanza rápido o afecta brotes nuevos, es importante intensificar los tratamientos y revisar el ambiente.\n" +
                "Si la planta muestra debilidad, puedes combinar el tratamiento con un bioestimulante suave.\n',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'La clorosis venal es un síntoma donde el tejido entre las venas de la hoja se vuelve amarillo (clorótico), mientras que las venas permanecen verdes. En Calathea makoyana, esto resalta especialmente debido al contraste natural de sus hojas. Es señal de que la planta está atravesando un problema de nutrición, riego o raíces.\n" +
                "\n" +
                "Síntomas\n" +
                "Coloración amarilla entre las venas (“malla” amarilla, venas verdes).\n" +
                "Las venas resaltan, a veces parecen más oscuras por el contraste.\n" +
                "Puede afectar hojas jóvenes o viejas, según la causa.\n" +
                "En casos severos, el tejido clorótico se seca o la hoja entera se debilita.\n" +
                "Causas principales\n" +
                "Deficiencia de hierro (clorosis férrica)\n" +
                "\n" +
                "La causa más frecuente: el hierro no está disponible o absorbible por la planta.\n" +
                "Ocurre sobre todo en suelos alcalinos o con exceso de calcio.\n" +
                "Deficiencia de otros micronutrientes\n" +
                "\n" +
                "Manganeso, zinc y magnesio también pueden causar clorosis venal (con matices distintos).\n" +
                "pH del sustrato demasiado alto\n" +
                "\n" +
                "Impide la absorción de hierro y otros micronutrientes.\n" +
                "Riego con agua dura (rica en sales, cal o cloro)\n" +
                "\n" +
                "Provoca acumulación de minerales y bloqueo de nutrientes.\n" +
                "Problemas de raíces\n" +
                "\n" +
                "Raíces dañadas por exceso de riego, compactación, pudrición o sustrato degradado no pueden absorber bien nutrientes.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "El exceso de sales puede bloquear la absorción de hierro y otros micronutrientes.','1. Revisa el agua de riego\n" +
                "\n" +
                "Usa agua filtrada, destilada o de lluvia. Evita agua con mucha cal o sal.\n" +
                "2. Comprueba el pH del sustrato\n" +
                "\n" +
                "El pH ideal debe ser ligeramente ácido (5,5–6,5). Si sospechas que el sustrato es alcalino, trasplanta a uno nuevo adecuado para plantas tropicales.\n" +
                "3. Aplica quelato de hierro\n" +
                "\n" +
                "Aplica un fertilizante de hierro quelatado siguiendo las instrucciones del producto, para corregir rápidamente la carencia.\n" +
                "4. Revisa el estado de las raíces\n" +
                "\n" +
                "Si hay signos de pudrición, compactación o raíces marrones/negras, trasplanta y elimina partes dañadas.\n" +
                "5. Evita el exceso de abono\n" +
                "\n" +
                "Suspende fertilizaciones hasta que la planta se recupere, y luego fertiliza solo con dosis bajas y productos equilibrados.\n" +
                "6. Aumenta la humedad ambiental\n" +
                "\n" +
                "La clorosis puede agravarse con ambiente muy seco.\n" +
                "Prevención\n" +
                "Usa siempre agua de calidad y controla la frecuencia de riego.\n" +
                "Fertiliza solo en primavera-verano, con productos específicos para plantas de interior.\n" +
                "Reemplaza el sustrato cada 1-2 años para evitar acumulación de sales.\n" +
                "Mantén la humedad ambiental alta y evita cambios bruscos de temperatura.',51)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (240,'Principales especies que afectan a Calathea makoyana\n" +
                "Pulgones\n" +
                "Pequeños, blandos, de color verde, negro, amarillo o marrón, agrupados en brotes y envés de hojas.\n" +
                "Cochinillas\n" +
                "Aspecto algodonoso, ceroso o en forma de costras marrones/blancas en tallos y hojas.\n" +
                "Trips\n" +
                "Muy pequeños, alargados, de color oscuro o traslúcido, se mueven rápido y dejan manchas plateadas o punteado en las hojas.\n" +
                "Ácaros (araña roja)\n" +
                "Tienen aspecto de minúsculos puntos rojos/naranjas, generan telarañas finas, especialmente en el envés de las hojas.\n" +
                "Mosca blanca\n" +
                "Pequeños insectos blancos que vuelan al sacudir la planta.\n" +
                "Síntomas de infestación\n" +
                "Hojas con manchas amarillas, plateadas o punteadas.\n" +
                "Enrollamiento, deformación o caída prematura de hojas.\n" +
                "Presencia de melaza pegajosa (excreción de los insectos) y, a veces, desarrollo de moho negro (“fumagina”).\n" +
                "Insectos visibles en brotes, envés de hojas, axilas o tallos.\n" +
                "Crecimiento debilitado y aspecto general decaído.\n','Aisla la planta afectada para evitar contagios.\n" +
                "Revisa bien toda la planta, especialmente el envés de las hojas y tallos.\n" +
                "Limpia manualmente:\n" +
                "Usa un algodón o paño húmedo con agua jabonosa (jabón neutro, no detergente) y limpia hojas y tallos.\n" +
                "Para cochinillas, puedes usar un bastoncillo mojado en alcohol.\n" +
                "Ducha suave:\n" +
                "Si es posible, lleva la planta a la ducha y enjuaga bien con agua tibia para arrastrar insectos.\n" +
                "Aplica insecticida adecuado:\n" +
                "Ecológico: Jabón potásico, aceite de neem, o extracto de ajo/ortiga.\n" +
                "Químico: Solo si la plaga es grave y los métodos ecológicos no funcionan. Siempre sigue las instrucciones del producto.\n" +
                "Repite el tratamiento cada 5-7 días hasta erradicar la plaga.\n" +
                "Mejora las condiciones:\n" +
                "Mantén alta la humedad (los ácaros odian la humedad).\n" +
                "Ventila bien la zona y revisa regularmente.\n" +
                "Evita el exceso de abono nitrogenado, pues favorece los brotes tiernos que atraen plagas.\n" +
                "Prevención\n" +
                "Inspecciona nuevas plantas antes de introducirlas.\n" +
                "Mantén buena ventilación y humedad ambiental.\n" +
                "Limpia regularmente hojas con paño húmedo.\n" +
                "Vigila cambios en el aspecto de las hojas o brotes.',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'Aquí tienes una guía detallada sobre las manchas marrones en hojas de Calathea insignis (Goeppertia insignis), sus causas, cómo identificarlas y cómo actuar:\n" +
                "\n" +
                "Manchas marrones en Calathea insignis\n" +
                "Síntomas\n" +
                "Manchas marrones de tamaño variable, a veces circulares y otras irregulares.\n" +
                "Pueden aparecer en los bordes, puntas o en el centro de la hoja.\n" +
                "El tejido afectado suele estar seco, quebradizo, o, a veces, hundido.\n" +
                "En casos avanzados, la mancha puede extenderse, fusionarse con otras o secar toda la hoja.\n" +
                "El resto de la hoja puede verse normal o mostrar signos adicionales de estrés (amarilleo, enrollamiento).\n" +
                "Causas frecuentes\n" +
                "Baja humedad ambiental\n" +
                "\n" +
                "Es la causa más común.\n" +
                "Puntas y bordes marrones, a veces manchas discretas en el centro.\n" +
                "Calefacción, aire acondicionado y clima seco agravan el problema.\n" +
                "Riego inadecuado\n" +
                "\n" +
                "Falta de agua: manchas marrones secas.\n" +
                "Exceso de agua o mal drenaje: manchas marrones oscuras, a veces húmedas, y riesgo de pudrición.\n" +
                "Agua de mala calidad\n" +
                "\n" +
                "El agua con cloro, cal o sales provoca necrosis puntual que se ve como manchas marrones.\n" +
                "Quemaduras solares\n" +
                "\n" +
                "Luz directa o excesiva puede causar manchas marrones secas, sobre todo en el centro de la hoja.\n" +
                "Plagas o enfermedades\n" +
                "\n" +
                "Hongos (mancha foliar) o bacterias pueden generar manchas marrones, a veces rodeadas de halos amarillos o con borde húmedo.\n" +
                "Revisar el envés y zonas húmedas por presencia de insectos o puntos negros.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "La acumulación de sales “quema” las raíces y aparece como manchas marrones en las hojas.\n" +
                "Envejecimiento natural\n" +
                "\n" +
                "Hojas viejas pueden mostrar manchas marrones antes de secarse por completo.\n','Identifica la causa probable:\n" +
                "\n" +
                "Revisa humedad, rutina de riego, calidad de agua, exposición a luz y presencia de plagas.\n" +
                "Corrige el ambiente:\n" +
                "\n" +
                "Aumenta la humedad ambiental (humidificador, bandeja con guijarros, agrupar plantas).\n" +
                "Mantén la luz filtrada, nunca directa.\n" +
                "Usa agua de lluvia, destilada o filtrada para riego.\n" +
                "Ajusta el riego:\n" +
                "\n" +
                "Sustrato húmedo pero no encharcado. Deja secar la capa superficial antes de volver a regar.\n" +
                "Recorta las partes dañadas:\n" +
                "\n" +
                "Usa tijeras limpias y corta solo la hoja afectada o la parte marrón siguiendo la forma natural.\n" +
                "Revisa plagas y enfermedades:\n" +
                "\n" +
                "Si ves signos de hongos (manchas con halo, parte húmeda) aplica fungicida específico.\n" +
                "Si sospechas plagas, limpia y aplica insecticida ecológico.\n" +
                "Evita el exceso de fertilización:\n" +
                "\n" +
                "Fertiliza solo en primavera-verano, y con dosis bajas.\n" +
                "Prevención\n" +
                "Mantén humedad ambiental alta (mínimo 50-60%).\n" +
                "Riega con agua de buena calidad.\n" +
                "No expongas la planta a sol directo.\n" +
                "Fertiliza con moderación.\n" +
                "Limpia hojas regularmente y revisa signos de plagas.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'La baja humedad es uno de los problemas más frecuentes para la Calathea insignis (Goeppertia insignis) y afecta directamente a la salud y apariencia de sus hojas.\n" +
                "\n" +
                "Baja humedad en Calathea insignis\n" +
                "Síntomas\n" +
                "Puntas marrones y secas: Las puntas y bordes de las hojas se secan y tornan marrones.\n" +
                "Hojas enrolladas: Las hojas pueden curvarse o enrollarse hacia adentro.\n" +
                "Manchas marrones: Pequeñas áreas secas, especialmente en los bordes.\n" +
                "Hojas opacas o con pérdida de brillo natural.\n" +
                "Crecimiento lento o brotes nuevos deformes.\n" +
                "Por qué ocurre\n" +
                "La Calathea insignis es una planta tropical que necesita humedad ambiental alta (idealmente 60-80%). Los ambientes interiores con calefacción, aire acondicionado o climas secos pueden bajar la humedad a niveles que la planta no tolera bien.\n" +
                "\n','Coloca un humidificador cerca de la planta.\n" +
                "Agrupa plantas: Juntar varias plantas crea un microclima más húmedo.\n" +
                "Bandeja con agua y guijarros: Pon la maceta sobre una bandeja con piedras y agua (el fondo de la maceta no debe tocar el agua directamente).\n" +
                "Rocía agua alrededor (no directo sobre las hojas si hay riesgo de hongos) para elevar la humedad.\n" +
                "Evita corrientes de aire y radiadores cercanos.\n" +
                "Monitorea con un higrómetro: Así sabrás si la humedad está en el rango ideal.\n" +
                "Revisa el riego: Aunque la humedad ambiental es baja, no debes aumentar excesivamente el riego del sustrato.\n" +
                "Prevención\n" +
                "Mantén la humedad alta durante todo el año, especialmente en invierno o en ambientes con aire acondicionado.\n" +
                "Limpia las hojas con un paño húmedo para quitar polvo y ayudar a la transpiración.',48)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'El envejecimiento es un proceso natural por el cual las hojas más viejas de la Calathea insignis completan su ciclo de vida y eventualmente mueren. No es un síntoma de enfermedad ni un problema de cultivo si ocurre de forma aislada y en hojas antiguas.\n" +
                "\n" +
                "Síntomas del envejecimiento natural\n" +
                "Las hojas más bajas y externas comienzan a perder color y vigor.\n" +
                "Aparecen manchas amarillas o marrones que avanzan desde el borde o la punta hacia el centro.\n" +
                "El tejido se vuelve cada vez más seco y quebradizo.\n" +
                "Las hojas pueden enrollarse y, finalmente, secarse por completo.\n" +
                "El resto de la planta y los brotes nuevos se ven sanos y vigorosos.\n" +
                "Suele afectar solo a una o pocas hojas a la vez, no a toda la planta.\n" +
                "Diferencias con otros problemas\n" +
                "Si muchas hojas muestran síntomas a la vez, o si el daño aparece en hojas jóvenes, es probable que haya otro problema (humedad, riego, plagas, etc.).\n" +
                "El envejecimiento no produce manchas húmedas, halos, ni mal olor.\n" +
                "No suele ir acompañado de otros síntomas como caída masiva de hojas o debilitamiento general.\n','No es necesario intervenir: El envejecimiento es normal.\n" +
                "Puedes recortar las hojas viejas cuando estén secas o muy dañadas, usando tijeras limpias, cortando cerca de la base pero sin dañar el tallo principal.\n" +
                "No fertilices ni riegues en exceso intentando “salvar” hojas viejas; mejor enfoca los cuidados en mantener condiciones ideales para el crecimiento nuevo.\n" +
                "Observa los brotes nuevos: Si crecen sanos, la planta está bien cuidada.\n" +
                "Prevención\n" +
                "Mantén condiciones óptimas (humedad alta, riego adecuado, luz filtrada).\n" +
                "Retira hojas muertas para evitar acumulación de residuos y favorecer la estética.\n" +
                "Reemplaza el sustrato cada 1-2 años para estimular la producción de hojas nuevas.\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'El marchitamiento de las puntas de las hojas es un problema muy frecuente en la Calathea insignis (Goeppertia insignis). Aquí tienes una guía completa para identificar la causa y solucionarlo:\n" +
                "\n" +
                "Marchitamiento de las puntas en Calathea insignis\n" +
                "Síntomas\n" +
                "Las puntas de las hojas se ven secas, marrones, quebradizas y pueden estar arrugadas o enrolladas.\n" +
                "El resto de la hoja y la planta pueden lucir sanos, aunque en casos graves el daño puede avanzar hacia los bordes o el centro de la hoja.\n" +
                "No suele haber signos de humedad, manchas acuosas o mal olor en el área afectada.\n" +
                "Causas más comunes\n" +
                "Baja humedad ambiental\n" +
                "Es la causa principal. La Calathea insignis necesita humedad relativa alta (mínimo 60%, ideal 70-80%).\n" +
                "\n" +
                "Riego inadecuado\n" +
                "Tanto el exceso como la falta de agua pueden provocar estrés en los bordes de las hojas.\n" +
                "\n" +
                "Calidad del agua\n" +
                "El agua con cloro, cal o sales puede acumularse en las puntas y dañarlas.\n" +
                "\n" +
                "Exceso de fertilizante\n" +
                "El exceso de sales minerales “quema” las puntas de las hojas.\n" +
                "\n" +
                "Corrientes de aire/frío/calor\n" +
                "Corrientes de aire seco, radiadores o aire acondicionado resecan el ambiente y las hojas.\n" +
                "\n" +
                "Envejecimiento natural\n" +
                "En hojas muy viejas, el síntoma puede deberse solo al ciclo vital normal.\n" +
                "\n','Aumenta la humedad ambiental\n" +
                "\n" +
                "Usa humidificador, bandeja con guijarros y agua, agrupa plantas.\n" +
                "Evita fuentes de calor o frío cerca de la planta.\n" +
                "Revisa el riego\n" +
                "\n" +
                "Riega solo cuando la capa superficial del sustrato esté seca.\n" +
                "Usa agua filtrada, destilada o de lluvia.\n" +
                "Corrige la calidad del agua\n" +
                "\n" +
                "Deja reposar el agua del grifo 24 horas antes de usarla, o utiliza agua libre de cloro y cal.\n" +
                "Reduce la fertilización\n" +
                "\n" +
                "Fertiliza solo en primavera-verano y con dosis bajas.\n" +
                "Recorta puntas dañadas\n" +
                "\n" +
                "Usa tijeras limpias y corta siguiendo la forma natural de la hoja, solo la parte seca o marrón.\n" +
                "Evita corrientes de aire\n" +
                "\n" +
                "Coloca la planta en un lugar protegido, lejos de ventanas abiertas, puertas, radiadores y aire acondicionado.\n" +
                "Prevención\n" +
                "Mantén la humedad alta todo el año.\n" +
                "No riegues en exceso ni dejes que la planta se seque completamente.\n" +
                "Usa agua de buena calidad.\n" +
                "Limpia las hojas con un paño apenas húmedo para evitar acumulación de polvo y sales.\n" +
                "Revisa las hojas regularmente para corregir problemas a tiempo.',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'Las cicatrices son marcas permanentes en las hojas que pueden tener forma de líneas, manchas, hendiduras o áreas de color diferente al tejido sano. No son necesariamente un signo de enfermedad activa, sino el resultado de un daño pasado que la hoja ha “cerrado” o “curado”.\n" +
                "\n" +
                "Principales causas de cicatrices\n" +
                "Daño físico\n" +
                "\n" +
                "Golpes, dobleces, roces o cortes accidentales al mover, limpiar o podar la planta.\n" +
                "Mascotas, niños o el roce con otras macetas pueden dejar marcas.\n" +
                "Daño por plagas o enfermedades pasadas\n" +
                "\n" +
                "Ataques previos de insectos (trips, ácaros, cochinillas) o infecciones fúngicas pueden dejar cicatrices después de que el tejido se recupera.\n" +
                "Quemaduras solares o químicas\n" +
                "\n" +
                "Exposición a sol directo o contacto con fertilizantes/químicos fuertes puede dañar el tejido, que luego cicatriza dejando manchas o áreas descoloridas.\n" +
                "Daño por baja humedad o sequedad puntual\n" +
                "\n" +
                "La deshidratación extrema puede “quemar” zonas de la hoja, que luego se secan y forman una costra o línea.\n" +
                "Daño por riego sobre las hojas\n" +
                "\n" +
                "Si el agua queda sobre las hojas y hay sol o frío, pueden aparecer marcas tras secarse la lesión.\n" +
                "Cómo reconocer una cicatriz\n" +
                "No progresa ni cambia de tamaño con el tiempo.\n" +
                "El tejido cicatrizado suele ser más duro, seco o con color diferente (blanco, marrón claro, plateado).\n" +
                "Puede tener bordes definidos.\n" +
                "No hay signos de humedad, pudrición, ni avance hacia otras zonas.\n" +
                "El resto de la hoja y la planta se ven sanos.','No es necesario tratar las cicatrices. No indican un problema activo.\n" +
                "Si la hoja tiene muchas cicatrices y afecta la estética, puedes recortarla parcialmente o retirar la hoja completa usando tijeras limpias.\n" +
                "Observa los brotes nuevos: si salen sin cicatrices, la planta está bien.\n" +
                "Evita futuras lesiones manipulando la planta con cuidado y dándole un sitio seguro.\n" +
                "Prevención\n" +
                "Manipula la planta con suavidad.\n" +
                "Mantén buena humedad y evita el sol directo.\n" +
                "No limpies las hojas con productos agresivos.\n" +
                "Vigila la aparición de plagas para tratarlas a tiempo.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'Aquí tienes una explicación específica y práctica sobre la pudrición de las hojas en Calathea insignis (Goeppertia insignis):\n" +
                "\n" +
                "Pudrición de las hojas en Calathea insignis\n" +
                "Síntomas\n" +
                "Áreas marrones oscuras, negras o translúcidas en las hojas, blandas y húmedas al tacto.\n" +
                "Mal olor (a podrido o humedad estancada) cerca de la planta o las hojas.\n" +
                "Manchas que se expanden rápidamente.\n" +
                "Hojas que se colapsan, se caen o se desintegran al tocarlas.\n" +
                "Puede empezar en la base de la hoja o en el borde y avanzar hacia el centro.\n" +
                "Causas principales\n" +
                "Exceso de riego\n" +
                "El sustrato demasiado húmedo y mal drenado favorece la aparición de pudrición.\n" +
                "Mal drenaje del sustrato\n" +
                "Sustratos compactados o macetas sin orificios de drenaje retienen agua en exceso.\n" +
                "Ambiente frío y húmedo\n" +
                "Temperaturas bajas + alta humedad ralentizan la evaporación y facilitan la acción de hongos y bacterias.\n" +
                "Infección fúngica o bacteriana\n" +
                "Hongos como Pythium, Phytophthora o bacterias pueden invadir las hojas dañadas o muy húmedas.','Aísla la planta para evitar contagios a otras.\n" +
                "Recorta todas las hojas afectadas usando tijeras limpias y desinfectadas. Desecha el material retirado.\n" +
                "Revisa el sustrato y raíces:\n" +
                "Si el sustrato está empapado, trasplanta a uno nuevo, suelto y bien drenante.\n" +
                "Elimina raíces negras, blandas o malolientes.\n" +
                "Reduce el riego:\n" +
                "Riega solo cuando la capa superior del sustrato esté seca al tacto.\n" +
                "Usa agua de calidad (filtrada o de lluvia).\n" +
                "Mejora la ventilación y evita ambientes muy fríos o húmedos.\n" +
                "Desinfecta herramientas antes y después de usarlas.\n" +
                "Aplica fungicida sistémico (opcional, si el problema es grave y sospechas de hongos).\n" +
                "No mojes las hojas al regar.\n" +
                "Riega directamente el sustrato.\n" +
                "Prevención\n" +
                "Usa siempre macetas con buen drenaje y sustratos ligeros.\n" +
                "Riega con moderación, adaptando la frecuencia a la temporada.\n" +
                "Mantén buena ventilación y humedad ambiental alta, pero sin encharcar.\n" +
                "Limpia regularmente las hojas y revisa signos de enfermedad.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'Síntomas\n" +
                "Pequeños puntos negros, aislados o agrupados, visibles sobre todo en el haz o el envés de la hoja.\n" +
                "A veces rodeados de halos amarillos, marrones o decoloración.\n" +
                "Los puntos pueden sentirse rugosos o hundidos al tacto, o pueden ser lisos, dependiendo de la causa.\n" +
                "Aparecen en hojas nuevas o viejas, y pueden extenderse si no se corrige la causa.\n" +
                "Principales causas\n" +
                "Mancha foliar fúngica o bacteriana\n" +
                "\n" +
                "Hongos (como Cercospora, Phyllosticta) y algunas bacterias pueden causar puntos negros, a veces con halo amarillo o marrón.\n" +
                "Se favorecen por humedad alta y poca ventilación.\n" +
                "Plagas (excrementos o insectos)\n" +
                "\n" +
                "Los trips, ácaros o cochinillas pueden dejar puntos negros (excrementos secos o sus propios cuerpos).\n" +
                "Revisar si los puntos se quitan al frotar suavemente.\n" +
                "Daños físicos o quemaduras químicas\n" +
                "\n" +
                "Salpicaduras de fertilizante o agua con sales pueden dejar puntos negros.\n" +
                "También pueden aparecer tras daños mecánicos o cortes.\n" +
                "Enfermedades avanzadas\n" +
                "\n" +
                "Si los puntos se agrandan o se combinan, puede tratarse de una infección más severa.\n','Observa bien los puntos\n" +
                "\n" +
                "Usa una lupa para ver si son excrementos, insectos, o parte de la hoja.\n" +
                "Frotar suavemente con paño húmedo: si salen, probablemente son de plagas.\n" +
                "Revisa el ambiente\n" +
                "\n" +
                "Asegúrate de que haya buena ventilación y no mojes las hojas al regar.\n" +
                "Retira hojas gravemente afectadas\n" +
                "\n" +
                "Usa tijeras limpias y desinfectadas.\n" +
                "Trata con fungicida o bactericida\n" +
                "\n" +
                "Si sospechas de hongo o bacteria, aplica un fungicida de amplio espectro (puede ser ecológico, como extracto de cola de caballo o cobre, o químico siguiendo indicaciones).\n" +
                "Si el problema persiste, corta y elimina las hojas afectadas.\n" +
                "Revisa y trata plagas\n" +
                "\n" +
                "Si detectas insectos, aplica jabón potásico o aceite de neem.\n" +
                "Ajusta riego y fertilización\n" +
                "\n" +
                "Riega solo el sustrato, no las hojas.\n" +
                "Evita excesos de fertilizante.\n" +
                "Prevención\n" +
                "Mantén buena ventilación y humedad controlada (no excesiva).\n" +
                "Inspecciona regularmente las hojas por ambos lados.\n" +
                "Usa siempre agua de calidad y fertiliza con moderación.\n" +
                "Limpia las hojas suavemente para evitar acumulación de polvo y plagas.',31)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'El oídio es una enfermedad fúngica común en plantas de interior y exterior, causada por diversos hongos (género Erysiphe, Oidium, etc.). Es conocido como “ceniza” o “blanco” por su aspecto característico de polvo blanquecino.\n" +
                "\n" +
                "Síntomas en Calathea insignis\n" +
                "Manchas blancas o grisáceas: Polvo fino que parece harina o ceniza sobre la superficie de las hojas, tallos y brotes.\n" +
                "Crecimiento lento o deformado: Las hojas nuevas pueden salir deformadas o con manchas.\n" +
                "Amarilleo y debilitamiento: El tejido infectado puede amarillear y secarse si el oídio avanza.\n" +
                "Hojas opacas: Pierden su brillo natural y se ven apagadas o sucias.\n" +
                "Condiciones que favorecen el oídio\n" +
                "Ambiente húmedo y poca ventilación: El hongo prospera en humedad alta, pero con aire estancado.\n" +
                "Oscilaciones de temperatura: Días cálidos y noches frescas.\n" +
                "Plantas muy juntas: Facilita el contagio y disminuye el flujo de aire.\n" +
                "Falta de luz: Lugares sombríos favorecen la aparición del hongo.','Evita el hacinamiento de macetas.\n" +
                "No mojes las hojas al regar.\n" +
                "Limpia regularmente las hojas con paño seco o ligeramente húmedo.\n" +
                "Revisa las plantas con frecuencia, especialmente en primavera-verano.',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'La clorosis venal es una condición en la que el espacio entre las venas de la hoja se vuelve amarillo o pálido, mientras que las venas principales permanecen de color verde. Es un síntoma típico de problemas en la nutrición de la planta.\n" +
                "\n" +
                "Síntomas\n" +
                "Hojas con venas verdes y el resto del tejido amarillento o pálido.\n" +
                "Puede empezar en hojas jóvenes o viejas, según la causa.\n" +
                "En casos avanzados, la hoja puede deformarse, secarse o mostrar necrosis en los bordes.\n" +
                "Causas frecuentes\n" +
                "Deficiencia de micronutrientes\n" +
                "\n" +
                "Hierro (Fe): La causa más común. La deficiencia de hierro provoca clorosis internerval en hojas jóvenes.\n" +
                "Magnesio (Mg): Suele afectar a hojas viejas primero.\n" +
                "Manganeso (Mn), zinc (Zn): También pueden estar implicados.\n" +
                "pH inadecuado del sustrato\n" +
                "\n" +
                "Un pH demasiado alto (alcalino) bloquea la absorción de hierro y otros micronutrientes, aunque estén presentes en el sustrato.\n" +
                "Exceso de riego\n" +
                "\n" +
                "El encharcamiento o el mal drenaje pueden dificultar la absorción de nutrientes por las raíces.\n" +
                "Sustrato agotado\n" +
                "\n" +
                "Sustratos viejos o pobres en nutrientes pueden causar carencias.\n" +
                "Daños en las raíces\n" +
                "\n" +
                "Por pudrición, plagas o trasplantes mal hechos.','Confirma el síntoma\n" +
                "\n" +
                "Observa si las venas siguen verdes mientras el tejido entre venas se amarillea.\n" +
                "Revisa el riego\n" +
                "\n" +
                "Asegúrate de no encharcar y de que la maceta drene bien.\n" +
                "Revisa el pH\n" +
                "\n" +
                "El pH óptimo para Calathea es ligeramente ácido (5,5 a 6,5). Si sospechas de pH alto, riega de vez en cuando con agua acidificada (unas gotas de limón o vinagre en 1 litro de agua).\n" +
                "Aporta quelato de hierro\n" +
                "\n" +
                "Aplica un fertilizante específico con quelatos de hierro siguiendo las dosis del envase.\n" +
                "Si el síntoma mejora en 7-10 días, la causa era deficiencia de hierro.\n" +
                "Revisa la fertilización\n" +
                "\n" +
                "Si no usas fertilizante, añade uno equilibrado para plantas verdes que incluya micronutrientes.\n" +
                "Si fertilizas mucho, reduce la dosis (el exceso de sales puede bloquear la absorción).\n" +
                "Renueva el sustrato si está agotado\n" +
                "\n" +
                "Trasplanta si el sustrato es muy viejo o compacto.\n" +
                "Prevención\n" +
                "Usa agua de calidad y evita el exceso de sales y cal.\n" +
                "Fertiliza de forma equilibrada (primavera-verano, dosis bajas).\n" +
                "Mantén el sustrato aireado y con buen drenaje.\n" +
                "Revisa el pH una vez al año si la planta muestra síntomas.\n',51)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (250,'Los insectos chupadores de savia más comunes que pueden atacar a la Calathea insignis son:\n" +
                "\n" +
                "Ácaros (araña roja)\n" +
                "Pulgones\n" +
                "Cochinillas (algodonosas o de caparazón)\n" +
                "Trips\n" +
                "Mosca blanca (menos frecuente en interior, pero posible)\n" +
                "Escamas (insectos planos adheridos a tallos y hojas)\n" +
                "Síntomas de infestación\n" +
                "Manchas amarillas, plateadas o decoloradas en hojas.\n" +
                "Hojas deformadas, enrolladas o con crecimiento anómalo.\n" +
                "Puntos pegajosos (mielada) sobre las hojas o sustrato.\n" +
                "Presencia de insectos pequeños, visibles a simple vista o con lupa, en el envés de las hojas, tallos o brotes.\n" +
                "Telarañas finas (si hay ácaros).\n" +
                "Puntos negros (excrementos de trips o pulgones).\n" +
                "Debilitamiento general de la planta y brotes nuevos afectados.\n','Aísla la planta para evitar el contagio a otras.\n" +
                "Revisa bien todas las hojas, especialmente el envés y los brotes nuevos, con lupa si es posible.\n" +
                "Lava la planta con agua tibia y jabón neutro (puedes usar una esponja suave).\n" +
                "Aplica un tratamiento insecticida ecológico:\n" +
                "Jabón potásico: Rocía toda la planta, incluyendo envés y tallos, cada 4-5 días.\n" +
                "Aceite de neem: Muy eficaz contra cochinillas, ácaros, pulgones y trips.\n" +
                "Alcohol etílico: Para cochinillas visibles, puedes aplicar con un hisopo directamente sobre el insecto.\n" +
                "En infestaciones graves: Usa insecticidas químicos de uso doméstico, siguiendo indicaciones, pero solo como último recurso y lejos de mascotas/niños.\n" +
                "Repite el tratamiento varias veces para eliminar ciclos de huevos y larvas.\n" +
                "Prevención\n" +
                "Revisa regularmente tus plantas, especialmente los brotes nuevos y el envés de las hojas.\n" +
                "Evita el exceso de sequedad y calor, que favorecen ácaros y trips.\n" +
                "Mantén buena ventilación y separa las plantas para dificultar la propagación.\n" +
                "Limpia las hojas regularmente para eliminar polvo y posibles huevos.\n" +
                "Aísla las plantas nuevas al menos dos semanas antes de juntarlas con las demás.\n',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Una flor marchita es aquella que pierde su frescura, comienza a doblarse, se seca o pierde color y firmeza. En boca de dragón, esto puede ser parte del ciclo natural, pero también puede indicar problemas de cultivo o enfermedades.\n" +
                "\n" +
                "Causas comunes\n" +
                "Ciclo natural de la floración\n" +
                "\n" +
                "Las flores de la boca de dragón tienen una vida corta (varios días a una semana). Al terminar su ciclo, es normal que se marchiten.\n" +
                "Falta o exceso de agua\n" +
                "\n" +
                "El estrés hídrico (tanto por sequía como por exceso) puede acelerar el marchitamiento.\n" +
                "Temperaturas elevadas\n" +
                "\n" +
                "El calor extremo puede hacer que las flores duren menos.\n" +
                "Falta de nutrientes\n" +
                "\n" +
                "Carencias de potasio o fósforo pueden afectar la duración de la floración.\n" +
                "Enfermedades\n" +
                "\n" +
                "Hongos como Botrytis (moho gris) pueden atacar flores y hacer que se marchiten prematuramente.\n" +
                "Pudrición del tallo o base de la flor.\n" +
                "Polinización o daño físico\n" +
                "\n" +
                "Después de ser polinizadas o tras un golpe, las flores pueden marchitarse antes de tiempo.','Retira flores marchitas\n" +
                "\n" +
                "Elimina las flores secas o dañadas cortando con tijeras limpias. Esto estimula nuevas floraciones y previene enfermedades.\n" +
                "Revisa el riego\n" +
                "\n" +
                "Mantén el sustrato húmedo pero nunca encharcado.\n" +
                "No dejes que la tierra se seque completamente.\n" +
                "Evita mojar las flores al regar\n" +
                "\n" +
                "Riega directamente en el sustrato para evitar que hongos afecten las flores.\n" +
                "Aporta nutrientes\n" +
                "\n" +
                "Fertiliza cada 2-3 semanas con abono para plantas de flor (rico en fósforo y potasio).\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Evita el hacinamiento y asegúrate de que las plantas tengan buena circulación de aire.\n" +
                "Controla enfermedades\n" +
                "\n" +
                "Si ves moho gris o manchas, elimina las partes afectadas y aplica un fungicida si es necesario.\n" +
                "Prevención\n" +
                "Realiza el “despunte” o corte de flores pasadas para prolongar la floración.\n" +
                "Mantén una rutina de riego regular y moderada.\n" +
                "Fertiliza adecuadamente durante la temporada de flor.\n" +
                "Inspecciona regularmente para detectar y tratar enfermedades a tiempo.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'¿Por qué ocurre?\n" +
                "Ciclo natural de la planta\n" +
                "\n" +
                "Tras la polinización y formación de semillas, las flores terminan su ciclo y se marchitan. Es totalmente normal.\n" +
                "El tallo floral puede secarse parcialmente después de emitir semillas.\n" +
                "Agotamiento de la planta\n" +
                "\n" +
                "La floración demanda muchos nutrientes y energía. Si la planta no recibe suficientes recursos, puede marchitarse más rápido después de florecer.\n" +
                "Condiciones ambientales\n" +
                "\n" +
                "Altas temperaturas, sequía o viento favorecen la deshidratación y la caída rápida de flores y tallos.\n" +
                "Enfermedades o estrés\n" +
                "\n" +
                "Hongos como Fusarium o Botrytis pueden atacar la planta debilitada tras la floración, acelerando el marchitamiento.\n','Elimina flores marchitas (“deadheading”)\n" +
                "\n" +
                "Corta las flores secas o tallos florales marchitos con tijeras limpias. Esto estimula nuevas floraciones y previene enfermedades.\n" +
                "Si no quieres semillas, elimina las flores antes de que formen cápsulas.\n" +
                "Mantén un riego regular\n" +
                "\n" +
                "No dejes secar el sustrato completamente, pero evita el encharcamiento.\n" +
                "Fertiliza después de la floración\n" +
                "\n" +
                "Aplica un abono equilibrado, rico en potasio y fósforo, para ayudar a la planta a recuperarse y, si es perenne, prepararse para la siguiente temporada.\n" +
                "Mejora la ventilación y evita estrés térmico\n" +
                "\n" +
                "Ubica la planta en lugar fresco y bien aireado, lejos de corrientes de aire caliente o sol extremo.\n" +
                "Vigila enfermedades\n" +
                "\n" +
                "Si ves manchas o pudriciones, elimina las partes afectadas y aplica un fungicida si es necesario.\n" +
                "¿Es normal?\n" +
                "Sí, es completamente normal que las flores y algunos tallos se marchiten después de la floración, especialmente si la planta entra en reposo o produce semillas. Sin embargo, con un buen manejo puedes prolongar el periodo de floración y mantener la planta saludable.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'El envejecimiento (senescencia) es el proceso natural por el cual una planta, o parte de ella, completa su ciclo vital y comienza a deteriorarse. En la boca de dragón, esto puede afectar tanto a las flores, las hojas como a la planta entera.\n" +
                "\n" +
                "Síntomas de envejecimiento\n" +
                "Flores marchitas y caída de pétalos.\n" +
                "Hojas amarillentas que se secan y caen, comenzando desde la base.\n" +
                "Tallos leñosos, secos o quebradizos.\n" +
                "Disminución de la producción de nuevas flores o brotes.\n" +
                "Planta general con aspecto apagado o sin vigor.\n" +
                "¿Por qué ocurre?\n" +
                "Ciclo de vida natural\n" +
                "\n" +
                "La boca de dragón suele comportarse como anual o bienal: florece, produce semillas y luego envejece y muere.\n" +
                "En climas suaves, algunas pueden comportarse como perennes de vida corta, pero igualmente declinan después de la floración y fructificación.\n" +
                "Agotamiento por floración\n" +
                "\n" +
                "Tras una floración abundante, la planta puede agotar sus reservas y comenzar a envejecer.\n" +
                "Condiciones ambientales adversas\n" +
                "\n" +
                "Calor, sequía, falta de nutrientes o enfermedades aceleran el envejecimiento.','Elimina flores y tallos secos\n" +
                "\n" +
                "Retirar partes envejecidas estimula la aparición de nuevos brotes si la planta aún tiene energía.\n" +
                "Fertiliza moderadamente\n" +
                "\n" +
                "Aplica abono equilibrado después de la floración para ayudar a la planta a recuperarse.\n" +
                "Riega de forma regular\n" +
                "\n" +
                "Mantén el sustrato húmedo, pero nunca encharcado.\n" +
                "Propaga nuevas plantas\n" +
                "\n" +
                "Si la planta está llegando al final de su ciclo, recolecta semillas para la siguiente temporada, o realiza esquejes si es posible.\n" +
                "Acepta el ciclo natural\n" +
                "\n" +
                "Si la planta ya cumplió su ciclo y no rebrota, reemplázala por nuevas siembras.\n" +
                "Prevención y prolongación de vida\n" +
                "Realiza despuntes periódicos (eliminar flores viejas).\n" +
                "Fertiliza y riega correctamente.\n" +
                "Mantén la planta en buena luz y con buena ventilación.\n" +
                "Si quieres plantas vigorosas cada año, siembra nuevas cada temporada.\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Síntomas de falta de riego\n" +
                "Hojas y flores marchitas o caídas: Las hojas pierden firmeza, se ven flácidas y las flores se doblan fácilmente.\n" +
                "Hojas secas o crujientes, sobre todo en los bordes.\n" +
                "Color amarillento en hojas viejas, que luego pueden volverse marrones.\n" +
                "Crecimiento lento y menor producción de flores.\n" +
                "Tallos débiles y a veces partidos.\n" +
                "Sustrato seco y suelto, que se despega de las paredes de la maceta o el suelo.\n" +
                "Consecuencias de la falta de riego\n" +
                "Las raíces se deshidratan y dejan de absorber nutrientes.\n" +
                "Las flores duran menos y pueden caerse prematuramente.\n" +
                "Si el estrés por sequía se prolonga, la planta puede morir.','Riega profundamente: Humedece bien el sustrato, permitiendo que el agua llegue a las raíces. Si está muy seco, riega poco a poco para que absorba bien.\n" +
                "Elimina hojas y flores secas: Ayuda a la planta a recuperarse y evita el gasto de energía innecesario.\n" +
                "Ajusta la frecuencia de riego: Riega regularmente, manteniendo el sustrato siempre ligeramente húmedo pero no encharcado.\n" +
                "Mejora el sustrato: Si el suelo es muy arenoso o drena demasiado rápido, mezcla con un poco de materia orgánica para retener la humedad.\n" +
                "Protege del sol intenso: En días muy calurosos, da sombra parcial para reducir el estrés hídrico.\n" +
                "Prevención\n" +
                "Riega de manera regular, especialmente en épocas calurosas o ventosas.\n" +
                "Usa mulch (cubierta vegetal) para reducir la evaporación.\n" +
                "Revisa el sustrato cada 2-3 días y riega cuando la parte superior esté seca al tacto.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'La pudrición de la hoja es un proceso donde el tejido foliar se descompone, volviéndose blando, marrón, acuoso y, a menudo, desprendiendo mal olor. Está causada generalmente por hongos o bacterias y favorecida por exceso de humedad.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas marrones, negras o translúcidas en las hojas, que se expanden rápidamente.\n" +
                "Tejido blando y acuoso, a menudo con mal olor.\n" +
                "Hojas que se desprenden fácilmente del tallo.\n" +
                "En casos graves, el problema puede extenderse a tallos y raíces.\n" +
                "Causas comunes\n" +
                "Exceso de riego o mal drenaje\n" +
                "\n" +
                "El exceso de agua en el sustrato favorece el desarrollo de hongos y bacterias.\n" +
                "Ambiente muy húmedo y poca ventilación\n" +
                "\n" +
                "La falta de circulación de aire y la humedad elevada aumentan el riesgo de enfermedades.\n" +
                "Infecciones fúngicas o bacterianas\n" +
                "\n" +
                "Hongos como Botrytis, Pythium, Rhizoctonia y bacterias como Erwinia pueden causar pudrición.\n" +
                "Salpicaduras sobre las hojas\n" +
                "\n" +
                "Mojar las hojas al regar facilita la entrada de patógenos.\n','Retira hojas afectadas\n" +
                "\n" +
                "Corta y elimina todas las hojas y partes blandas o podridas con tijeras limpias y desinfectadas.\n" +
                "Reduce el riego\n" +
                "\n" +
                "Deja secar la capa superior del sustrato antes de volver a regar. Asegúrate de que la maceta drene bien.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Separa las plantas y mejora la circulación de aire alrededor de ellas.\n" +
                "Evita mojar las hojas\n" +
                "\n" +
                "Riega directamente el sustrato, no sobre las hojas.\n" +
                "Aplica fungicida si es necesario\n" +
                "\n" +
                "Usa un fungicida de amplio espectro (como cobre, mancozeb o uno ecológico como extracto de cola de caballo) y sigue las instrucciones del producto.\n" +
                "Desinfecta herramientas\n" +
                "\n" +
                "Lava y desinfecta siempre tijeras y herramientas después de cortar partes enfermas.\n" +
                "Prevención\n" +
                "Riega solo cuando el sustrato esté seco al tacto.\n" +
                "Usa sustratos bien aireados y macetas con buen drenaje.\n" +
                "Evita el hacinamiento de plantas.\n" +
                "Limpia regularmente las hojas de polvo y restos vegetales.\n',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Las orugas son las larvas de mariposas o polillas. Son plagas comunes en plantas ornamentales y pueden causar daños considerables al alimentarse de hojas, brotes y flores.\n" +
                "\n" +
                "Síntomas de la presencia de orugas\n" +
                "Hojas comidas o con agujeros irregulares.\n" +
                "Flores mordidas o parcialmente devoradas.\n" +
                "Presencia de excrementos pequeños y oscuros (bolitas) sobre las hojas.\n" +
                "Orugas visibles: suelen encontrarse en el envés de las hojas o cerca de los brotes.\n" +
                "Daños que causan\n" +
                "Reducción del área foliar, lo que debilita la planta y afecta la floración.\n" +
                "Daño estético importante.\n" +
                "Si la infestación es fuerte, pueden llegar a defoliar la planta casi por completo.\n','Inspección regular\n" +
                "Revisa las plantas, especialmente el envés de las hojas y brotes jóvenes.\n" +
                "Eliminación manual\n" +
                "Si la infestación es pequeña, retira las orugas a mano (usa guantes).\n" +
                "Lava la planta\n" +
                "Rocía con agua a presión suave para desalojar orugas pequeñas.\n" +
                "Control biológico\n" +
                "Fomenta la presencia de aves o introduce enemigos naturales como Trichogramma o Bacillus thuringiensis (Bt).\n" +
                "Bacillus thuringiensis (Bt) es un bioinsecticida muy eficaz y seguro para plantas ornamentales y polinizadores.\n" +
                "Insecticidas ecológicos\n" +
                "Jabón potásico o aceite de neem funcionan como repelentes para larvas jóvenes.\n" +
                "Insecticidas químicos\n" +
                "Úsalos solo si la infestación es severa y siguiendo siempre las instrucciones del fabricante. Evita aplicar en floración para no afectar polinizadores.\n" +
                "Prevención\n" +
                "Mantén la planta sana y bien nutrida, ya que las plantas débiles son más susceptibles.\n" +
                "Revisa plantas nuevas antes de introducirlas en el jardín.\n" +
                "Retira restos vegetales y malezas alrededor de la planta para reducir sitios de refugio.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Principales insectos chupadores\n" +
                "Pulgones\n" +
                "Pequeños, verdes, negros o amarillos, suelen encontrarse en brotes tiernos y el envés de las hojas.\n" +
                "Trips\n" +
                "Muy pequeños, alargados, de color claro u oscuro, causan manchas plateadas y deformación en hojas y flores.\n" +
                "Mosca blanca\n" +
                "Pequeñas mosquitas blancas que vuelan al mover la planta; dejan melaza pegajosa.\n" +
                "Cochinillas\n" +
                "Pequeños bultos algodonosos o escudos marrones en tallos y hojas.\n" +
                "Ácaros (araña roja)\n" +
                "Muy diminutos, suelen dejar telarañas finas; provocan moteado amarillento y caída de hojas.\n" +
                "Síntomas de infestación\n" +
                "Hojas amarillas, deformadas o con manchas.\n" +
                "Brotes y flores marchitos o deformados.\n" +
                "Presencia de melaza (sustancia pegajosa) y, a veces, moho negro (fumagina).\n" +
                "Reducción del crecimiento y vigor general.\n" +
                "Telarañas finas (si hay ácaros).\n','Inspección regular\n" +
                "Revisa el envés de las hojas y brotes jóvenes.\n" +
                "Eliminación manual\n" +
                "Quita insectos visibles con un paño húmedo o con agua a presión (suave).\n" +
                "Jabón potásico o insecticida ecológico\n" +
                "Rocía la planta, cubriendo bien envés y tallos, cada 4-5 días hasta eliminar la plaga.\n" +
                "Aceite de neem\n" +
                "Muy efectivo contra pulgones, cochinillas, trips y mosca blanca.\n" +
                "Retira partes muy afectadas\n" +
                "Si hay zonas muy dañadas, córtalas y deséchalas para evitar la propagación.\n" +
                "Control químico\n" +
                "Solo si la infestación es muy severa y siguiendo siempre las instrucciones del producto.\n" +
                "Prevención\n" +
                "Mantén la planta bien ventilada y no la mojes en exceso.\n" +
                "Limpia regularmente hojas y revisa plantas nuevas antes de juntarlas con las demás.\n" +
                "Fertiliza de forma equilibrada para que la planta esté fuerte y menos susceptible a plagas.',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Causas más comunes\n" +
                "Enfermedades fúngicas\n" +
                "\n" +
                "Alternaria, Septoria, Cercospora, Botrytis: hongos que causan manchas marrones o marrón oscuro, a menudo con halo amarillo o borde definido. Las manchas pueden crecer y unirse, provocando la muerte de zonas de la hoja.\n" +
                "Mildiu o Royas: también pueden dejar manchas marrones o marrón-rojizas en hojas y tallos.\n" +
                "Exceso de humedad\n" +
                "\n" +
                "Sustratos encharcados o riego frecuente favorecen los hongos.\n" +
                "Bacterias\n" +
                "\n" +
                "Algunas infecciones bacterianas causan manchas marrones con aspecto acuoso o bordes irregulares.\n" +
                "Daños físicos o quemaduras\n" +
                "\n" +
                "Salpicaduras de agua bajo sol directo, fertilización excesiva o contacto con productos químicos pueden causar manchas marrones secas.\n" +
                "Plagas\n" +
                "\n" +
                "Trips y ácaros pueden dejar pequeñas manchas marrones donde succionan savia.\n','Retira hojas afectadas\n" +
                "\n" +
                "Corta y desecha las hojas con manchas para evitar la propagación.\n" +
                "Mejora el riego\n" +
                "\n" +
                "Riega solo cuando el sustrato esté seco al tacto y evita mojar las hojas al regar.\n" +
                "Aumenta la ventilación\n" +
                "\n" +
                "Separa las plantas y asegúrate de que haya buena circulación de aire.\n" +
                "Aplica fungicida si es necesario\n" +
                "\n" +
                "Usa un fungicida de amplio espectro (como cobre, mancozeb, azufre) o uno ecológico (extracto de cola de caballo) según la gravedad y la causa sospechada.\n" +
                "Revisa si hay plagas\n" +
                "\n" +
                "Busca en el envés de las hojas y brotes. Si encuentras trips, ácaros o pulgones, usa jabón potásico o aceite de neem.\n" +
                "Evita el exceso de fertilizante\n" +
                "\n" +
                "No abones en exceso; sigue siempre las dosis recomendadas.\n" +
                "Prevención\n" +
                "Mantén el follaje limpio y seco.\n" +
                "Riega temprano en la mañana para que las hojas se sequen rápido si se mojan.\n" +
                "Retira restos vegetales y maleza alrededor de la planta.\n" +
                "Usa sustratos bien drenados y macetas con buen drenaje.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'El tizón del pétalo es una enfermedad fúngica, causada principalmente por el hongo Botrytis cinerea (moho gris), aunque otros hongos pueden estar implicados. Es muy común en ambientes húmedos y frescos.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas marrones, grises o negruzcas en los pétalos, que pueden expandirse rápidamente.\n" +
                "Pétalos marchitos y blandos, con áreas acuosas o descompuestas.\n" +
                "Crecimiento de moho grisáceo sobre los pétalos afectados, especialmente en clima húmedo.\n" +
                "Caída prematura de flores y malformaciones.\n" +
                "Si la infección avanza, puede extenderse a tallos y hojas cercanas.\n" +
                "Causas\n" +
                "Alta humedad ambiental y poca ventilación.\n" +
                "Lluvias frecuentes o riego sobre las flores.\n" +
                "Restos de flores viejas o enfermas sobre la planta.\n" +
                "Plantación muy densa o falta de poda.\n','Retira flores afectadas\n" +
                "\n" +
                "Corta y elimina todas las flores y pétalos enfermos con tijeras limpias.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Separa las plantas y poda para permitir mejor circulación de aire.\n" +
                "Evita mojar las flores al regar\n" +
                "\n" +
                "Riega directamente el sustrato, sin mojar hojas ni flores.\n" +
                "Aplica fungicida\n" +
                "\n" +
                "Usa fungicidas específicos para Botrytis, como productos a base de cobre, mancozeb, o fungicidas biológicos como extracto de cola de caballo.\n" +
                "Aplica según las instrucciones del fabricante, especialmente después de lluvias o en periodos de alta humedad.\n" +
                "Limpia restos vegetales\n" +
                "\n" +
                "Retira flores caídas y restos de poda del suelo para eliminar fuentes de inóculo.\n" +
                "No abones en exceso con nitrógeno\n" +
                "\n" +
                "Un exceso de nitrógeno favorece tejidos blandos y susceptibles a hongos.\n" +
                "Prevención\n" +
                "Realiza despuntes o podas regulares.\n" +
                "Planta a distancias adecuadas para evitar hacinamiento.\n" +
                "Mantén un entorno bien ventilado y evita el exceso de humedad.\n" +
                "Desinfecta tijeras y herramientas antes y después de usarlas.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (320,'Es un insecto pequeño y generalmente brillante (amarillo, verde, negro o con puntos), que se alimenta de las hojas y a veces de flores. Los más comunes en ornamentales son de la familia Chrysomelidae.\n" +
                "\n" +
                "Síntomas de ataque\n" +
                "Agujeros redondeados o irregulares en las hojas.\n" +
                "Hojas muy dañadas o esqueletonizadas (solo quedan nervaduras).\n" +
                "Presencia de escarabajos adultos sobre las hojas durante el día.\n" +
                "Huevos amarillos o anaranjados en el envés de las hojas.\n" +
                "Los adultos y larvas pueden estar presentes al mismo tiempo.\n" +
                "Daños\n" +
                "Reducción del área foliar, lo que afecta la fotosíntesis.\n" +
                "Plantas debilitadas y menos floración.\n" +
                "En casos graves, defoliación casi total.','Inspección regular\n" +
                "\n" +
                "Revisa el haz y envés de las hojas para detectar adultos, larvas y huevos.\n" +
                "Eliminación manual\n" +
                "\n" +
                "Retira los escarabajos y huevos a mano (usa guantes si lo prefieres).\n" +
                "Rocía con agua a presión\n" +
                "\n" +
                "Ayuda a desalojar adultos y larvas.\n" +
                "Insecticidas ecológicos\n" +
                "\n" +
                "Aplica jabón potásico o aceite de neem para repeler y debilitar larvas jóvenes.\n" +
                "Si la infestación es fuerte, usa productos a base de piretrinas naturales (siguiendo indicaciones).\n" +
                "Control biológico\n" +
                "\n" +
                "Fomenta la presencia de aves y depredadores naturales.\n" +
                "Mantén limpieza\n" +
                "\n" +
                "Retira hojas caídas y restos vegetales, donde pueden esconderse larvas.\n" +
                "Prevención\n" +
                "Mantén la planta sana y bien nutrida.\n" +
                "Revisa plantas nuevas antes de incorporarlas.\n" +
                "Limpia frecuentemente el área, especialmente en primavera y verano.',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Causas comunes de la flor marchita\n" +
                "Ciclo natural de la flor\n" +
                "\n" +
                "Las flores tienen un tiempo de vida limitado; tras la polinización y la producción de semillas, se marchitan de manera natural.\n" +
                "Falta de riego\n" +
                "\n" +
                "Si la planta no recibe suficiente agua, las flores se marchitan prematuramente.\n" +
                "Exceso de riego\n" +
                "\n" +
                "El encharcamiento puede causar pudrición radicular, lo que impide que las flores reciban agua y nutrientes.\n" +
                "Temperaturas extremas\n" +
                "\n" +
                "Calor intenso o exposición prolongada al sol puede marchitar las flores rápidamente.\n" +
                "Plagas y enfermedades\n" +
                "\n" +
                "Hongos, bacterias o insectos pueden dañar los tallos florales, provocando la marchitez.\n" +
                "Falta de nutrientes\n" +
                "\n" +
                "Carencias de potasio y fósforo afectan la duración y vigor de las flores.\n','Elimina las flores marchitas (“deadheading”)\n" +
                "\n" +
                "Corta las flores secas para estimular una nueva floración y evitar enfermedades.\n" +
                "Ajusta el riego\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, sin encharcar. Deja secar la capa superficial entre riegos.\n" +
                "Fertiliza moderadamente\n" +
                "\n" +
                "Usa un abono equilibrado, rico en potasio y fósforo, durante la floración.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Evita el hacinamiento y favorece la circulación de aire para prevenir hongos.\n" +
                "Revisa plagas y enfermedades\n" +
                "\n" +
                "Si notas manchas, pudrición o presencia de insectos, consulta para un tratamiento específico.\n" +
                "Protege de temperaturas extremas\n" +
                "\n" +
                "En épocas de calor, proporciona sombra parcial durante las horas más intensas.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Causas más comunes\n" +
                "Enfermedades fúngicas\n" +
                "\n" +
                "Hongos como Alternaria, Septoria, y Fusarium provocan manchas marrones, muchas veces con bordes definidos o halos amarillos.\n" +
                "Botrytis (moho gris) puede causar manchas pardas y áreas blandas, especialmente en ambientes húmedos.\n" +
                "Exceso de humedad y mal drenaje\n" +
                "\n" +
                "El agua estancada favorece la aparición de hongos, sobre todo si el sustrato permanece húmedo mucho tiempo.\n" +
                "Quemaduras por sol o productos químicos\n" +
                "\n" +
                "Salpicaduras de agua en hojas bajo sol intenso, o fertilización y fitosanitarios mal aplicados, pueden causar manchas marrones secas.\n" +
                "Daños por plagas\n" +
                "\n" +
                "Trips, ácaros o chinches pueden dejar marcas marrones en los puntos donde se alimentan.\n','Retira hojas afectadas\n" +
                "\n" +
                "Usa tijeras limpias para eliminar hojas con manchas, evita dejar restos en el suelo.\n" +
                "Revisa el riego\n" +
                "\n" +
                "Deja secar la capa superficial del sustrato antes de volver a regar. Evita el encharcamiento.\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Separa las plantas y poda el exceso de follaje si es necesario para que circule el aire.\n" +
                "Aplica fungicida si es necesario\n" +
                "\n" +
                "Usa un fungicida de amplio espectro (cobre, mancozeb) o ecológico (cola de caballo) si la causa es fúngica. Aplica según instrucciones del envase.\n" +
                "Revisa plagas\n" +
                "\n" +
                "Observa el envés de las hojas y brotes. Si encuentras insectos, usa jabón potásico o aceite de neem.\n" +
                "Evita mojar las hojas al regar\n" +
                "\n" +
                "Riega siempre al pie de la planta.\n" +
                "Prevención\n" +
                "Usa sustratos bien drenados y macetas con orificios de desagüe.\n" +
                "Mantén el follaje seco y limpio.\n" +
                "Abona de forma equilibrada, sin excesos.\n" +
                "Retira restos vegetales y hojas caídas.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'¿Qué son los minadores?\n" +
                "Los minadores son larvas de insectos (principalmente moscas, pero a veces polillas o escarabajos) que viven dentro de las hojas, alimentándose del tejido interno y formando “minas” o galerías visibles.\n" +
                "\n" +
                "Síntomas de daño por minadores\n" +
                "Galerías o túneles serpenteantes y translúcidos en el interior de las hojas.\n" +
                "Manchas blanquecinas o amarillentas en las hojas afectadas.\n" +
                "Hojas deformadas o secas si la infestación es fuerte.\n" +
                "En ocasiones, puedes ver las pequeñas larvas dentro de la hoja, si la observas a contraluz.\n" +
                "Daños que causan\n" +
                "Reducción de la capacidad fotosintética de la planta.\n" +
                "Hojas poco estéticas y debilitadas.\n" +
                "En infestaciones graves, puede afectar el vigor y la floración.','Eliminación manual\n" +
                "\n" +
                "Retira y destruye las hojas afectadas para evitar la diseminación de las larvas.\n" +
                "Revisión constante\n" +
                "\n" +
                "Inspecciona regularmente las hojas, especialmente en primavera y verano.\n" +
                "Control biológico\n" +
                "\n" +
                "Fomenta la presencia de enemigos naturales, como avispitas parásitas (Diglyphus isaea), que controlan eficazmente las poblaciones de minadores.\n" +
                "Insecticidas ecológicos\n" +
                "\n" +
                "El aceite de neem puede ayudar a reducir infestaciones leves, pero los minadores están protegidos dentro de la hoja, por lo que la eficacia es limitada.\n" +
                "Insecticidas sistémicos (como abamectina o imidacloprid) pueden ser necesarios en infestaciones graves, pero deben usarse con precaución y solo como último recurso.\n" +
                "Evita el uso indiscriminado de insecticidas de contacto\n" +
                "\n" +
                "Los productos de contacto suelen ser ineficaces porque la larva está protegida dentro de la hoja.\n" +
                "Prevención\n" +
                "Mantén la planta sana y bien nutrida.\n" +
                "Evita el hacinamiento y mejora la ventilación para dificultar la proliferación de plagas.\n" +
                "Revisa plantas nuevas antes de incorporarlas al jardín.',53)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Causas comunes de cicatrices en clavel lanudo\n" +
                "Daño por plagas\n" +
                "Insectos como trips, minadores, ácaros y escarabajos pueden dejar lesiones que, al sanar, se convierten en cicatrices visibles.\n" +
                "Enfermedades\n" +
                "Infecciones fúngicas o bacterianas pueden causar manchas o lesiones que luego cicatrizan dejando marcas secas o líneas.\n" +
                "Daño mecánico\n" +
                "Cortes, raspaduras, o golpes durante el manejo, poda o por roce con otras plantas, macetas o herramientas.\n" +
                "Condiciones ambientales adversas\n" +
                "Quemaduras por sol, frío, granizo o viento pueden dañar el tejido y dejar cicatrices.\n" +
                "Químicos\n" +
                "Daño por fitotoxicidad (exceso de fertilizante, plaguicidas mal aplicados, etc.).\n" +
                "¿Es grave?\n" +
                "Por lo general, las cicatrices no afectan la vida de la planta si son superficiales y no avanzan. Son principalmente un problema estético. Sin embargo, si aparecen muchas cicatrices nuevas, podría indicar una plaga activa o manejo inadecuado.\n" +
                "\n','Identifica la causa\n" +
                "Observa si hay plagas, signos de enfermedad, daño físico o quemaduras.\n" +
                "Controla plagas y enfermedades\n" +
                "Si ves insectos o síntomas de hongos/bacterias, actúa con el tratamiento adecuado (jabón potásico, aceite de neem, fungicida, etc.).\n" +
                "Mejora el manejo\n" +
                "Manipula la planta con cuidado y usa herramientas limpias.\n" +
                "Evita el exceso de químicos\n" +
                "Aplica fertilizantes y productos siempre en las dosis recomendadas.\n" +
                "Protege del clima extremo\n" +
                "Si es posible, resguarda la planta de sol intenso, viento o granizo.\n" +
                "Retira partes muy dañadas\n" +
                "Si el daño es grave, poda las zonas afectadas para estimular un crecimiento sano.',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'El clavel lanudo (Dianthus spp.) es una planta ornamental que produce pequeñas cápsulas como fruto después de la floración, donde se almacenan las semillas. Estas cápsulas no son frutos carnosos, sino secos y discretos.\n" +
                "\n" +
                "¿Por qué se marchita el fruto (cápsula de semilla)?\n" +
                "Causas naturales:\n" +
                "Ciclo normal: Tras la formación y maduración, la cápsula se seca (marchita) y se abre para liberar semillas. Esto es completamente natural.\n" +
                "Causas anormales:\n" +
                "Falta o exceso de riego\n" +
                "Si la planta está muy seca o encharcada, las cápsulas pueden secarse prematuramente o no desarrollarse bien.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "Hongos como Botrytis o Fusarium pueden atacar las cápsulas, haciendo que se tornen marrones, blandas y se pudran antes de madurar.\n" +
                "Plagas\n" +
                "Insectos como trips, pulgones o minadores pueden dañar los frutos, afectando su desarrollo.\n" +
                "Falta de nutrientes\n" +
                "Carencias (especialmente potasio y fósforo) afectan la formación y duración del fruto.\n" +
                "Estrés ambiental\n" +
                "Calor intenso, viento fuerte o cambios bruscos de temperatura pueden hacer que las cápsulas se sequen antes de tiempo.\n','Identifica si es natural o un problema\n" +
                "\n" +
                "Si la cápsula se seca tras la floración y contiene semillas, es normal.\n" +
                "Si se marchita sin semilla, con manchas, pudrición o plagas, hay un problema.\n" +
                "Mejora el riego\n" +
                "\n" +
                "Mantén el sustrato apenas húmedo, sin encharcamiento.\n" +
                "Elimina frutos enfermos\n" +
                "\n" +
                "Retira y desecha cápsulas afectadas por hongos o plagas.\n" +
                "Aplica tratamiento si hay plagas o hongos\n" +
                "\n" +
                "Usa fungicidas suaves (ejemplo: cobre) y/o insecticidas ecológicos (jabón potásico, aceite de neem).\n" +
                "Abona correctamente\n" +
                "\n" +
                "Un fertilizante equilibrado ayuda a una mejor formación de semillas.',32)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'El oídio es una enfermedad fúngica muy común en plantas ornamentales, causada por hongos del género Erysiphe, Sphaerotheca u otros. Se reconoce fácilmente por un polvo blanco o grisáceo en hojas, tallos y a veces flores.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas blancas o grisáceas como polvo en la superficie de hojas, tallos o botones florales.\n" +
                "Hojas deformadas, amarillas o secas si el ataque es severo.\n" +
                "Retraso en el crecimiento y menor floración.\n" +
                "Las hojas gravemente afectadas pueden caer prematuramente.\n" +
                "Causas\n" +
                "Alta humedad ambiental y falta de ventilación.\n" +
                "Temperaturas moderadas (15-25°C) y cambios de temperatura entre día y noche.\n" +
                "Exceso de fertilizante nitrogenado.','Retira partes afectadas\n" +
                "\n" +
                "Elimina y desecha hojas, tallos o flores con mucho oídio (no las composte).\n" +
                "Mejora la ventilación\n" +
                "\n" +
                "Separa las plantas y poda el exceso de follaje.\n" +
                "Evita mojar el follaje\n" +
                "\n" +
                "Riega siempre al pie de la planta.\n" +
                "Tratamiento con fungicidas\n" +
                "\n" +
                "Aplica fungicidas específicos para oídio, como azufre, cobre, bicarbonato de potasio o productos ecológicos como leche diluida (1 parte de leche por 9 de agua).\n" +
                "Repite el tratamiento cada 7-10 días mientras dure la infección.\n" +
                "Reduce el exceso de nitrógeno\n" +
                "\n" +
                "Fertiliza de forma equilibrada, sin abusar del nitrógeno.\n" +
                "Prevención\n" +
                "Mantén las plantas bien espaciadas y aireadas.\n" +
                "Evita el riego por aspersión, principalmente en la tarde/noche.\n" +
                "Retira restos vegetales y hojas caídas regularmente.\n" +
                "Realiza tratamientos preventivos en épocas de alta humedad.',50)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Causas normales (fisiológicas)\n" +
                "Ciclo natural de la planta:\n" +
                "Tras la floración, los tallos florales y las flores se marchitan porque la planta invierte energía en producir semillas. Es completamente normal que las flores y sus tallos se sequen y mueran después de cumplir su función.\n" +
                "Causas anormales (problemas a considerar)\n" +
                "Falta o exceso de riego:\n" +
                "El sustrato demasiado seco o encharcado puede acelerar la muerte de tallos y hojas después de la floración.\n" +
                "\n" +
                "Agotamiento de nutrientes:\n" +
                "La floración consume nutrientes, y si no fertilizas, la planta puede debilitarse y marchitarse rápido.\n" +
                "\n" +
                "Plagas y enfermedades:\n" +
                "Hongos (Fusarium, Verticillium), nematodos o bacterias pueden invadir la base del tallo tras la floración, causando marchitamiento anormal.\n" +
                "\n" +
                "Estrés ambiental:\n" +
                "Altas temperaturas, cambios bruscos, viento fuerte o quemaduras solares pueden acelerar el marchitamiento.','Qué hacer? (Soluciones y manejo)\n" +
                "Elimina las partes marchitas (despunte o poda):\n" +
                "Corta flores y tallos secos para estimular brotes nuevos y posible refloración o desarrollo vegetativo.\n" +
                "Revisa el riego:\n" +
                "Mantén el sustrato ligeramente húmedo, sin encharcamientos.\n" +
                "Fertiliza tras la floración:\n" +
                "Aplica un abono equilibrado rico en potasio y fósforo para ayudar a la recuperación.\n" +
                "Observa síntomas adicionales:\n" +
                "Si ves manchas, tallos blandos o mal olor, revisa si hay hongos o plagas y actúa con el tratamiento adecuado (fungicida, insecticida).\n" +
                "Mejora ventilación y ubicación:\n" +
                "Asegura buena circulación de aire y protección ante climas extremos.\n" +
                "Prevención\n" +
                "Mantén la planta bien nutrida y con riego controlado.\n" +
                "Retira siempre flores y tallos secos.\n" +
                "Realiza tratamientos preventivos para hongos si el clima es húmedo.\n" +
                "No abuses del fertilizante nitrogenado tras la floración.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Causas frecuentes\n" +
                "Falta de riego\n" +
                "\n" +
                "La tierra seca hace que la planta no pueda absorber agua suficiente.\n" +
                "Síntomas: hojas y tallos flácidos, color apagado, bordes secos.\n" +
                "Exceso de riego\n" +
                "\n" +
                "El encharcamiento provoca pudrición de raíces, impidiendo la absorción de agua y nutrientes.\n" +
                "Síntomas: hojas amarillas, blandas, mal olor en la base, tallos que colapsan.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Fusarium, Verticillium, Pythium, y bacterias pueden atacar las raíces y el cuello de la planta.\n" +
                "Síntomas: marchitamiento rápido, a veces solo de un lado, tallos con manchas oscuras o blandos.\n" +
                "Plagas\n" +
                "\n" +
                "Nemátodos, minadores de raíz o insectos chupadores pueden debilitar la planta.\n" +
                "Síntomas: raíces dañadas, crecimiento lento, hojas pálidas o con manchas.\n" +
                "Estrés ambiental\n" +
                "\n" +
                "Calor, viento fuerte, cambios bruscos de temperatura o luz solar excesiva.\n" +
                "Síntomas: marchitamiento en las horas calurosas, recuperación al atardecer si no es grave.\n" +
                "Deficiencias nutricionales\n" +
                "\n" +
                "Falta de potasio, fósforo o hierro puede debilitar la planta.\n" +
                "Síntomas: hojas cloróticas, crecimiento pobre, menor floración.','Revisa el sustrato\n" +
                "\n" +
                "Si está seco, riega poco a poco.\n" +
                "Si está encharcado, mejora el drenaje y suspende el riego hasta que se seque la superficie.\n" +
                "Examina raíces y base\n" +
                "\n" +
                "Saca la planta con cuidado. Si las raíces están marrones/negras y blandas, hay pudrición. Elimina las partes dañadas y trasplanta.\n" +
                "Mejora el ambiente\n" +
                "\n" +
                "Pon la planta en un sitio con buena ventilación y luz filtrada.\n" +
                "Controla plagas y enfermedades\n" +
                "\n" +
                "Usa fungicidas si hay sospecha de hongo.\n" +
                "Aplica insecticidas ecológicos si ves plagas.\n" +
                "Fertiliza correctamente\n" +
                "\n" +
                "Usa un abono equilibrado, sin excesos de nitrógeno.\n" +
                "Poda partes marchitas\n" +
                "\n" +
                "Elimina hojas y tallos secos para estimular brotes nuevos.',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Síntomas de falta de riego\n" +
                "Hojas y tallos decaídos o marchitos: Se ven caídos, flácidos y sin firmeza.\n" +
                "Coloración opaca: Las hojas pueden volverse más claras, grises o amarillas.\n" +
                "Bordes secos o quemados: Las puntas y márgenes de las hojas se secan.\n" +
                "Flores y capullos secos: Caen antes de abrirse o se marchitan rápidamente.\n" +
                "Crecimiento lento o detenido: La planta deja de producir brotes o flores nuevas.\n" +
                "Sustrato muy seco: Al tocar la tierra, se siente seca y suelta, e incluso puede separarse del borde de la maceta.\n" +
                "Consecuencias\n" +
                "Si la sequía se prolonga, las raíces pueden morir y la planta puede no recuperarse.\n" +
                "Menor floración y desarrollo general débil.','Riega de inmediato\n" +
                "\n" +
                "Hazlo de forma gradual para no ahogar a la planta. Empieza humedeciendo el sustrato poco a poco hasta que esté bien hidratado.\n" +
                "Revisa la frecuencia de riego\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, pero nunca encharcado. Riega cuando la capa superficial esté seca al tacto.\n" +
                "Mejora el sustrato\n" +
                "\n" +
                "Usa un sustrato que drene bien para evitar tanto el exceso como la falta de agua.\n" +
                "Mulching\n" +
                "\n" +
                "Coloca una capa de mantillo (paja, corteza, etc.) para conservar la humedad en el suelo.\n" +
                "Ubicación adecuada\n" +
                "\n" +
                "Si el clima es muy caluroso, proporciona sombra parcial en las horas de mayor sol.\n" +
                "Prevención\n" +
                "Riega regularmente según la estación: más frecuente en verano, menos en invierno.\n" +
                "Usa macetas con buen drenaje.\n" +
                "Observa tu planta: si ves síntomas tempranos de falta de agua, actúa rápido.\n',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (330,'Pulgones (Aphididae)\n" +
                "\n" +
                "Pequeños, blandos, de color verde, negro, amarillo o rojo.\n" +
                "Se agrupan en brotes tiernos y el envés de las hojas.\n" +
                "Mosca blanca (Aleyrodidae)\n" +
                "\n" +
                "Insectos diminutos, blancos, que vuelan al mover la planta.\n" +
                "Sus larvas y adultos chupan savia en el envés de las hojas.\n" +
                "Trips (Thysanoptera)\n" +
                "\n" +
                "Muy pequeños, de color oscuro o amarillento, rápidos y difíciles de ver.\n" +
                "Dejan manchas plateadas o punteadas en hojas y pétalos.\n" +
                "Cochinillas (Coccoidea)\n" +
                "\n" +
                "Parecen pequeñas costras o bolitas blancas/algodonosas en tallos y hojas.\n" +
                "Algunas segregan una sustancia algodonosa.\n" +
                "Ácaros (principalmente araña roja, Tetranychidae)\n" +
                "\n" +
                "Muy pequeños, generan finas telarañas, hojas decoloradas o punteadas.\n" +
                "Síntomas de daño\n" +
                "Hojas enrolladas, deformadas o amarillas.\n" +
                "Brotes nuevos detenidos o marchitos.\n" +
                "Presencia de melaza (líquido pegajoso) y fumagina (moho negro superficial).\n" +
                "Flores deformes o con manchas.\n" +
                "Crecimiento lento y aspecto general decaído.','Revisión frecuente\n" +
                "\n" +
                "Examina el envés de las hojas y brotes nuevos cada semana.\n" +
                "Eliminación manual\n" +
                "\n" +
                "Retira insectos visibles con un pincel, algodón humedecido o agua a presión.\n" +
                "Insecticidas ecológicos\n" +
                "\n" +
                "Aplica jabón potásico o aceite de neem cada 5-7 días, cubriendo bien el envés de las hojas.\n" +
                "Para ácaros, usa azufre en polvo o acaricidas ecológicos.\n" +
                "Control biológico\n" +
                "\n" +
                "Fomenta la presencia de mariquitas, crisopas y avispas parásitas, depredadores naturales de pulgón, mosca blanca y cochinilla.\n" +
                "Evita el exceso de abono nitrogenado\n" +
                "\n" +
                "Favorece el crecimiento blando, más atractivo para estos insectos.\n" +
                "Mejora la ventilación y reduce el hacinamiento\n" +
                "\n" +
                "Menos humedad y mejor circulación dificultan la proliferación de plagas.\n" +
                "Prevención\n" +
                "Inspecciona nuevas plantas antes de incorporarlas.\n" +
                "Mantén la planta limpia y sin restos vegetales.\n" +
                "Evita el estrés hídrico, ya que las plantas debilitadas son más atacadas.\n',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'¿Es normal que la flor se marchite?\n" +
                "Sí, es parte del ciclo natural de la planta. Las flores de la neguilla suelen durar solo unos pocos días. Tras la floración, los pétalos se marchitan, caen y dan paso a la formación del fruto o cápsula de semillas, característica de la especie.\n" +
                "\n" +
                "¿Cuándo es un problema?\n" +
                "Si notas que las flores se marchitan prematuramente (antes de abrirse bien o en cantidad), puede deberse a:\n" +
                "\n" +
                "Falta o exceso de agua\n" +
                "El sustrato muy seco o encharcado puede provocar caída o marchitez prematura de flores.\n" +
                "Temperaturas extremas\n" +
                "Calor excesivo, viento fuerte, o cambios bruscos pueden hacer que las flores duren menos.\n" +
                "Ataque de plagas\n" +
                "Trips, pulgones o ácaros pueden dañar los capullos y flores.\n" +
                "Enfermedades fúngicas\n" +
                "Oídio u otros hongos pueden afectar los botones florales.\n" +
                "Déficit nutricional\n" +
                "Falta de potasio o fósforo puede afectar la duración y calidad de la floración.\n','Riego adecuado\n" +
                "Mantén el sustrato moderadamente húmedo, sin encharcar.\n" +
                "Ubicación protegida\n" +
                "Si hay viento o calor fuerte, pon la planta en un lugar más resguardado.\n" +
                "Revisa plagas y enfermedades\n" +
                "Observa flores y capullos por pequeños insectos o manchas. Usa jabón potásico o aceite de neem si ves plagas.\n" +
                "Abona moderadamente\n" +
                "Usa abono equilibrado, rico en potasio y fósforo.\n" +
                "Retira flores marchitas\n" +
                "Esto estimula nuevas floraciones y mejora la salud general.\n',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'Síntomas de falta de riego\n" +
                "Hojas decaídas: Las hojas pierden firmeza y se ven flácidas o caídas.\n" +
                "Coloración opaca: Las hojas pueden tornarse amarillas o marrones en los bordes.\n" +
                "Crecimiento lento: La planta deja de crecer y puede no florecer.\n" +
                "Capullos y flores secas: Los capullos se caen o las flores se marchitan antes de tiempo.\n" +
                "Sustrato seco: La tierra se separa del borde de la maceta o está muy suelta.\n" +
                "Consecuencias\n" +
                "Reducción significativa en la floración y producción de semillas.\n" +
                "Si la sequía es prolongada, puede provocar la muerte de la planta.\n','Riega de inmediato, pero gradualmente\n" +
                "Humedece el sustrato poco a poco para evitar shock por exceso de agua repentina.\n" +
                "Ajusta la frecuencia de riego\n" +
                "Riega cuando la capa superficial de la tierra esté seca al tacto, evitando encharcamientos.\n" +
                "Mejora el sustrato\n" +
                "Usa un suelo que drene bien para evitar exceso de humedad, pero que retenga algo de agua.\n" +
                "Mulching\n" +
                "Aplica una capa de mantillo (hojarasca, corteza, paja) para conservar la humedad.\n" +
                "Ubicación\n" +
                "Si el clima es muy caluroso, proporciona sombra parcial durante las horas de mayor sol.\n" +
                "Prevención\n" +
                "Observa la planta regularmente, especialmente en épocas secas o calurosas.\n" +
                "Riega con constancia, adaptando la frecuencia según la estación y el clima.\n" +
                "Asegúrate de que las macetas tengan buen drenaje.\n',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'Es una enfermedad causada por bacterias (generalmente del género Xanthomonas o Pseudomonas) que infectan hojas, tallos y a veces flores, produciendo manchas visibles y debilitando la planta.\n" +
                "\n" +
                "Síntomas\n" +
                "Manchas pequeñas, oscuras o acuosas en hojas, a menudo rodeadas por un halo amarillo.\n" +
                "Las manchas pueden crecer, unirse y hacer que la hoja se marchite o muera.\n" +
                "Si la infección es severa, las hojas pueden secarse y caer.\n" +
                "En casos graves, también pueden aparecer lesiones en tallos y frutos/cápsulas.\n" +
                "Causas y condiciones favorables\n" +
                "Alta humedad y salpicaduras de agua favorecen la propagación.\n" +
                "Temperaturas cálidas (20-30°C) aceleran el desarrollo bacteriano.\n" +
                "Heridas en la planta (por poda, insectos, granizo, etc.) facilitan la entrada de las bacterias.\n','Retira partes afectadas\n" +
                "Corta hojas o tallos con manchas y deséchalos (no los compostes).\n" +
                "Evita mojar el follaje\n" +
                "Riega solo al pie de la planta, nunca por aspersión.\n" +
                "Mejora la ventilación\n" +
                "Mantén espacio entre plantas y poda exceso de follaje.\n" +
                "Desinfecta herramientas\n" +
                "Siempre limpia tijeras y cuchillas con alcohol después de cortar partes enfermas.\n" +
                "No manipules las plantas cuando están mojadas\n" +
                "Así evitas propagar la bacteria.\n" +
                "Uso de productos\n" +
                "El cobre (caldo bordelés, oxicloruro de cobre) puede ayudar a limitar la propagación, aunque no cura hojas ya enfermas.\n" +
                "No hay curas químicas específicas, la prevención es clave.\n" +
                "Prevención\n" +
                "Usa siempre semillas y plantas sanas.\n" +
                "Evita el encharcamiento y el exceso de humedad ambiental.\n" +
                "Retira restos de plantas viejas y desinfecta el sustrato si ha habido infecciones previas.\n" +
                "Rota los cultivos para evitar que la bacteria se establezca en el suelo.',36)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'Falta de riego\n" +
                "\n" +
                "Sustrato seco, hojas y tallos flácidos, crecimiento detenido.\n" +
                "Exceso de riego o mal drenaje\n" +
                "\n" +
                "Encharcamiento, raíces blandas o negras, hojas amarillas, mal olor en la base.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Hongos como Fusarium, Pythium o bacterias pueden dañar raíces y tallos.\n" +
                "Síntomas: marchitamiento rápido, tallos con manchas, muerte súbita.\n" +
                "Estrés ambiental\n" +
                "\n" +
                "Calor excesivo, viento fuerte, cambios bruscos de temperatura o luz solar directa prolongada.\n" +
                "Marchitamiento en horas de calor, recuperación parcial al atardecer.\n" +
                "Plagas\n" +
                "\n" +
                "Daños por nematodos, trips, pulgones o ácaros pueden debilitar la planta y causar marchitez.\n" +
                "Deficiencia nutricional\n" +
                "\n" +
                "Falta de nutrientes, sobre todo potasio y fósforo.','Revisa el sustrato\n" +
                "\n" +
                "Si está seco, riega gradualmente.\n" +
                "Si está encharcado, mejora el drenaje y deja secar antes de volver a regar.\n" +
                "Examina raíces y base\n" +
                "\n" +
                "Saca suavemente la planta. Si ves raíces podridas, elimina las partes dañadas y trasplanta a sustrato nuevo.\n" +
                "Observa hojas y tallos\n" +
                "\n" +
                "Busca manchas, pudrición o presencia de plagas y actúa según corresponda (fungicida, insecticida).\n" +
                "Ajusta ubicación\n" +
                "\n" +
                "Coloca la planta en lugar aireado y protegida de sol extremo o viento fuerte.\n" +
                "Fertiliza moderadamente\n" +
                "\n" +
                "Usa abono equilibrado, sin exceder el nitrógeno.\n" +
                "Poda partes secas\n" +
                "\n" +
                "Retira hojas y tallos marchitos para favorecer brotes nuevos.\n" +
                "Prevención\n" +
                "Mantén riego y drenaje adecuados.\n" +
                "Espacia bien las plantas para mejorar ventilación.\n" +
                "Inspecciona regularmente para detectar plagas o enfermedades a tiempo.\n" +
                "No abuses del riego ni del abono',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'El marchitamiento después de la floración en la neguilla (Nigella sativa) es un proceso completamente normal y esperado, aunque también puede indicar problemas bajo ciertas circunstancias. Aquí tienes un resumen de causas y recomendaciones:\n" +
                "\n" +
                "Marchitamiento después de la floración en neguilla\n" +
                "1. Ciclo natural de la planta\n" +
                "Tras la floración, la neguilla dirige su energía a la formación de semillas.\n" +
                "Las flores y parte de la planta se marchitan y secan naturalmente.\n" +
                "Es señal de que el ciclo vital anual está llegando a su fin.\n" +
                "2. ¿Cuándo preocuparse?\n" +
                "Si el marchitamiento es excesivamente rápido, afecta a toda la planta antes de que las semillas se formen, o va acompañado de otros síntomas (manchas, mal olor, pudrición), puede deberse a:\n" +
                "Falta o exceso de riego.\n" +
                "Enfermedades fúngicas o bacterianas.\n" +
                "Estrés ambiental (calor extremo, viento, sequía).\n" +
                "Ataque de plagas.','Retira flores y tallos marchitos para favorecer aireación y prevenir hongos.\n" +
                "Mantén un riego moderado, sin encharcamientos.\n" +
                "Vigila la formación de cápsulas de semillas, que es el objetivo de la neguilla tras la floración.\n" +
                "Si detectas síntomas sospechosos (manchas, pudrición), revisa si hay plagas o enfermedades y actúa según corresponda.\n" +
                "4. Recomendación final\n" +
                "El marchitamiento tras la floración es parte del ciclo natural de la neguilla.\n" +
                "Si quieres recolectar semillas, deja secar bien las cápsulas antes de cortarlas.\n" +
                "Si cultivas la neguilla como ornamental, puedes retirar las plantas secas y sembrar nuevas la siguiente temporada.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'El envejecimiento, también llamado senescencia, es el proceso natural en el que una planta completa su ciclo de vida, pierde vitalidad y finalmente muere. En especies anuales como la neguilla, este proceso ocurre tras la floración y la formación de semillas.\n" +
                "\n" +
                "2. Síntomas de envejecimiento natural\n" +
                "Hojas amarillas o secas, especialmente desde la base.\n" +
                "Floración y fructificación completas: después de producir semillas, la planta comienza a deteriorarse.\n" +
                "Tallos que se secan y colapsan.\n" +
                "Pérdida de vigor: crecimiento detenido, hojas y flores pequeñas o deformes.\n" +
                "3. ¿Por qué ocurre?\n" +
                "La neguilla es una planta anual: germina, crece, florece, produce semillas y muere, todo en un mismo año.\n" +
                "El envejecimiento es necesario para que la planta libere semillas y la especie continúe.\n','Recolecta las semillas cuando las cápsulas estén secas si quieres reproducir la planta.\n" +
                "Retira la planta seca y limpia el área para evitar plagas o enfermedades.\n" +
                "Siembra nuevas semillas en la siguiente temporada para tener nuevas plantas.\n" +
                "5. Diferenciar envejecimiento de problemas\n" +
                "Si la planta está en etapa final de vida y ves síntomas como los mencionados arriba, es normal.\n" +
                "Si el deterioro ocurre antes de la floración o formación de semillas, revisa si hay plagas, enfermedades, o problemas de riego.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'¿Qué son los trips?\n" +
                "Son insectos diminutos (1-2 mm), alargados, generalmente de color amarillo, marrón o negro.\n" +
                "Se mueven rápidamente y a menudo saltan cuando se les molesta.\n" +
                "Se encuentran en flores, hojas y brotes tiernos.\n" +
                "Síntomas de daño por trips\n" +
                "Manchas plateadas o blanquecinas en hojas y pétalos.\n" +
                "Punteado o pequeñas heridas en la superficie de las hojas.\n" +
                "Deformación de flores y brotes.\n" +
                "Aparición de líneas marrones o puntos negros (excrementos).\n" +
                "Caída prematura de flores y hojas jóvenes.\n" +
                "En infestaciones graves: crecimiento atrofiado y menor floración.\n" +
                "Ciclo de vida\n" +
                "Ponen huevos dentro de tejidos vegetales.\n" +
                "Las ninfas y adultos se alimentan de la savia.\n" +
                "Proliferan en clima cálido y seco.','1. Control preventivo\n" +
                "Revisa frecuentemente el envés de hojas y capullos.\n" +
                "Mantén buena ventilación y elimina malezas cercanas.\n" +
                "2. Medidas ecológicas\n" +
                "Trampas adhesivas azules: Muy efectivas para monitoreo y captura.\n" +
                "Ducha suave: Lava las plantas con agua para reducir la población.\n" +
                "Control biológico: Fomentar insectos benéficos (crisopas, orius, ácaros depredadores).\n" +
                "3. Insecticidas ecológicos\n" +
                "Jabón potásico o aceite de neem: Pulveriza sobre hojas y flores, insistiendo en el envés.\n" +
                "Extractos naturales: Como ajo o piretro.\n" +
                "4. En casos graves\n" +
                "Alterna productos para evitar resistencia.\n" +
                "Si usas insecticidas químicos, sigue las instrucciones y protege polinizadores (aplica al atardecer).\n" +
                "Consejos adicionales\n" +
                "Elimina restos vegetales y flores marchitas.\n" +
                "No abuses de abonos nitrogenados (favorecen tejidos blandos y más atacables).\n" +
                "Si cultivas en interior o invernadero, ventila regularmente.',40)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'¿Qué son las cochinillas?\n" +
                "Insectos pequeños, de cuerpo blando, a veces cubiertos por una capa cerosa blanca, algodonosa o marrón.\n" +
                "Se ubican en tallos, hojas (generalmente en el envés), axilas y a veces raíces.\n" +
                "Succionan la savia de la planta, debilitándola.\n" +
                "Síntomas de daño por cochinillas\n" +
                "Manchas pegajosas (melaza) en hojas y tallos.\n" +
                "Hojas amarillas, deformadas o caídas.\n" +
                "Crecimiento lento o marchitamiento.\n" +
                "Presencia de negrilla (hongo negro) sobre la melaza excretada por las cochinillas.\n" +
                "Pequeños bultos algodonosos o costrosos en tallos y hojas.\n','1. Control manual\n" +
                "Retira cochinillas con un bastoncillo de algodón humedecido en alcohol de 70°.\n" +
                "Lava la planta con agua y unas gotas de jabón potásico.\n" +
                "2. Insecticidas ecológicos\n" +
                "Jabón potásico: Pulveriza sobre las zonas afectadas, repite cada 5-7 días.\n" +
                "Aceite de neem: Muy efectivo contra cochinillas y otras plagas.\n" +
                "Aceite mineral: Asfixia a las cochinillas, aplícalo siguiendo instrucciones.\n" +
                "3. Control preventivo\n" +
                "Mantén buena ventilación y evita el exceso de humedad.\n" +
                "Inspecciona regularmente las plantas, sobre todo en primavera y verano.\n" +
                "4. Control biológico\n" +
                "Fomenta la presencia de insectos benéficos como mariquitas (coccinélidos) y crisopas.\n" +
                "Consejos adicionales\n" +
                "Si la infestación es grave, poda las partes más afectadas.\n" +
                "No sobreabonar con nitrógeno, ya que favorece tejidos blandos susceptibles a plagas.\n" +
                "Elimina restos vegetales y hojas caídas.',13)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'Mancha marrón en neguilla (Nigella sativa)\n" +
                "1. Mancha marrón por hongos\n" +
                "Síntomas: Manchas marrones irregulares, a veces rodeadas por un halo amarillo, pueden aparecer en hojas, tallos o flores. Las manchas pueden crecer y unirse, provocando necrosis.\n" +
                "Causas: Humedad alta, ventilación insuficiente, riego por aspersión.\n" +
                "Hongos comunes: Alternaria, Septoria, Botrytis.\n" +
                "Solución:\n" +
                "Retira las hojas afectadas.\n" +
                "Mejora la ventilación.\n" +
                "Evita mojar el follaje al regar.\n" +
                "Aplica fungicidas ecológicos (azufre, cobre, extracto de cola de caballo).\n" +
                "2. Mancha marrón por exceso de riego o mal drenaje\n" +
                "Síntomas: Manchas marrones en la base del tallo o raíces, plantas flácidas, olor a podrido.\n" +
                "Solución:\n" +
                "Deja secar el sustrato antes de volver a regar.\n" +
                "Mejora el drenaje.\n" +
                "Si hay podredumbre, elimina las partes dañadas y trasplanta.\n" +
                "3. Mancha marrón por quemaduras solares\n" +
                "Síntomas: Manchas marrones secas, a veces con borde definido, en hojas expuestas al sol intenso.\n" +
                "Solución:\n" +
                "Proporciona sombra parcial en las horas más calurosas.\n" +
                "Riega en la base, no en el follaje.\n" +
                "4. Mancha marrón por carencias nutricionales\n" +
                "Síntomas: Manchas marrones en los bordes de la hoja, a menudo asociadas con amarilleo general.\n" +
                "Solución:\n" +
                "Aplica un fertilizante equilibrado, rico en potasio y magnesio.\n','Observa si las manchas se extienden rápidamente y si hay otros síntomas (mal olor, moho, caída de hojas).\n" +
                "Retira las partes afectadas con tijeras desinfectadas.\n" +
                "Ajusta el riego y mejora la ventilación.\n" +
                "Si sospechas de hongos, aplica un fungicida ecológico.\n" +
                "Si tienes dudas, puedes enviar una foto para diagnóstico más preciso.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (340,'¿Es normal?\n" +
                "Sí, es completamente natural que, tras la floración, la cápsula se seque y adquiera un aspecto marchito.\n" +
                "Esto indica que la planta está madurando sus semillas.\n" +
                "Cuando la cápsula está completamente seca y quebradiza, es el momento ideal para cosechar las semillas.\n" +
                "2. ¿Cuándo es un problema?\n" +
                "Si la cápsula se marchita antes de tiempo (cuando aún está verde, pequeña o blanda), puede indicar:\n" +
                "Falta o exceso de riego.\n" +
                "Estrés ambiental (calor excesivo, viento fuerte).\n" +
                "Ataque de plagas (trips, cochinillas, pulgones).\n" +
                "Enfermedades fúngicas o bacterianas (manchas, moho, podredumbre).','Observa el estado del resto de la planta: Si las hojas y tallos también están marchitos, revisa riego y presencia de plagas.\n" +
                "Revisa cápsulas para signos de moho, manchas o insectos: Si los hay, retira las cápsulas afectadas y mejora ventilación.\n" +
                "Ajusta el riego: Ni exceso ni sequía.\n" +
                "Si la cápsula está seca y sana, cosecha las semillas: Guarda en sitio seco y oscuro.',32)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'Síntomas de falta de riego\n" +
                "Hojas y tallos flácidos, decaídos.\n" +
                "Coloración opaca o amarillenta, especialmente en hojas más viejas.\n" +
                "Flores marchitas o que caen prematuramente.\n" +
                "Detención del crecimiento.\n" +
                "Sustrato seco al tacto y separado de las paredes de la maceta.\n" +
                "Consecuencias\n" +
                "Menor floración y vigor.\n" +
                "En casos extremos, muerte de la planta.','Riega de inmediato, pero de forma gradual para evitar shock.\n" +
                "Revisa el drenaje: asegúrate de que el agua es absorbida correctamente y no se encharque.\n" +
                "Ajusta la frecuencia: riega cuando la superficie del sustrato esté seca, evitando dejarlo seco por periodos prolongados.\n" +
                "Mulching: coloca una capa de mantillo para conservar la humedad.\n" +
                "Evita el riego por aspersión sobre flores y hojas para prevenir enfermedades.\n" +
                "Prevención\n" +
                "Mantén un riego regular, especialmente en épocas calurosas.\n" +
                "Observa la planta para detectar signos tempranos de deshidratación.\n" +
                "Usa un sustrato que drene bien pero retenga algo de humedad.\n',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'El marchitamiento después de la floración en el conejito de los muros (Antirrhinum majus, también conocido como “boca de dragón”) es un fenómeno común y generalmente natural, pero puede tener varias causas:\n" +
                "\n" +
                "1. Ciclo natural de la planta\n" +
                "Tras la floración, especialmente en climas cálidos, la planta puede completar su ciclo anual y empezar a secarse y marchitarse.\n" +
                "Es habitual en variedades anuales o bienales: producen semillas y luego la parte aérea se va secando.\n" +
                "¿Qué hacer?\n" +
                "Deja que las cápsulas de semillas maduren si quieres recolectarlas.\n" +
                "Cuando la planta esté completamente seca, retírala y limpia la zona.\n" +
                "Si quieres nuevas flores, siembra semillas en la siguiente temporada.\n" +
                "2. Factores de estrés\n" +
                "Falta de riego: Después de la floración, la planta necesita menos agua, pero la sequía repentina puede acelerar el marchitamiento.\n" +
                "Exceso de calor: Olas de calor pueden acelerar la senescencia.\n" +
                "Plagas o enfermedades: Hongos, podredumbre, pulgones o trips pueden debilitar la planta antes y después de la floración.\n" +
                "Carencia de nutrientes: Si el suelo está agotado, la planta se debilita más rápido.\n" +
                "3. ¿Cuándo preocuparse?\n" +
                "Si el marchitamiento es muy rápido, con manchas oscuras, pudrición, moho o mal olor, puede ser señal de hongos o bacterias.\n" +
                "Si la planta no llegó a formar semillas, revisa riego, plagas y enfermedades.','Revisa el sustrato y ajusta el riego.\n" +
                "Retira partes marchitas para evitar hongos.\n" +
                "Si hay plagas, actúa con jabón potásico o aceite de neem.\n" +
                "Si la planta ya completó su ciclo, es normal que se seque: recolecta semillas y prepara el terreno para nuevas siembras.',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'¿Qué es la mancha bacteriana?\n" +
                "Es una enfermedad causada por bacterias fitopatógenas, como Pseudomonas o Xanthomonas, que afectan hojas, tallos y, en ocasiones, flores.\n" +
                "\n" +
                "Síntomas principales\n" +
                "Manchas pequeñas, húmedas y oscuras (marrones o negras) en hojas y tallos.\n" +
                "Las manchas pueden tener un halo amarillento alrededor.\n" +
                "Las zonas afectadas pueden volverse translúcidas y, luego, secarse y romperse.\n" +
                "En casos graves, hojas enteras se marchitan y caen.\n" +
                "Si el ambiente es húmedo, puede verse exudado pegajoso.\n" +
                "¿Cómo se propaga?\n" +
                "Salpicaduras de agua (lluvia, riego por aspersión).\n" +
                "Herramientas o manos contaminadas.\n" +
                "Restos de plantas infectadas.','1. Medidas culturales\n" +
                "Retira y destruye las hojas afectadas para evitar que la bacteria se disemine.\n" +
                "Evita mojar el follaje al regar (riego por goteo, no por aspersión).\n" +
                "Asegura buena ventilación entre plantas.\n" +
                "No manipules las plantas cuando estén mojadas.\n" +
                "2. Desinfección\n" +
                "Esteriliza tijeras y herramientas con alcohol o lejía diluida antes y después de usarlas.\n" +
                "3. Fitosanitarios\n" +
                "Los productos de cobre (oxicloruro de cobre) pueden ayudar a frenar la propagación, pero no eliminan la bacteria.\n" +
                "No existen tratamientos específicos para eliminar bacterias, solo para prevenir su avance.\n" +
                "4. Prevención\n" +
                "Usa semillas certificadas y sanas.\n" +
                "Elimina restos vegetales al final de la temporada.\n" +
                "Rota cultivos si es posible.\n" +
                "¿Cuándo consultar más a fondo?\n" +
                "Si la enfermedad avanza muy rápido o afecta a muchas plantas.\n" +
                "Si aparecen síntomas en tallos y flores además de hojas.',36)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'La podrición de la hoja en el “conejito de los muros” (Antirrhinum majus) suele estar asociada a problemas de hongos, exceso de humedad o mala ventilación. A continuación te explico las causas más comunes, cómo identificar el problema y qué hacer:\n" +
                "\n" +
                "Podrición de la hoja en conejito de los muros (Antirrhinum majus)\n" +
                "Causas principales\n" +
                "Hongos (ejemplo: Botrytis, Fusarium, Rhizoctonia)\n" +
                "Aparecen en ambientes húmedos y poco ventilados.\n" +
                "Exceso de riego o drenaje deficiente\n" +
                "Las raíces y el sustrato permanentemente húmedos favorecen hongos y bacterias.\n" +
                "Daño mecánico\n" +
                "Hojas dañadas por viento o insectos pueden ser puerta de entrada para patógenos.\n" +
                "Síntomas típicos\n" +
                "Manchas marrones, negras o grises en hojas, a menudo húmedas y blandas.\n" +
                "Áreas con aspecto acuoso, mal olor o moho visible (gris o blanco).\n" +
                "Hojas que se descomponen fácilmente al tocarlas.\n" +
                "En casos severos, marchitamiento de toda la planta.','Retira y destruye las hojas afectadas inmediatamente, usando tijeras limpias y desinfectadas.\n" +
                "Reduce el riego: deja secar la superficie del sustrato antes de volver a regar.\n" +
                "Mejora la ventilación: separa las plantas y evita la humedad estancada.\n" +
                "No mojes las hojas al regar: riega directamente al sustrato.\n" +
                "Aplica fungicida ecológico (azufre, cobre, extracto de cola de caballo) si el problema persiste.\n" +
                "Limpia restos vegetales caídos para reducir la proliferación de patógenos.\n" +
                "Revisa el drenaje de la maceta o terreno.\n" +
                "Consejo extra:\n" +
                "Si la pudrición es recurrente, considera cambiar el sustrato y desinfectar macetas y herramientas antes de plantar de nuevo.',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'¿Qué son?\n" +
                "Son pequeños insectos que se alimentan perforando los tejidos de la planta y succionando su savia. Suelen atacar hojas, brotes tiernos, flores y tallos.\n" +
                "\n" +
                "Ejemplos comunes\n" +
                "Pulgones (áfidos): Pequeños, verdes, negros, amarillos o rojos; suelen agruparse en brotes y capullos.\n" +
                "Trips: Alargados, muy pequeños, rápidos; dejan manchas plateadas.\n" +
                "Cochinillas: Con o sin escudo; algunas parecen bolitas algodonosas.\n" +
                "Mosca blanca: Pequeños insectos blancos que vuelan al agitar la planta.\n" +
                "Cicadélidos (chicharritas): De color verde o amarillo, saltan cuando se asustan.\n" +
                "Síntomas en la planta\n" +
                "Hojas enrolladas, deformadas o amarillas.\n" +
                "Puntitos claros, manchas pegajosas (melaza) o negrilla (hongo negro sobre la melaza).\n" +
                "Brotes y flores deformados o que no se abren.\n" +
                "Reducción del vigor y floración.\n" +
                "Presencia de colonias visibles de insectos en el envés de las hojas o en brotes.\n" +
                "¿Por qué son un problema?\n" +
                "Debilitan la planta y pueden transmitir enfermedades virales.\n" +
                "La melaza que excretan favorece el desarrollo de hongos.\n" +
                "En infestaciones fuertes pueden causar marchitamiento y pérdida de flores.\n','1. Control manual\n" +
                "Quita los insectos con un chorro de agua o un paño húmedo.\n" +
                "2. Control ecológico\n" +
                "Pulveriza con jabón potásico o aceite de neem cada 5-7 días, insistiendo en el envés de las hojas.\n" +
                "Fomenta insectos benéficos (mariquitas, crisopas, sírfidos).\n" +
                "3. Cultural y preventivo\n" +
                "Ventila bien y evita el exceso de abono nitrogenado.\n" +
                "Elimina partes muy infestadas.\n" +
                "4. Control químico\n" +
                "Solo en casos graves y como última opción, usando productos específicos para insectos chupadores, siguiendo instrucciones y evitando polinizadores.',52)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'La aparición de manchas marrones en el “conejito de los muros” (Antirrhinum majus) puede deberse a varias causas. A continuación te resumo las posibilidades más frecuentes, cómo diferenciarlas y qué hacer:\n" +
                "\n" +
                "Mancha marrón en conejito de los muros (Antirrhinum majus)\n" +
                "1. Enfermedades fúngicas\n" +
                "Síntomas: Manchas marrones irregulares, a veces con halo amarillento o borde definido, que pueden crecer y unirse.\n" +
                "Causas: Hongos como Alternaria, Botrytis, Septoria, etc. Favorecidos por alta humedad, riego sobre hojas y poca ventilación.\n" +
                "Solución:\n" +
                "Retira las hojas afectadas.\n" +
                "Mejora la ventilación y evita mojar el follaje al regar.\n" +
                "Aplica fungicidas ecológicos como azufre, cobre o extractos naturales (cola de caballo).\n" +
                "2. Mancha bacteriana\n" +
                "Síntomas: Manchas marrones oscuras, a veces húmedas o con halo amarillento. Pueden volverse translúcidas y romperse.\n" +
                "Causas: Bacterias (Pseudomonas, Xanthomonas), favorecidas por humedad y salpicaduras.\n" +
                "Solución:\n" +
                "Retira y destruye las hojas afectadas.\n" +
                "Evita mojar el follaje y mejora ventilación.\n" +
                "Desinfecta herramientas y usa fitosanitarios a base de cobre.\n" +
                "3. Quemaduras solares\n" +
                "Síntomas: Manchas marrones secas con borde definido en hojas expuestas al sol directo, sobre todo tras riego o lluvia.\n" +
                "Solución:\n" +
                "Proporciona sombra parcial en las horas más intensas.\n" +
                "Riega directamente al sustrato, no sobre hojas.\n" +
                "4. Carencias o excesos nutricionales\n" +
                "Síntomas: Manchas marrones en bordes o entre nervios, a menudo con amarilleo.\n" +
                "Solución:\n" +
                "Aplica un fertilizante equilibrado y revisa el pH del sustrato.','Observa si las manchas se extienden y si hay otros síntomas (moho, mal olor, caída de hojas).\n" +
                "Retira las partes afectadas con tijeras desinfectadas.\n" +
                "Mejora riego, ventilación y nutrición según la causa sospechada.\n" +
                "Si no mejoras, consulta con foto para un diagnóstico más preciso.',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'La deficiencia de nutrientes en el “conejito de los muros” (Antirrhinum majus) puede manifestarse de varias formas dependiendo del nutriente faltante. Aquí tienes una guía rápida para identificar y corregir estas carencias:\n" +
                "\n" +
                "Deficiencia de nutrientes en conejito de los muros (Antirrhinum majus)\n" +
                "Síntomas según el nutriente\n" +
                "1. Nitrógeno (N)\n" +
                "Hojas inferiores amarillas (clorosis), planta de crecimiento lento y poco vigorosa.\n" +
                "Solución: Fertilizante rico en nitrógeno o abono orgánico (compost).\n" +
                "2. Fósforo (P)\n" +
                "Hojas con tonos púrpuras o rojizos, plantas pequeñas, raíces poco desarrolladas.\n" +
                "Solución: Fertilizante con fósforo (harina de hueso, guano).\n" +
                "3. Potasio (K)\n" +
                "Bordes de las hojas marrones o quemados, manchas necróticas, floración pobre.\n" +
                "Solución: Fertilizante con potasio (ceniza de madera, sulfato de potasio).\n" +
                "4. Magnesio (Mg)\n" +
                "Clorosis entre nervios de hojas viejas, mientras los nervios permanecen verdes.\n" +
                "Solución: Sulfato de magnesio (sales de Epsom).\n" +
                "5. Hierro (Fe)\n" +
                "Clorosis entre nervios en hojas jóvenes, pero los nervios permanecen verdes.\n" +
                "Solución: Quelatos de hierro.\n" +
                "6. Calcio (Ca)\n" +
                "Hojas nuevas deformes o necrosadas, puntas secas, desarrollo radicular pobre.\n" +
                "Solución: Cal agrícola, cáscaras de huevo trituradas.\n','Usa sustratos equilibrados y abona regularmente en temporada de crecimiento.\n" +
                "Mantén un pH del suelo entre 6 y 7.\n" +
                "Riega sin exceso ni carencia.\n" +
                "Observa tus plantas para detectar síntomas tempranos y corrige pronto.',26)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'¿Qué es el envejecimiento en las plantas?\n" +
                "Es el proceso natural por el cual la planta completa su ciclo de vida: germina, crece, florece, produce semillas y finalmente comienza a deteriorarse y secarse.\n" +
                "\n" +
                "Síntomas del envejecimiento\n" +
                "Hojas inferiores amarillas o secas.\n" +
                "Tallos leñosos o quebradizos.\n" +
                "Reducción de la floración o flores más pequeñas.\n" +
                "Marchitamiento general, incluso con riego adecuado.\n" +
                "Mayor susceptibilidad a plagas y enfermedades.\n" +
                "Cápsulas de semillas secas y quebradizas.\n" +
                "¿Por qué ocurre?\n" +
                "La mayoría de los conejitos cultivados son anuales o bienales: tras florecer y dar semillas, la planta termina su ciclo.\n" +
                "El estrés por calor, sequía o enfermedades puede acelerar el envejecimiento.\n" +
                "En plantas perennes viejas, la floración y el vigor se reducen con los años.\n','Si la planta es anual o bienal:\n" +
                "Es normal que se seque tras la floración.\n" +
                "Recolecta semillas si lo deseas.\n" +
                "Arranca la planta seca y limpia el terreno para futuras siembras.\n" +
                "Si es perenne y quieres prolongar su vida:\n" +
                "Realiza podas ligeras tras la floración para estimular nuevos brotes.\n" +
                "Fertiliza moderadamente.\n" +
                "Elimina flores marchitas (“despunte”) para prolongar la floración.\n" +
                "Divide la planta si es muy vieja y densa (menos común en conejito, pero posible).\n" +
                "¿Cómo distinguir envejecimiento de enfermedad?\n" +
                "El envejecimiento ocurre de forma gradual, empezando desde la base.\n" +
                "No suele haber manchas húmedas, moho ni mal olor, a diferencia de hongos o bacterias.\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (350,'Copilot said: La **flor marchita** en el “conejito de\n" +
                "La flor marchita en el “conejito de los muros” (Antirrhinum majus, boca de dragón) es un proceso habitual y puede tener diferentes causas, naturales o relacionadas con el ambiente o el cuidado. Aquí tienes una guía para identificar y manejar el problema:\n" +
                "\n" +
                "Flor marchita en conejito de los muros (Antirrhinum majus)\n" +
                "Causas naturales\n" +
                "Fin del ciclo de la flor: Cada flor tiene una vida útil limitada; al terminar su ciclo, se marchita de forma normal.\n" +
                "Formación de semillas: Las flores marchitas suelen indicar que la planta está formando cápsulas de semillas.\n" +
                "Causas ambientales o de manejo\n" +
                "Falta o exceso de riego: Cambios bruscos de humedad pueden hacer que las flores se marchiten prematuramente.\n" +
                "Calor extremo o viento fuerte: El clima muy caluroso o ventoso deshidrata las flores.\n" +
                "Falta de nutrientes: Suelo pobre o carente de nutrientes puede afectar la duración y calidad de la floración.\n" +
                "Plagas y enfermedades: Insectos chupadores (pulgones, trips) u hongos (Botrytis) pueden causar marchitez y caída de flores.','Despunta (retira) las flores marchitas cortándolas con cuidado. Esto estimula la aparición de nuevas flores y evita el gasto de energía en semillas (si no quieres recolectarlas).\n" +
                "Revisa el riego: Mantén el sustrato húmedo pero sin encharcar.\n" +
                "Fertiliza de manera equilibrada durante la temporada de floración.\n" +
                "Vigila plagas y enfermedades: Si ves insectos o manchas, actúa con jabón potásico o fungicida ecológico.\n" +
                "Mejora el ambiente: Si hay mucho sol o viento, proporciona sombra parcial y resguardo.\n',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'La flor marchita en el clavel de poeta (Dianthus barbatus) es un proceso habitual y puede tener varias causas, tanto naturales como relacionadas con el ambiente o el manejo. Aquí tienes una guía adaptada a esta planta:\n" +
                "\n" +
                "Flor marchita en clavel de poeta (Dianthus barbatus)\n" +
                "Causas naturales\n" +
                "Fin del ciclo floral: Cada flor tiene una vida limitada; al cumplir su ciclo, se marchita de forma normal.\n" +
                "Formación de semillas: Las flores marchitas suelen señalar que la planta está destinando energía a producir semillas.\n" +
                "Causas ambientales o de manejo\n" +
                "Falta o exceso de riego: Cambios bruscos en la humedad pueden marchitar las flores prematuramente.\n" +
                "Calor extremo o viento: Climas muy calurosos o ventosos deshidratan las flores rápidamente.\n" +
                "Suelo pobre o falta de nutrientes: Reduce la duración y calidad de la floración.\n" +
                "Plagas y enfermedades: Insectos chupadores (pulgón, trips) u hongos pueden causar marchitez y caída prematura de flores.','Despunta (retira) las flores marchitas cortándolas con tijeras limpias. Esto potencia el rebrote y la aparición de nuevas flores.\n" +
                "Revisa el riego: Mantén el sustrato húmedo, pero nunca encharcado.\n" +
                "Abona de forma equilibrada durante la temporada de crecimiento y floración.\n" +
                "Vigila plagas y enfermedades: Si ves signos, trata con jabón potásico, aceite de neem o fungicidas ecológicos.\n" +
                "Mejora el ambiente: Si hay mucho sol o viento, proporciona sombra parcial y protección.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'El marchitamiento después de la floración en el clavel de poeta (Dianthus barbatus) es un proceso común y generalmente natural, aunque puede verse influido por varios factores. Aquí tienes un resumen de causas y recomendaciones:\n" +
                "\n" +
                "Causas naturales\n" +
                "Ciclo de vida: El clavel de poeta es una planta bienal o perenne de vida corta. Tras la floración, la planta enfoca su energía en madurar semillas; por eso, las flores y en ocasiones parte de la planta pueden marchitarse.\n" +
                "Formación de semillas: Cuando las flores se marchitan, la planta puede estar formando cápsulas de semillas. Si no se eliminan las flores marchitas (“despunte”), la energía va a semillas en vez de producir nuevas flores.\n" +
                "Factores ambientales y de manejo\n" +
                "Falta o exceso de riego: Cambios bruscos pueden acelerar el marchitamiento.\n" +
                "Olas de calor, viento fuerte o sol intenso: Deshidratan las flores rápidamente.\n" +
                "Suelo agotado: Carencia de nutrientes tras la floración puede debilitar la planta.\n" +
                "Plagas y enfermedades: Pueden acelerar el deterioro tras la floración.\n','Despunta las flores marchitas (córtalas con tijeras limpias). Esto puede estimular nuevas floraciones y mantener la planta más sana.\n" +
                "Revisa el riego para que sea constante pero no excesivo.\n" +
                "Abona después de la floración para recuperar nutrientes perdidos.\n" +
                "Vigila plagas y enfermedades en tallos y hojas.\n" +
                "Si la planta termina su ciclo y se seca completamente, puedes recolectar semillas y sembrar nuevas plantas para la próxima temporada.\n',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'La aparición de manchas marrones en el clavel de poeta (Dianthus barbatus) puede deberse a varias causas, principalmente enfermedades fúngicas, pero también a problemas bacterianos, quemaduras o carencias nutricionales. Aquí tienes una guía para identificar la causa y posibles soluciones:\n" +
                "\n" +
                "Mancha marrón en clavel de poeta (Dianthus barbatus)\n" +
                "1. Enfermedades fúngicas\n" +
                "Síntomas: Manchas marrones irregulares, a veces con halo amarillento o borde definido, que pueden crecer, unirse o tener centro seco.\n" +
                "Hongos comunes: Alternaria, Septoria, Botrytis, Cladosporium.\n" +
                "Condiciones favorables: Alta humedad, riegos sobre las hojas, ventilación insuficiente.\n" +
                "Solución:\n" +
                "Retira y destruye las hojas afectadas.\n" +
                "Mejora la ventilación y evita mojar el follaje.\n" +
                "Aplica fungicidas ecológicos (azufre, extracto de cola de caballo, cobre).\n" +
                "2. Mancha bacteriana\n" +
                "Síntomas: Manchas marrones oscuras, a veces húmedas, con halo amarillento y aspecto traslúcido.\n" +
                "Causa: Bacterias como Xanthomonas.\n" +
                "Solución:\n" +
                "Retira las partes afectadas.\n" +
                "Desinfecta herramientas.\n" +
                "Reduce la humedad ambiental.\n" +
                "Usa productos a base de cobre.\n" +
                "3. Quemaduras solares o químicas\n" +
                "Síntomas: Manchas marrones secas con bordes definidos, especialmente en hojas expuestas al sol o tras aplicar productos en horas de calor.\n" +
                "Solución: Riega al pie de la planta y aplica productos en horas frescas.\n" +
                "4. Deficiencia nutricional\n" +
                "Síntomas: Manchas marrones en bordes o entre nervios, acompañadas de amarilleo.\n" +
                "Solución: Fertiliza con un abono equilibrado y revisa el pH del suelo.\n','Observa si las manchas se extienden y si hay otros síntomas (moho, caída de hojas, mal olor).\n" +
                "Retira las hojas y flores afectadas con tijeras desinfectadas.\n" +
                "Mejora el riego, la ventilación y la nutrición según la causa sospechada.\n" +
                "Aplica tratamientos apropiados (fungicidas o bactericidas ecológicos si es necesario).',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'El tizón del pétalo en el clavel de poeta (Dianthus barbatus) es una enfermedad causada principalmente por hongos, y puede afectar gravemente la apariencia y salud de las flores. Aquí tienes una guía completa sobre el tema:\n" +
                "\n" +
                "Tizón del pétalo en clavel de poeta (Dianthus barbatus)\n" +
                "Causa principal\n" +
                "El hongo más frecuente es Alternaria dianthi y, en menor medida, Botrytis cinerea (moho gris). Ambos pueden atacar los pétalos, especialmente en ambientes húmedos y con poca ventilación.\n" +
                "Síntomas\n" +
                "Manchas pequeñas marrones, rojizas o negruzcas en los pétalos, que pueden aumentar de tamaño y unirse.\n" +
                "Los pétalos afectados se marchitan, se tornan blandos y pueden pudrirse.\n" +
                "En casos graves, toda la flor se vuelve marrón y cae prematuramente.\n" +
                "En infecciones por Botrytis puede observarse un moho gris sobre las flores.','Retira y destruye las flores y partes afectadas lo antes posible.\n" +
                "Evita mojar los pétalos al regar; riega al pie de la planta.\n" +
                "Mejora la ventilación y separa las plantas para reducir humedad.\n" +
                "No dejes restos de flores caídas en el suelo.\n" +
                "Aplica fungicidas preventivos ecológicos como extracto de cola de caballo, azufre o cobre, especialmente si hay condiciones favorables para la enfermedad.\n" +
                "Desinfecta las herramientas después de usarlas en plantas enfermas.\n" +
                "Consejo extra\n" +
                "Si el problema es recurrente, siembra variedades resistentes y rota cultivos para evitar acumulación de esporas en el suelo.',24)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'Las orugas son una de las plagas más comunes que pueden afectar al clavel de poeta (Dianthus barbatus) y a muchas otras plantas ornamentales y de jardín. Aquí tienes información útil para identificarlas y manejarlas:\n" +
                "\n" +
                "Orugas en clavel de poeta (Dianthus barbatus)\n" +
                "¿Qué son?\n" +
                "Son las larvas de mariposas o polillas. Se alimentan de hojas, brotes tiernos y a veces flores, causando daños visibles.\n" +
                "Síntomas de infestación\n" +
                "Hojas con agujeros o bordes irregulares, a veces solo quedan los nervios.\n" +
                "Presencia de excrementos pequeños y oscuros sobre las hojas o el suelo.\n" +
                "Brotes y flores dañados o parcialmente comidos.\n" +
                "Orugas visibles en el envés de las hojas o entre los pétalos.','1. Revisión manual\n" +
                "Busca orugas en las plantas, especialmente al atardecer o en la mañana.\n" +
                "Retíralas manualmente y destrúyelas si la infestación es pequeña.\n" +
                "2. Control biológico\n" +
                "Fomenta la presencia de aves insectívoras, avispas parasitoides y otros depredadores naturales.\n" +
                "Puedes usar productos a base de Bacillus thuringiensis (Bt), un bioinsecticida selectivo para orugas, seguro para personas y animales.\n" +
                "3. Control ecológico\n" +
                "Rocía infusiones de ajo, ají o neem sobre las plantas como repelente natural.\n" +
                "Mantén el área libre de malezas y restos vegetales donde puedan esconderse las orugas adultas.\n" +
                "4. Control químico\n" +
                "Solo en casos graves y como última medida, aplica insecticidas específicos para orugas, siguiendo las instrucciones del fabricante y evitando dañar polinizadores.\n" +
                "Consejo extra\n" +
                "Revisa frecuentemente tus plantas, ya que una detección temprana facilita el control sin recurrir a químicos fuertes.',25)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'La falta de riego en el clavel de poeta (Dianthus barbatus) provoca síntomas claros y puede afectar tanto la floración como la salud general de la planta. Aquí tienes cómo identificarla, sus consecuencias y qué hacer:\n" +
                "\n" +
                "Síntomas de falta de riego en clavel de poeta\n" +
                "Hojas lacias o caídas: Las hojas pierden turgencia y se ven decaídas.\n" +
                "Puntas secas o marrones: Primeras zonas en secarse suelen ser las puntas y bordes de las hojas.\n" +
                "Marchitamiento general: La planta puede verse decaída, especialmente en las horas más calurosas.\n" +
                "Flores marchitas o secas: Las flores se marchitan prematuramente y pueden caerse.\n" +
                "Sustrato seco: Al meter el dedo en la tierra, estará seca varios centímetros hacia abajo.\n" +
                "Consecuencias\n" +
                "Menor floración o flores pequeñas.\n" +
                "Mayor susceptibilidad a plagas y enfermedades.\n" +
                "En casos graves, muerte de la planta.\n','Riega de inmediato, empapando bien el sustrato pero evitando encharcamientos.\n" +
                "Revisa la frecuencia de riego: En épocas de calor o viento, el clavel de poeta necesita riegos más frecuentes.\n" +
                "Mantén el sustrato húmedo, no empapado: Deja que la tierra se seque ligeramente antes de volver a regar.\n" +
                "Acolcha con corteza, paja o compost para mantener la humedad y proteger las raíces.\n" +
                "Evita mojar el follaje para prevenir enfermedades.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'La quemadura de las hojas en el clavel de poeta (Dianthus barbatus) es un problema relativamente común y puede deberse a varias causas, principalmente ambientales o de manejo. Aquí tienes cómo identificarla, las causas más probables y cómo prevenirla o tratarla:\n" +
                "\n" +
                "Quemadura de las hojas en clavel de poeta (Dianthus barbatus)\n" +
                "Síntomas\n" +
                "Bordes de las hojas marrones, secos o crujientes.\n" +
                "Manchas pardas o blanquecinas, a veces translúcidas, sobre la superficie de la hoja.\n" +
                "En casos graves, toda la hoja puede secarse y caer.\n" +
                "Las áreas dañadas suelen estar en la parte superior o más expuesta de la planta.\n" +
                "Causas más frecuentes\n" +
                "Exceso de sol o calor (quemadura solar)\n" +
                "\n" +
                "Ocurre cuando la planta está expuesta a sol intenso, especialmente en horas centrales del día.\n" +
                "Más común si la planta no está acostumbrada a sol directo o tras una poda severa.\n" +
                "Riego o pulverización en horas de sol\n" +
                "\n" +
                "Las gotas de agua actúan como lentes y concentran la luz solar, quemando el tejido foliar.\n" +
                "Viento seco o corrientes de aire\n" +
                "\n" +
                "Deshidratan rápidamente las hojas, causando quemaduras en los bordes.\n" +
                "Exceso de fertilizante (quema química)\n" +
                "\n" +
                "Fertilizantes en exceso, especialmente los ricos en sales, pueden quemar raíces y hojas.\n" +
                "Carencia de agua\n" +
                "\n" +
                "La falta de agua hace que las hojas se sequen y mueran desde los bordes.\n','Ubicación: Si es posible, mueve la planta a un lugar donde reciba sol suave o sombra parcial en las horas más calurosas.\n" +
                "Riego: Riega temprano por la mañana o al atardecer, evitando mojar las hojas.\n" +
                "Fertilización: Usa abonos equilibrados y nunca excedas las dosis recomendadas.\n" +
                "Protección: Durante olas de calor o viento fuerte, proporciona algún tipo de protección temporal (pantallas, mallas de sombreo).\n" +
                "Poda: Retira las hojas muy dañadas para evitar infecciones secundarias.',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'El escarabajo de las hojas es una plaga frecuente en muchas plantas ornamentales y hortícolas, incluyendo el clavel de poeta (Dianthus barbatus). Aquí tienes información útil para identificarlo y controlarlo:\n" +
                "\n" +
                "Escarabajo de las hojas en clavel de poeta (Dianthus barbatus)\n" +
                "¿Qué es?\n" +
                "Son insectos pequeños, generalmente de forma ovalada o alargada y colores brillantes (amarillos, verdes, marrones o metálicos), que pertenecen a la familia Chrysomelidae. Se alimentan de las hojas, tanto en estado adulto como larval.\n" +
                "\n" +
                "Síntomas de infestación\n" +
                "Hojas con agujeros redondeados o irregulares.\n" +
                "Daños en los bordes de las hojas o entre los nervios.\n" +
                "Presencia de pequeños escarabajos sobre las hojas, a menudo activos durante el día.\n" +
                "En algunas especies, las larvas también se alimentan de raíces o tallos jóvenes.','1. Revisión manual\n" +
                "Inspecciona las plantas regularmente, especialmente en primavera y verano.\n" +
                "Retira manualmente los escarabajos adultos y larvas si la infestación es pequeña.\n" +
                "2. Control biológico\n" +
                "Fomenta la presencia de depredadores naturales como aves, mariquitas y crisopas.\n" +
                "3. Control ecológico\n" +
                "Rocía las plantas con infusión de ajo, jabón potásico o extracto de neem, que actúan como repelentes y dificultan la alimentación de los escarabajos.\n" +
                "Elimina las malas hierbas y restos vegetales donde puedan refugiarse.\n" +
                "4. Control químico\n" +
                "Solo si la plaga es muy severa y como último recurso, usa insecticidas específicos para escarabajos de las hojas siguiendo las indicaciones del fabricante. Hazlo en las horas menos activas de los polinizadores.\n" +
                "Consejo extra\n" +
                "Mantén el jardín bien cuidado y evita el exceso de abono nitrogenado, que favorece brotes tiernos y atrae más plagas.\n',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'Las cicatrices en el clavel de poeta (Dianthus barbatus) suelen referirse a marcas o lesiones permanentes en hojas, tallos o flores. Estas cicatrices pueden deberse a distintos factores, y aunque generalmente no ponen en riesgo inmediato la vida de la planta, sí indican que ha sufrido algún tipo de daño. Aquí tienes una guía para identificar su origen y cómo manejar la situación:\n" +
                "\n" +
                "Cicatrices en clavel de poeta (Dianthus barbatus)\n" +
                "Causas comunes\n" +
                "Daños mecánicos\n" +
                "\n" +
                "Causados por viento fuerte, granizo, manipulación brusca o contacto con herramientas de jardinería.\n" +
                "Aparecen como cortes, raspaduras o zonas hundidas que posteriormente se secan y quedan como marcas.\n" +
                "Ataques de plagas\n" +
                "\n" +
                "Orugas, escarabajos, babosas y caracoles pueden comer partes de la hoja, dejando bordes irregulares que al cicatrizar se ven secos o corchosos.\n" +
                "Chupadores como pulgones pueden dejar pequeñas cicatrices puntiformes.\n" +
                "Enfermedades\n" +
                "\n" +
                "Algunas infecciones fúngicas o bacterianas dejan manchas que, al secarse, forman una costra o tejido endurecido (necrosis).\n" +
                "Las zonas afectadas pueden caerse, dejando agujeros o marcas.\n" +
                "Quemaduras\n" +
                "\n" +
                "Por sol, viento, productos químicos o exceso de fertilizante, dejan áreas secas y de color marrón o blanquecino que luego cicatrizan.\n" +
                "Cicatriz natural\n" +
                "\n" +
                "Las flores y hojas caídas o podadas dejan pequeñas marcas en los tallos, que se cierran y forman una cicatriz.','Identifica la causa: Observa si hay presencia de plagas, si las cicatrices siguen apareciendo, o si pueden ser por daño físico o quemaduras.\n" +
                "Mantén la planta sana: Un clavel de poeta vigoroso cicatriza mejor. Riega y fertiliza adecuadamente.\n" +
                "Elimina plagas y prevén daños: Usa barreras físicas, control biológico o insecticidas ecológicos si es necesario.\n" +
                "Evita heridas innecesarias: Manipula la planta con cuidado y poda solo con herramientas limpias y afiladas.\n" +
                "Retira hojas muy dañadas: Para prevenir infecciones secundarias.\n',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (360,'El envejecimiento en el clavel de poeta (Dianthus barbatus) es un proceso natural por el cual la planta, sus hojas y flores van perdiendo vigor y vitalidad con el tiempo. Aquí tienes información relevante sobre el envejecimiento de esta planta, cómo identificarlo, y recomendaciones para prolongar su vida y belleza:\n" +
                "\n" +
                "Envejecimiento en clavel de poeta (Dianthus barbatus)\n" +
                "Características del envejecimiento\n" +
                "Ciclo de vida: El clavel de poeta es una planta bienal (vive dos años) o perenne de vida corta.\n" +
                "En el primer año, desarrolla hojas y raíces.\n" +
                "En el segundo año, florece, produce semillas y suele morir después.\n" +
                "Síntomas de envejecimiento:\n" +
                "Hojas amarillas, secas o marchitas, especialmente en la base.\n" +
                "Tallos leñosos, menos flexibles.\n" +
                "Disminución en el número y tamaño de las flores.\n" +
                "Floración más pobre y menos intensa.\n" +
                "Mayor susceptibilidad a plagas, enfermedades y estrés ambiental.\n" +
                "Formación de semillas y muerte progresiva de la planta tras la floración.','Despunta flores marchitas: Esto puede prolongar la floración y retrasar la formación de semillas.\n" +
                "Abona y riega adecuadamente: Aporta nutrientes y agua, especialmente tras la floración.\n" +
                "Multiplica la planta: Aprovecha para recolectar semillas y sembrar nuevas plantas para la siguiente temporada.\n" +
                "Divide o renueva: Si tienes ejemplares perennes, puedes dividir las matas jóvenes para rejuvenecer el macizo.\n" +
                "Elimina partes secas: Retira hojas y tallos secos para evitar enfermedades y mejorar la apariencia.\n" +
                "Rota el lugar de siembra: Cambia la ubicación cada año para evitar acumulación de enfermedades en el suelo.\n" +
                "Consejo práctico\n" +
                "El envejecimiento es un proceso natural, pero con buenos cuidados puedes disfrutar de tu clavel de poeta durante más tiempo y asegurar una floración continua año tras año, renovando la plantación a partir de semillas o esquejes.\n" +
                "\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'El marchitamiento del clavel del aire (Tillandsia spp.) puede ser preocupante, pero sus causas y soluciones son diferentes a las de otras plantas. Aquí tienes una guía para identificar el problema y actuar:\n" +
                "\n" +
                "Marchitamiento en clavel del aire (Tillandsia)\n" +
                "Síntomas\n" +
                "Hojas blandas, arrugadas o enrolladas hacia adentro.\n" +
                "Color apagado o amarillento.\n" +
                "Pérdida de turgencia general (“falta de vida”).\n" +
                "Hojas secas en la base (cierto grado es normal, pero si avanza mucho, es señal de problema).\n" +
                "Caída prematura de hojas.\n" +
                "Causas más comunes\n" +
                "Falta de agua/humedad\n" +
                "\n" +
                "Tillandsias absorben agua por las hojas, no por raíces.\n" +
                "Riego insuficiente o ambiente muy seco causa deshidratación y marchitamiento.\n" +
                "Solución: Sumerge la planta en agua (no clorada) durante 20-30 minutos 1-2 veces por semana (ajusta según clima). Deja secar completamente antes de volver a colocarla.\n" +
                "Exceso de agua\n" +
                "\n" +
                "Si la planta permanece húmeda mucho tiempo, puede pudrirse la base o el centro, lo que también causa marchitamiento.\n" +
                "Solución: Asegura buena ventilación y que las hojas sequen completamente después del riego.\n" +
                "Falta de luz\n" +
                "\n" +
                "Luz insuficiente debilita la planta y la hace más propensa a marchitarse.\n" +
                "Solución: Proporciónale luz brillante indirecta, evita sol directo intenso y prolongado.\n" +
                "Ambiente cerrado/sin ventilación\n" +
                "\n" +
                "El aire estancado favorece hongos y evita el secado rápido tras el riego.\n" +
                "Solución: Colócala en un lugar bien ventilado.\n" +
                "Envejecimiento natural\n" +
                "\n" +
                "Si la planta ya floreció, es normal que comience a secarse mientras produce hijuelos (nuevas plantas).','Ajusta la frecuencia de riego según el clima (más en calor/sequedad, menos en frío/humedad).\n" +
                "Usa agua de lluvia, filtrada o reposada (sin cloro).\n" +
                "Revisa que no haya pudrición en la base o centro.\n" +
                "Retira hojas completamente secas o podridas.\n" +
                "Si tienes hijuelos, sepáralos y cultiva aparte.',10)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'El marchitamiento de la punta de las hojas en el clavel del aire (Tillandsia) es un síntoma frecuente y puede deberse a varias causas, generalmente relacionadas con el ambiente, el riego o la salud general de la planta. Aquí te explico las causas más comunes y cómo solucionarlo:\n" +
                "\n" +
                "Marchitamiento de la punta de las hojas en clavel del aire (Tillandsia)\n" +
                "Causas principales\n" +
                "Falta de agua / humedad insuficiente\n" +
                "\n" +
                "Las puntas se secan, se arrugan o adquieren un tono marrón/gris.\n" +
                "Solución: Aumenta la frecuencia de los baños o pulverizaciones, especialmente en ambientes secos. Sumerge la planta en agua no clorada durante 20-30 minutos una vez por semana y deja secar completamente.\n" +
                "Exceso de sol o quemadura solar\n" +
                "\n" +
                "Las puntas se tornan marrones/blanquecinas y secas, especialmente si la planta está bajo sol directo intenso.\n" +
                "Solución: Coloca la planta en un sitio con luz brillante pero filtrada, evitando el sol directo fuerte.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "Las puntas se queman y secan por acumulación de sales.\n" +
                "Solución: Fertiliza solo una vez al mes (en época de crecimiento) con fertilizante diluido específico para bromelias o plantas epífitas.\n" +
                "Ventilación insuficiente\n" +
                "\n" +
                "El estancamiento de aire favorece la descomposición de las puntas y tejidos.\n" +
                "Solución: Ubica la planta en un lugar con buena circulación de aire.\n" +
                "Envejecimiento natural\n" +
                "\n" +
                "Es normal que algunas hojas más viejas sequen sus puntas al envejecer.\n" +
                "Solución: Retira puntas secas solo si afectan la estética, nunca cortes demasiado.\n','Revisa el ambiente: Evalúa luz, humedad y ventilación.\n" +
                "Ajusta el riego: Si el clima es seco o caluroso, aumenta la frecuencia de baños o nebulizaciones.\n" +
                "Evita el sol directo fuerte: Prefiere luz brillante e indirecta.\n" +
                "Reduce o suspende fertilizantes: Si has abonado recientemente.\n" +
                "Elimina puntas secas: Usa tijeras limpias para cortar solo la parte seca, sin dañar el tejido sano.\n',49)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'La flor marchita en el clavel del aire (Tillandsia) es un proceso natural y esperado, pero también puede indicar algún problema de cultivo si ocurre de manera prematura. Aquí tienes las causas más comunes y cómo actuar:\n" +
                "\n" +
                "Flor marchita en clavel del aire (Tillandsia)\n" +
                "Causas naturales\n" +
                "Ciclo vital:\n" +
                "Las Tillandsias solo florecen una vez en su vida (son monocárpicas). Tras la floración, la flor se marchita y la planta madre comienza un proceso de envejecimiento mientras genera hijuelos (nuevas plantas en la base).\n" +
                "Duración de la flor:\n" +
                "La flor de Tillandsia suele durar entre unos días y varias semanas, dependiendo de la especie y las condiciones ambientales.\n" +
                "Causas de flor marchita prematura\n" +
                "Falta o exceso de agua:\n" +
                "Si la planta no ha recibido suficiente humedad, la flor puede secarse antes de tiempo. El exceso de humedad puede favorecer hongos, acelerando el marchitamiento.\n" +
                "Falta de luz:\n" +
                "Luz insuficiente debilita la flor y acorta su vida útil.\n" +
                "Temperaturas extremas:\n" +
                "Mucho calor o frío pueden provocar marchitez anticipada.\n" +
                "Estrés (transplante, cambios bruscos):\n" +
                "El movimiento o cambios repentinos de ambiente pueden afectar la floración.','No retires la flor marchita de inmediato:\n" +
                "Espera a que se seque completamente. Si la planta está produciendo hijuelos, deja que el ciclo siga su curso.\n" +
                "Asegura buena humedad y ventilación:\n" +
                "Pulveriza o baña la planta según tu clima, y deja secar completamente entre riegos.\n" +
                "Luz brillante e indirecta:\n" +
                "Evita sol directo fuerte, pero proporciona buena iluminación.\n" +
                "Retira la flor seca cuando esté totalmente marchita:\n" +
                "Puedes cortarla cuidadosamente para mejorar la apariencia y evitar hongos.\n" +
                "Cuida los hijuelos:\n" +
                "Cuando alcancen un tercio del tamaño de la planta madre, puedes separarlos para obtener nuevas Tillandsias.\n',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'La pudrición de la hoja en el clavel del aire (Tillandsia) es un problema serio que puede afectar la salud y supervivencia de la planta. A continuación te detallo las causas, cómo identificarla y qué hacer para solucionarla:\n" +
                "\n" +
                "Pudrición de la hoja en clavel del aire (Tillandsia)\n" +
                "Síntomas\n" +
                "Hojas blandas, oscuras, translúcidas o con manchas negras/marrones.\n" +
                "Tejido que se deshace al tocarlo.\n" +
                "Mal olor proveniente de la planta.\n" +
                "La base de la hoja, donde se unen al centro, suele estar más afectada.\n" +
                "En casos avanzados, la pudrición se extiende al centro y puede matar la planta entera.\n" +
                "Causas principales\n" +
                "Exceso de agua y mala ventilación\n" +
                "\n" +
                "Tillandsias necesitan secarse completamente después del riego. Si quedan húmedas mucho tiempo, los hongos y bacterias prosperan y causan pudrición.\n" +
                "Problema común cuando se riegan muy seguido, se sumergen por mucho tiempo o se mantienen en ambientes cerrados.\n" +
                "Acumulación de agua en el centro de la planta\n" +
                "\n" +
                "El agua estancada en la roseta o base de las hojas favorece la aparición de hongos y bacterias.\n" +
                "Temperaturas bajas y humedad alta\n" +
                "\n" +
                "El frío y la humedad favorecen el desarrollo de patógenos.\n" +
                "Daños previos en la hoja\n" +
                "\n" +
                "Heridas o cortes pueden ser puerta de entrada para infecciones.\n','¿Qué hacer si hay pudrición?\n" +
                "Aísla la planta para evitar contagio a otras Tillandsias.\n" +
                "Retira cuidadosamente las partes podridas usando tijeras limpias y desinfectadas.\n" +
                "Deja secar la planta en un lugar bien ventilado y luminoso, evitando humedad excesiva.\n" +
                "Evita regar hasta que esté completamente seca y observes recuperación.\n" +
                "Desinfecta la herramienta y tus manos después de manipular la planta.\n" +
                "Si la pudrición es avanzada y llega al centro, generalmente la planta no podrá recuperarse, pero puedes intentar salvar hijuelos sanos si los hay.\n" +
                "Prevención\n" +
                "Riega solo cuando la planta y el ambiente estén secos.\n" +
                "Después de sumergir o mojar, sacude el exceso de agua y coloca la planta en un lugar con buena ventilación y luz.\n" +
                "Nunca dejes agua acumulada en el centro de la planta.\n" +
                "Evita regar en días muy fríos o húmedos.\n" +
                "Limpia regularmente las hojas y revisa posibles lesiones.\n',14)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'¿Qué es un cerambícido?\n" +
                "Son escarabajos caracterizados por sus largas antenas, a menudo más largas que su cuerpo.\n" +
                "Las larvas suelen alimentarse de madera, perforando tallos y troncos de árboles o plantas leñosas.\n" +
                "Los adultos pueden encontrarse sobre flores, madera o plantas, y algunas especies se alimentan de polen, néctar o savia.\n" +
                "¿Afectan los cerambícidos al clavel del aire?\n" +
                "Tillandsia (clavel del aire) generalmente no es un hospedero habitual de cerambícidos, ya que no tiene madera ni tallos gruesos.\n" +
                "Sin embargo, en ambientes donde hay mucha materia vegetal en descomposición o madera, pueden encontrarse adultos cerca o sobre las plantas, aunque raramente causan daño directo.\n" +
                "Si encuentras un cerambícido sobre tu clavel del aire, probablemente esté de paso o buscando refugio, no alimentándose de la planta.','¿Qué hacer si ves cerambícidos en tus Tillandsias?\n" +
                "Observa si hay daño real:\n" +
                "Si solo ves al adulto y no hay orificios ni daño, no es necesario intervenir.\n" +
                "Revisa el entorno:\n" +
                "Si tienes madera vieja, troncos o ramas cerca de tus Tillandsias, ahí podrían estar las larvas.\n" +
                "Control manual:\n" +
                "Retira los escarabajos adultos si te preocupan, pero no suelen representar una amenaza.\n" +
                "Prevención:\n" +
                "Mantén el área libre de madera en descomposición si no deseas atraer cerambícidos.\n" +
                "Resumen\n" +
                "Los cerambícidos rara vez dañan Tillandsias.\n" +
                "Su presencia suele estar relacionada con madera o materia vegetal cercana.\n" +
                "No requieren control específico en clavel del aire, salvo casos excepcionales.\n',43)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'La mancha marrón en las hojas del clavel del aire (Tillandsia) es un síntoma común que puede deberse a varias causas. Aquí tienes las principales posibilidades, cómo diferenciarlas y qué hacer en cada caso:\n" +
                "\n" +
                "Manchas marrones en Tillandsia (clavel del aire)\n" +
                "Causas más frecuentes\n" +
                "Quemaduras solares\n" +
                "\n" +
                "Manchas marrones secas, a veces rodeadas por un halo blanquecino.\n" +
                "Aparecen tras exposición al sol directo intenso, especialmente en horas centrales o tras cambiar la planta de lugar.\n" +
                "Solución: Coloca la planta en luz brillante filtrada, evita el sol directo fuerte.\n" +
                "Daño por riego inadecuado\n" +
                "\n" +
                "Manchas marrones blandas o con aspecto de pudrición.\n" +
                "Ocurren por agua estancada en las hojas o base, o por falta de secado tras el riego.\n" +
                "Solución: Asegura buena ventilación y que la planta se seque por completo después de cada riego.\n" +
                "Falta de agua/problemas de sequedad\n" +
                "\n" +
                "Manchas marrones secas en las puntas o márgenes de las hojas, acompañadas de arrugas.\n" +
                "Solución: Aumenta la humedad ambiental y la frecuencia de riego, pero deja secar bien entre riegos.\n" +
                "Daños mecánicos o golpes\n" +
                "\n" +
                "Manchas marrones localizadas donde la planta ha sido lastimada.\n" +
                "Solución: Evita golpes y manipulación brusca.\n" +
                "Enfermedades fúngicas o bacterianas\n" +
                "\n" +
                "Manchas marrones que crecen, a veces con un halo amarillento o aspecto húmedo.\n" +
                "Solución: Retira las hojas afectadas, mejora la ventilación y reduce la humedad. Si el problema es grave, utiliza un fungicida específico para bromelias.\n" +
                "Exceso de fertilizante\n" +
                "\n" +
                "Manchas marrones localizadas, a menudo en las puntas.\n" +
                "Solución: Reduce o suspende el fertilizante y enjuaga la planta con agua limpia.','Observa la textura:\n" +
                "Si la mancha es seca → probablemente quemadura o sequedad.\n" +
                "Si es blanda o húmeda → posible pudrición o enfermedad.\n" +
                "Revisa el entorno:\n" +
                "¿Recibió sol directo? ¿Hay buena ventilación? ¿Se seca bien tras el riego?\n" +
                "Retira hojas muy dañadas:\n" +
                "Usa tijeras limpias y desinfectadas.\n" +
                "Adapta el ambiente:\n" +
                "Luz filtrada, riego adecuado, buena aireación.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'El envejecimiento en el clavel del aire (Tillandsia) es un proceso natural y parte del ciclo de vida de estas plantas. Aquí tienes una explicación sobre cómo ocurre, qué observar y qué hacer para prolongar la salud de tus Tillandsias:\n" +
                "\n" +
                "Envejecimiento en clavel del aire (Tillandsia)\n" +
                "¿Cómo envejece una Tillandsia?\n" +
                "Ciclo vital:\n" +
                "La mayoría de las Tillandsias son monocárpicas: florecen una sola vez en su vida. Tras la floración, la planta madre entra en un proceso de envejecimiento y eventual muerte, pero antes suele producir hijuelos (pequeñas plantas en la base).\n" +
                "Síntomas del envejecimiento:\n" +
                "Hojas de la base que se secan y caen progresivamente.\n" +
                "Reducción del crecimiento y vigor.\n" +
                "Coloración más opaca o amarillenta.\n" +
                "Aparición de hijuelos al pie de la planta madre.\n" +
                "La planta madre puede marchitarse mientras los hijuelos crecen.','Permite que la planta complete su ciclo:\n" +
                "Es natural que la planta madre decline después de florecer.\n" +
                "Cuida los hijuelos:\n" +
                "Cuando los hijuelos alcancen aproximadamente un tercio del tamaño de la madre, puedes separarlos cuidadosamente y cultivarlos como nuevas plantas.\n" +
                "Mantén buenos cuidados:\n" +
                "Continúa con riegos, buena ventilación y luz adecuada para estimular el crecimiento de los hijuelos.\n" +
                "Retira hojas secas:\n" +
                "Elimina con cuidado las hojas secas y partes muertas para evitar infecciones y mejorar la apariencia.\n" +
                "No fertilices en exceso:\n" +
                "El exceso de fertilizante no detendrá el envejecimiento natural y puede dañar la planta.\n" +
                "Consejo práctico\n" +
                "¡El envejecimiento es una oportunidad! Cada Tillandsia que termina su ciclo regala nuevas plantas para seguir disfrutando.\n" +
                "\n',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'La falta de riego en el clavel del aire (Tillandsia) es una de las causas más frecuentes de problemas en estas plantas epífitas. A continuación te indico cómo reconocer los síntomas, qué consecuencias tiene y cómo recuperarla:\n" +
                "\n" +
                "Falta de riego en clavel del aire (Tillandsia)\n" +
                "Síntomas principales\n" +
                "Hojas arrugadas, blandas o enrolladas hacia adentro.\n" +
                "Puntas secas y marrones.\n" +
                "Color general más opaco, grisáceo o amarillento.\n" +
                "Reducción del crecimiento.\n" +
                "Caída de hojas viejas.\n" +
                "Floración pobre o nula.\n" +
                "Consecuencias\n" +
                "Deshidratación progresiva de la planta.\n" +
                "Pérdida de vigor y capacidad de producir hijuelos.\n" +
                "Si la sequía es severa y prolongada, la planta puede morir.','Recuperación rápida:\n" +
                "\n" +
                "Sumerge la planta en agua no clorada (de lluvia, destilada o reposada) durante 20-30 minutos.\n" +
                "Después, sacude el exceso de agua y deja secar en un sitio ventilado y luminoso (nunca al sol directo intenso).\n" +
                "Frecuencia de riego:\n" +
                "\n" +
                "En climas cálidos y secos: 2-3 veces por semana.\n" +
                "En climas húmedos o fríos: 1 vez por semana o cada 10 días.\n" +
                "Siempre deja que la planta se seque completamente entre riegos.\n" +
                "Pulverización:\n" +
                "\n" +
                "Si el ambiente es muy seco, puedes pulverizar agua sobre las hojas entre riegos profundos.\n" +
                "Evita el agua con cloro:\n" +
                "\n" +
                "El cloro y otras sales pueden dañar las Tillandsias. Usa agua de lluvia, filtrada o reposada 24 horas.\n" +
                "Consejo práctico\n" +
                "Observa el aspecto de las hojas para ajustar la frecuencia de riego.\n" +
                "Si la planta se recupera, vuelve a tu rutina de riego habitual.\n" +
                "Si no mejora, revisa otros factores como luz, ventilación o posibles enfermedades.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'La quemadura de hojas en el clavel del aire (Tillandsia) es un problema común, especialmente cuando la planta está expuesta a condiciones ambientales demasiado intensas. Aquí te explico cómo identificarla, sus causas y cómo prevenirla:\n" +
                "\n" +
                "Quemadura de hojas en clavel del aire (Tillandsia)\n" +
                "¿Cómo reconocer una quemadura de hojas?\n" +
                "Manchas secas de color marrón claro, gris o blanquecino en las hojas.\n" +
                "Bordes o puntas de hojas secos o quebradizos.\n" +
                "Áreas decoloradas, blanquecinas o translúcidas (a veces con apariencia de vidrio esmerilado).\n" +
                "El daño suele aparecer en el lado de la planta más expuesto al sol o cerca de fuentes de calor.\n" +
                "Causas principales\n" +
                "Exposición directa al sol intenso:\n" +
                "Las Tillandsias prefieren luz brillante pero filtrada. El sol directo, especialmente en las horas centrales del día, puede quemar sus hojas.\n" +
                "\n" +
                "Proximidad a fuentes de calor o viento seco:\n" +
                "Estufas, calefactores, aire acondicionado o corrientes de aire caliente pueden secar y quemar las hojas.\n" +
                "\n" +
                "Gotas de agua como lupa:\n" +
                "Si quedan gotas de agua sobre las hojas bajo el sol, pueden concentrar la luz y causar quemaduras puntuales (efecto lupa).\n" +
                "\n','Retira cuidadosamente las partes muy dañadas con tijeras limpias, cortando solo el tejido seco o muerto.\n" +
                "Reubica la planta en un sitio con luz brillante pero indirecta o filtrada (por ejemplo, cerca de una ventana con cortina).\n" +
                "Asegura buena ventilación para evitar acumulación de calor.\n" +
                "Evita mojar las hojas en horas de sol fuerte, realiza los baños o pulverizaciones temprano en la mañana o al atardecer.\n" +
                "No fertilices ni riegues en exceso mientras la planta se recupera.\n" +
                "Prevención\n" +
                "Acostumbra gradualmente a la planta a más luz si la vas a trasladar a un sitio más iluminado.\n" +
                "Nunca expongas la Tillandsia al sol directo intenso por periodos largos.\n" +
                "Manténla alejada de fuentes de calor artificial.\n',21)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (370,'Las cicatrices en las hojas del clavel del aire (Tillandsia) son marcas visibles que quedan como resultado de algún daño previo. No son una enfermedad en sí, pero pueden indicar que la planta atravesó situaciones de estrés. Aquí te explico las causas más comunes, cómo reconocerlas y qué hacer:\n" +
                "\n" +
                "Cicatrices en Tillandsia (clavel del aire)\n" +
                "¿Cómo lucen las cicatrices?\n" +
                "Manchas secas, duras o levemente hundidas de color marrón, gris o blanco.\n" +
                "Pueden ser líneas, puntos, áreas irregulares o pequeñas heridas cerradas.\n" +
                "El tejido cicatrizado ya no cambia de tamaño ni se expande.\n" +
                "No suele estar blando ni húmedo.\n" +
                "Causas más frecuentes\n" +
                "Daño mecánico:\n" +
                "Golpes, rozaduras, cortes o presión durante el transporte o manipulación.\n" +
                "\n" +
                "Quemaduras solares antiguas:\n" +
                "Daños por sol directo dejan manchas marrones o blanquecinas que luego cicatrizan.\n" +
                "\n" +
                "Ataque de plagas superado:\n" +
                "Orugas, caracoles o insectos que comieron la hoja dejan marcas que luego cicatrizan.\n" +
                "\n" +
                "Cicatrización tras enfermedad leve:\n" +
                "Manchas de hongos o bacterias que la planta superó pueden dejar zonas secas.','No te preocupes si la cicatriz es seca y estable:\n" +
                "Las cicatrices no afectan la salud general si el resto de la planta se ve bien.\n" +
                "Observa nuevas hojas:\n" +
                "Si las hojas nuevas salen sanas, el problema fue superado.\n" +
                "Retira hojas muy dañadas solo si afectan la estética:\n" +
                "Usa tijeras limpias y corta solo las hojas o partes muy secas.\n" +
                "Evita nuevas heridas:\n" +
                "Manipula con cuidado y revisa ambiente y cuidados para prevenir estrés futuro.\n" +
                "¿Debo preocuparme?\n" +
                "No, las cicatrices son como “marcas de batalla” y muestran que la planta sobrevivió a un episodio de estrés. Solo debes intervenir si ves que las marcas se expanden, están blandas, húmedas u huelen mal (en ese caso, sería pudrición o infección activa).',3)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'Copilot said: La **flor marchita** en la **margarita del\n" +
                "La flor marchita en la margarita del cabo (Dimorphotheca/Osteospermum) puede ser un proceso natural o el resultado de algún problema de cultivo. Aquí tienes las causas más comunes, cómo diferenciarlas y qué hacer:\n" +
                "\n" +
                "Flor marchita en margarita del cabo\n" +
                "Causas naturales\n" +
                "Final del ciclo floral:\n" +
                "Las flores de la margarita del cabo tienen una duración limitada (varios días a un par de semanas). Es normal que, tras abrirse, se marchiten y sequen.\n" +
                "Formación de semillas:\n" +
                "Cuando la polinización ha sido exitosa, la flor se marchita para dar paso a la formación de semillas.\n" +
                "Causas de marchitez prematura\n" +
                "Falta de agua:\n" +
                "Las flores pueden marchitarse rápidamente si la planta sufre sequía.\n" +
                "Exceso de calor o sol intenso:\n" +
                "Las flores expuestas a sol muy fuerte o altas temperaturas pueden marchitarse antes de tiempo.\n" +
                "Enfermedades fúngicas:\n" +
                "Hongos como Botrytis pueden atacar flores, sobre todo en ambientes húmedos, provocando marchitez y manchas.\n" +
                "Plagas (trips, pulgones):\n" +
                "Insectos chupadores pueden debilitar y marchitar flores.\n" +
                "Falta de nutrientes:\n" +
                "Un sustrato pobre puede afectar la duración y vigor de las flores.','Retira las flores marchitas (despunte o deadheading):\n" +
                "Esto estimula a la planta a producir más flores y evita enfermedades.\n" +
                "Riego adecuado:\n" +
                "Mantén el sustrato ligeramente húmedo, evitando encharcamientos.\n" +
                "Ubicación:\n" +
                "Proporciona sol directo, pero si el calor es extremo, protege en las horas más intensas.\n" +
                "Fertilización equilibrada:\n" +
                "Usa abono para plantas de flor cada 2-3 semanas en temporada de crecimiento.\n" +
                "Vigila plagas y enfermedades:\n" +
                "Revisa flores y hojas, y actúa ante cualquier plaga o mancha sospechosa.',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'La marchitación después de la floración en la margarita del cabo (Osteospermum/Dimorphotheca) es común y, en la mayoría de los casos, es parte del ciclo natural de la planta. Sin embargo, hay detalles importantes que debes considerar:\n" +
                "\n" +
                "Marchitación tras la floración en la margarita del cabo\n" +
                "Causas naturales\n" +
                "Ciclo floral normal:\n" +
                "Después de florecer, las flores se marchitan y secan para dar paso a la formación de semillas. Este proceso es completamente normal.\n" +
                "Producción continua:\n" +
                "La planta continuará produciendo nuevas flores si se eliminan las flores marchitas (despunte o \"deadheading\").\n" +
                "Situaciones a vigilar\n" +
                "Marchitación de toda la planta:\n" +
                "Si, además de las flores, la planta completa empieza a marchitarse, puede indicar otros problemas como:\n" +
                "Falta o exceso de riego.\n" +
                "Estrés por calor o trasplante.\n" +
                "Enfermedades de raíz o tallo.\n" +
                "Agotamiento de nutrientes.','Quita las flores marchitas:\n" +
                "Realiza despunte regularmente. Esto estimula la aparición de nuevas flores y evita el gasto de energía en la producción de semillas.\n" +
                "Riego adecuado:\n" +
                "Mantén el sustrato húmedo pero nunca encharcado. Deja secar la capa superficial entre riegos.\n" +
                "Aporta nutrientes:\n" +
                "Fertiliza cada 2-3 semanas durante la temporada de crecimiento con un fertilizante para plantas de flor.\n" +
                "Buena exposición:\n" +
                "La margarita del cabo necesita sol directo para florecer bien, pero si el calor es extremo, protégela en las horas más intensas.\n" +
                "Vigila plagas y enfermedades:\n" +
                "Revisa hojas y tallos por si hay manchas, podredumbre o insectos.\n',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'Las manchas marrones en las hojas de la margarita del cabo (Osteospermum/Dimorphotheca) pueden aparecer por varias razones. Aquí tienes las causas más frecuentes, cómo diferenciarlas y qué hacer en cada caso:\n" +
                "\n" +
                "Causas de manchas marrones en margarita del cabo\n" +
                "1. Enfermedades fúngicas (hongos)\n" +
                "Síntomas: Manchas marrones circulares, a veces con halo amarillo, que pueden agrandarse y fusionarse. Las hojas pueden amarillear y caer.\n" +
                "Solución: Retira las hojas afectadas, mejora la ventilación, evita mojar el follaje y, si la infección es fuerte, aplica un fungicida específico para plantas ornamentales.\n" +
                "2. Exceso de riego o mal drenaje\n" +
                "Síntomas: Manchas marrón oscuro, blandas o húmedas, especialmente en la base de la planta. Puede ir acompañada de pudrición de raíces o tallos.\n" +
                "Solución: Revisa el sustrato, asegúrate de que drene bien y ajusta la frecuencia de riego.\n" +
                "3. Quemaduras solares o estrés térmico\n" +
                "Síntomas: Manchas marrón claro o blanquecinas, secas, en las partes más expuestas al sol.\n" +
                "Solución: Protege la planta del sol directo intenso (especialmente en horas centrales) y adapta la exposición gradualmente.\n" +
                "4. Plagas (trips, ácaros, pulgones)\n" +
                "Síntomas: Manchas irregulares, a veces acompañadas de puntos negros (excrementos), deformaciones o presencia de insectos.\n" +
                "Solución: Examina el envés de las hojas y trata con insecticida adecuado si encuentras plagas.\n" +
                "5. Daños mecánicos o químicos\n" +
                "Síntomas: Manchas localizadas donde la planta fue golpeada, doblada o recibió productos concentrados (fertilizantes, pesticidas).\n" +
                "Solución: Evita manipulación brusca y sigue dosis recomendadas de productos.','Retira las hojas muy dañadas con tijeras limpias.\n" +
                "Revisa riego, exposición solar y presencia de plagas.\n" +
                "Asegura buena ventilación y sustrato drenante.\n" +
                "Si sospechas hongos, usa un fungicida preventivo.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'La falta de riego en la margarita del cabo (Osteospermum/Dimorphotheca) puede afectar su salud y floración. Aquí tienes cómo identificar y solucionar este problema:\n" +
                "\n" +
                "Síntomas de falta de riego en margarita del cabo\n" +
                "Hojas lacias, blandas o caídas.\n" +
                "Bordes de las hojas secos y marrones.\n" +
                "Marchitez general de la planta.\n" +
                "Flores que se marchitan rápidamente o no abren completamente.\n" +
                "Crecimiento lento y aspecto apagado.\n" +
                "Sustrato seco al tacto, especialmente en profundidad.\n" +
                "Consecuencias\n" +
                "Estrés hídrico que puede debilitar la planta.\n" +
                "Menor producción y duración de flores.\n" +
                "Mayor susceptibilidad a plagas y enfermedades.\n','Riego profundo inmediato:\n" +
                "Riega la planta abundantemente, permitiendo que el agua llegue hasta el fondo de la maceta o el suelo. Deja escurrir el exceso de agua para evitar encharcamientos.\n" +
                "\n" +
                "Frecuencia adecuada:\n" +
                "\n" +
                "En primavera-verano: Riega cuando los primeros 2-3 cm del sustrato estén secos, normalmente 1-3 veces por semana dependiendo del clima.\n" +
                "En otoño-invierno: Reduce la frecuencia, pero nunca dejes que la planta se seque completamente.\n" +
                "Evita el encharcamiento:\n" +
                "Un buen drenaje es clave. Si la planta está en maceta, asegúrate de que el agua salga por los agujeros inferiores.\n" +
                "\n" +
                "Mulching (opcional):\n" +
                "Una capa de mulch orgánico ayuda a conservar la humedad en el suelo.\n" +
                "\n" +
                "Consejo práctico\n" +
                "Observa la planta a diario en épocas de calor o viento.\n" +
                "Es preferible regar por la mañana.\n" +
                "Si la planta está muy alicaída, puede tardar 1-2 días en recuperarse tras el riego.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'El envejecimiento en la margarita del cabo (Osteospermum/Dimorphotheca) es un proceso natural que afecta tanto a las flores como a la planta entera con el paso del tiempo o al finalizar la temporada de floración. Aquí tienes lo más relevante:\n" +
                "\n" +
                "Envejecimiento en margarita del cabo\n" +
                "¿Qué significa?\n" +
                "Las flores y hojas más viejas pierden color, se secan y caen.\n" +
                "Las ramas pueden volverse leñosas y menos productivas con los años.\n" +
                "La planta puede mostrar menos vigor y floración si no recibe los cuidados adecuados.\n" +
                "Síntomas de envejecimiento\n" +
                "Flores que duran menos y se secan rápidamente.\n" +
                "Hojas amarillas, secas o con manchas marrones, sobre todo en la base.\n" +
                "Tallos leñosos y poco ramificados.\n" +
                "Menor producción de brotes nuevos y flores.','Poda rejuvenecedora\n" +
                "\n" +
                "Al final de la temporada de floración, corta los tallos secos y leñosos dejando la base y los brotes jóvenes.\n" +
                "Retira flores y hojas secas durante todo el año para estimular nuevas flores.\n" +
                "Fertilización\n" +
                "\n" +
                "Aplica un fertilizante equilibrado a comienzos de la primavera para estimular nuevos brotes.\n" +
                "División o renovación\n" +
                "\n" +
                "Si la planta está muy envejecida y poco productiva, puedes dividirla o plantar esquejes de brotes jóvenes.\n" +
                "Mejora del sustrato\n" +
                "\n" +
                "Cambia o enriquece el sustrato cada 2-3 años para renovar nutrientes y mejorar el drenaje.\n" +
                "Riego y exposición solar\n" +
                "\n" +
                "Mantén un riego adecuado y suficiente sol directo para favorecer la recuperación y el crecimiento.\n" +
                "Consejo\n" +
                "La margarita del cabo puede vivir y florecer muchos años si se la rejuvenece con podas y buenos cuidados. Si la planta está muy vieja y no responde, puedes obtener nuevas plantas fácilmente mediante esquejes.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'La podrición del tallo en la margarita del cabo (Osteospermum/Dimorphotheca) es un problema grave que puede provocar la muerte de la planta si no se actúa rápido. Aquí tienes cómo identificarla, sus causas y qué hacer:\n" +
                "\n" +
                "Pudrición del tallo en margarita del cabo\n" +
                "Síntomas\n" +
                "El tallo se vuelve blando, oscuro o negro y está húmedo al tacto.\n" +
                "La base del tallo puede verse acuosa o colapsada.\n" +
                "Las hojas cercanas al tallo afectado se ponen amarillas y se caen.\n" +
                "Olor desagradable en la zona afectada.\n" +
                "La planta se debilita rápidamente y puede caerse.\n" +
                "Causas principales\n" +
                "Exceso de riego o mal drenaje (el sustrato permanece mojado mucho tiempo).\n" +
                "Hongos patógenos del suelo (Fusarium, Phytophthora, Pythium, etc.).\n" +
                "Heridas en el tallo por daños mecánicos o plagas.\n','Actúa rápido:\n" +
                "Si la pudrición está muy extendida y afecta la base, suele ser difícil salvar la planta.\n" +
                "Si solo una parte está afectada, corta el tallo sano por encima de la zona podrida usando tijeras limpias y desinfectadas.\n" +
                "Mejora el drenaje:\n" +
                "Cambia el sustrato por uno suelto y bien drenante.\n" +
                "Usa macetas con agujeros de drenaje.\n" +
                "Reduce el riego:\n" +
                "Riega solo cuando el sustrato esté seco al tacto.\n" +
                "Aplica fungicida:\n" +
                "Si pudiste salvar parte de la planta, puedes aplicar un fungicida sistémico en la base y el sustrato.\n" +
                "Propaga esquejes:\n" +
                "Si tienes zonas sanas, corta esquejes y plántalos en sustrato nuevo y limpio para obtener una nueva planta.\n" +
                "Descarta la planta si está muy afectada:\n" +
                "Si la pudrición es total, retira la planta y desecha el sustrato para evitar contagios.\n" +
                "Prevención\n" +
                "Riega moderadamente y asegúrate de que el sustrato drene rápido.\n" +
                "No mojes el tallo al regar.\n" +
                "Desinfecta herramientas de poda.\n" +
                "No reutilices sustrato de plantas que han tenido pudrición.\n',34)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'Las mariquitas (también llamadas “vaquitas de San Antonio”, “catarinas” o “ladybugs”) son insectos benéficos muy conocidos en jardinería y agricultura.\n" +
                "\n" +
                "¿Qué hacen las mariquitas?\n" +
                "Son depredadores naturales de plagas:\n" +
                "Se alimentan principalmente de pulgones, cochinillas, ácaros y otros insectos dañinos para las plantas, como la margarita del cabo y muchas otras especies.\n" +
                "Ayudan a mantener el equilibrio en el jardín:\n" +
                "Una población saludable de mariquitas reduce la necesidad de insecticidas.\n" +
                "¿Son buenas o malas para mis plantas?\n" +
                "¡Son muy buenas!\n" +
                "No dañan las plantas; al contrario, las protegen de plagas.\n" +
                "\n" +
                "¿Cómo atraer o mantener mariquitas en el jardín?\n" +
                "Evita los insecticidas químicos: Pueden matar a las mariquitas y a sus presas.\n" +
                "Planta flores variadas: Margaritas, caléndulas, eneldo, hinojo, cilantro y otras flores pequeñas atraen mariquitas.\n" +
                "Mantén refugios: Piedras, cortezas o pequeñas áreas con maleza pueden servirles de escondite.\n" +
                "No elimines todos los pulgones: Si hay algunos, las mariquitas tendrán alimento y permanecerán en el jardín.\n','¿Qué hacer si ves muchas mariquitas?\n" +
                "¡Déjalas! Son aliadas naturales en el control de plagas.\n" +
                "Si las encuentras en exceso dentro de casa, puedes recogerlas suavemente y liberarlas en el jardín.\n',6)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (430,'1. Pulgones (Aphididae)\n" +
                "Descripción: Pequeños, blandos, verdes, negros, amarillos o marrones. Se agrupan en brotes tiernos y el envés de las hojas.\n" +
                "Síntomas: Hojas enrolladas, deformes, pegajosas (mielada), presencia de hormigas, crecimiento lento.\n" +
                "2. Mosca blanca (Aleyrodidae)\n" +
                "Descripción: Pequeños insectos blancos que vuelan al mover la planta. Se instalan en el envés de las hojas.\n" +
                "Síntomas: Hojas amarillas, pegajosas, debilitamiento general, aparición de manchas negras (fumagina).\n" +
                "3. Cochinillas (Coccoidea)\n" +
                "Descripción: Pequeños insectos con caparazón duro o algodonoso, pegados a tallos y hojas.\n" +
                "Síntomas: Hojas amarillas, manchas, presencia de costras o masas blancas, debilitamiento.\n" +
                "4. Trips (Thysanoptera)\n" +
                "Descripción: Insectos alargados, pequeños y rápidos, de color marrón o negro.\n" +
                "Síntomas: Hojas con manchas plateadas o descoloridas, flores deformes, puntos negros (excrementos).\n" +
                "5. Ácaros (araña roja, Tetranychus urticae)\n" +
                "Descripción: Muy pequeños, rojizos o amarillos, visibles como polvo móvil; a veces tejen telarañas finas.\n" +
                "Síntomas: Hojas pálidas, amarillas o con puntitos, caída prematura, hilos finos entre hojas.\n" +
                "Daños que causan\n" +
                "Succionan la savia debilitando la planta.\n" +
                "Producen deformaciones, manchas, caída de hojas y flores.\n" +
                "Excretan “mielada” que favorece el desarrollo de hongos.\n" +
                "Pueden transmitir virus y otras enfermedades.','Revisión periódica:\n" +
                "Inspecciona hojas y brotes, especialmente el envés.\n" +
                "\n" +
                "Control manual:\n" +
                "Elimina con agua a presión, paño húmedo o tijeras las partes infestadas.\n" +
                "\n" +
                "Insecticidas orgánicos:\n" +
                "Usa jabón potásico, aceite de neem o infusiones de ajo. Repetir aplicaciones.\n" +
                "\n" +
                "Depredadores naturales:\n" +
                "Fomenta la presencia de mariquitas y crisopas, que se alimentan de pulgones y otros chupadores.\n" +
                "\n" +
                "Insecticidas químicos:\n" +
                "Solo como último recurso. Elige productos específicos y sigue las instrucciones.',52)")

        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'En la margarita de la lluvia (Dimorphotheca u Osteospermum), el marchitamiento después de la floración es un proceso completamente natural y esperado. Aquí tienes los puntos clave:\n" +
                "\n" +
                "¿Por qué ocurre el marchitamiento después de la floración?\n" +
                "Ciclo natural: Cada flor tiene una vida limitada. Tras polinizarse o cumplir su función, comienza a marchitarse.\n" +
                "Redirección de energía: La planta dirige sus recursos a producir nuevas flores o semillas, por lo que las flores viejas se secan y caen.\n','Retira las flores marchitas (deadheading):\n" +
                "Corta las flores secas con tijeras limpias o pellizca con los dedos. Esto estimula la producción de nuevas flores y mantiene la planta sana y atractiva.\n" +
                "Continúa con los cuidados normales:\n" +
                "Riego moderado, pleno sol, y fertilización durante la temporada de crecimiento.\n" +
                "Evita el exceso de humedad:\n" +
                "Elimina flores y hojas secas para reducir el riesgo de hongos.\n" +
                "Excepción: ¿Marchitamiento excesivo o prematuro?\n" +
                "Si notas que las flores se marchitan muy rápido, revisa:\n" +
                "\n" +
                "Exceso o falta de agua\n" +
                "Falta de sol\n" +
                "Plagas o enfermedades',22)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'En la margarita de la lluvia (Dimorphotheca/Osteospermum), la aparición de flores marchitas es completamente normal y parte de su ciclo de vida. Aquí tienes los puntos clave:\n" +
                "\n" +
                "¿Por qué se marchitan las flores?\n" +
                "Ciclo natural: Cada flor tiene una vida útil limitada. Al terminar su ciclo, se marchita para dar paso a nuevas flores.\n" +
                "Condiciones ambientales: El calor intenso, la falta de agua o el viento fuerte pueden acelerar el proceso.\n" +
                "Falta de cuidados: Si no se retiran las flores secas, la planta puede dejar de producir nuevas flores con tanta energía.\n','Retira las flores secas (\"deadheading\"):\n" +
                "\n" +
                "Corta o pellizca las flores marchitas usando tijeras limpias.\n" +
                "Esto estimula la planta a producir nuevos botones florales y mejora su aspecto.\n" +
                "Cuida el riego y la luz:\n" +
                "\n" +
                "Mantén el sustrato ligeramente húmedo, nunca encharcado.\n" +
                "Asegura que la planta reciba pleno sol.\n" +
                "Fertiliza moderadamente:\n" +
                "\n" +
                "Un abono equilibrado cada 3-4 semanas durante la floración ayuda a mantener la producción de flores.\n" +
                "Excepción: Marchitez anormal\n" +
                "Si las flores se marchitan muy rápido o toda la planta se ve afectada, revisa por:\n" +
                "\n" +
                "Falta o exceso de agua.\n" +
                "Falta de sol.\n" +
                "Plagas (pulgones, trips, mosca blanca).\n" +
                "Enfermedades fúngicas (si hay manchas o podredumbre).',2)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'El escarabajo de las hojas es una plaga ocasional en la margarita de la lluvia (Dimorphotheca/Osteospermum), aunque no suele ser la más frecuente. Aquí tienes información esencial sobre este insecto y cómo manejarlo:\n" +
                "\n" +
                "¿Qué es el escarabajo de las hojas?\n" +
                "Nombre común para diversas especies de la familia Chrysomelidae.\n" +
                "Son pequeños, de colores metálicos o amarillos/negros, y se alimentan de las hojas de muchas plantas ornamentales y hortícolas.\n" +
                "Los adultos y las larvas mastican el follaje, dejando agujeros o bordes irregulares.\n" +
                "Síntomas de ataque\n" +
                "Hojas con agujeros irregulares o bordes comidos.\n" +
                "En casos severos, defoliación parcial.\n" +
                "Presencia de pequeños escarabajos brillantes sobre o debajo de las hojas, especialmente en días soleados.\n" +
                "¿Es peligroso para la margarita de la lluvia?\n" +
                "En infestaciones leves, el daño es principalmente estético y la planta suele recuperarse.\n" +
                "En infestaciones severas, pueden debilitar la planta, reducir la floración y hacerla más susceptible a enfermedades.','Revisión regular:\n" +
                "Inspecciona las hojas, especialmente el envés, para detectar escarabajos.\n" +
                "Eliminación manual:\n" +
                "Si hay pocos, quítalos a mano o sacude la planta sobre un recipiente.\n" +
                "Control ecológico:\n" +
                "Usa jabón potásico o aceite de neem (menos efectivo contra adultos, sí sobre larvas jóvenes).\n" +
                "Fomenta la presencia de aves o depredadores naturales en el jardín.\n" +
                "Insecticida químico:\n" +
                "Solo si la plaga es muy abundante y no hay alternativas, elige productos específicos para escarabajos y sigue las instrucciones.\n" +
                "Prevención:\n" +
                "Mantén la planta fuerte con riego y fertilización adecuados, ya que las plantas sanas toleran mejor el ataque.\n',28)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'En la margarita de la lluvia (Dimorphotheca/Osteospermum), el envejecimiento es un proceso natural, pero puede afectar el vigor, la floración y la apariencia general de la planta. Aquí tienes lo esencial sobre el envejecimiento y cómo rejuvenecer tu planta:\n" +
                "\n" +
                "¿Cómo se manifiesta el envejecimiento?\n" +
                "Hojas viejas: Se tornan amarillas, secas o caen, sobre todo en la parte baja.\n" +
                "Tallos leñosos: Se vuelven más duros, menos flexibles y menos productivos.\n" +
                "Menos floración: La planta produce menos flores y de menor calidad.\n" +
                "Crecimiento lento: El desarrollo de la planta se ralentiza y pierde densidad.\n" +
                "Causas principales\n" +
                "Ciclo natural de la planta (especialmente en ejemplares de varios años).\n" +
                "Falta de poda o renovación.\n" +
                "Sustrato agotado o compactado.\n" +
                "Estrés por riego inadecuado, plagas o enfermedades.','Poda rejuvenecedora\n" +
                "Realiza una poda fuerte al final de la floración, eliminando tallos viejos y dejando los brotes jóvenes.\n" +
                "Retira flores y hojas secas regularmente para estimular nuevos brotes.\n" +
                "Renueva el sustrato\n" +
                "Cambia parte de la tierra o añade compost cada 1-2 años para aportar nutrientes.\n" +
                "Fertiliza en época de crecimiento\n" +
                "Usa abono equilibrado cada 3-4 semanas durante la primavera y el verano.\n" +
                "Multiplicación por esquejes\n" +
                "Si la planta está muy envejecida, corta tallos jóvenes y enraíza en sustrato fresco para obtener nuevas plantas vigorosas.\n" +
                "Mantén un buen riego y exposición solar\n" +
                "Riego moderado y pleno sol favorecen el vigor y la floración.\n" +
                "Consejo\n" +
                "Con podas regulares, renovación de sustrato y cuidados básicos, la margarita de la lluvia puede mantenerse saludable y floreciente durante muchos años. Si la planta está demasiado deteriorada, es recomendable propagar esquejes y reemplazarla.',27)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'En la margarita de la lluvia (Dimorphotheca/Osteospermum), la falta de riego es una de las causas más comunes de problemas en la planta. Aquí tienes cómo identificarla y qué hacer:\n" +
                "\n" +
                "Síntomas de falta de riego\n" +
                "Hojas y tallos lacias, caídas o marchitas.\n" +
                "Bordes de las hojas secos o marrones.\n" +
                "Flores que se marchitan rápidamente o no se abren bien.\n" +
                "Sustrato seco al tacto, incluso varios centímetros por debajo de la superficie.\n" +
                "Crecimiento lento o aspecto apagado de la planta.','Riego profundo:\n" +
                "Riega la planta abundantemente para asegurar que el agua llegue a las raíces. Espera a que el exceso drene.\n" +
                "Ajusta la frecuencia:\n" +
                "Riega cuando los primeros 2-3 cm del sustrato estén secos.\n" +
                "En maceta, puede ser necesario regar 2-3 veces por semana en climas cálidos.\n" +
                "Evita el encharcamiento:\n" +
                "El exceso de agua puede causar pudrición de raíces. Asegúrate de que la maceta drene bien.\n" +
                "Observa la recuperación:\n" +
                "La planta suele recuperar firmeza en 12-24 horas tras un buen riego si no está muy dañada.\n" +
                "Consejos extra\n" +
                "Riega preferentemente por la mañana.\n" +
                "En clima cálido, revisa la planta a diario.\n" +
                "Un acolchado superficial ayuda a mantener la humedad del sustrato.',16)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'En la margarita de la lluvia (Dimorphotheca/Osteospermum), la aparición de manchas marrones puede deberse a varias causas. Aquí tienes cómo identificar el problema y las posibles soluciones:\n" +
                "\n" +
                "Principales causas de mancha marrón\n" +
                "1. Hongos foliares (manchas foliares)\n" +
                "Síntomas: Manchas marrones, circulares o irregulares, a veces con borde amarillo en hojas o pétalos.\n" +
                "Causa: Humedad alta, exceso de riego o mala ventilación.\n" +
                "Solución:\n" +
                "Retira y desecha las hojas afectadas.\n" +
                "Mejora la ventilación y evita mojar el follaje al regar.\n" +
                "Aplica fungicida específico para plantas ornamentales.\n" +
                "2. Quemaduras solares\n" +
                "Síntomas: Manchas marrones secas, a menudo en la parte superior de la planta.\n" +
                "Causa: Exposición repentina a sol intenso, especialmente tras estar en sombra.\n" +
                "Solución:\n" +
                "Adapta la planta gradualmente al sol.\n" +
                "Riega adecuadamente para evitar estrés hídrico.\n" +
                "3. Plagas\n" +
                "Síntomas: Manchas marrones acompañadas de pequeños agujeros, restos pegajosos o presencia de insectos.\n" +
                "Causa: Insectos chupadores o masticadores (trips, escarabajos, pulgones).\n" +
                "Solución:\n" +
                "Revisa el envés de las hojas y los tallos.\n" +
                "Elimina plagas manualmente o con insecticida ecológico (jabón potásico, neem).\n" +
                "4. Exceso de riego/pudrición\n" +
                "Síntomas: Manchas marrones blandas, especialmente en la base de tallos u hojas bajas.\n" +
                "Causa: Sustrato encharcado o mal drenaje.\n" +
                "Solución:\n" +
                "Reduce el riego.\n" +
                "Mejora el drenaje y retira partes dañadas.','Observa el tipo y localización de las manchas.\n" +
                "Revisa riego, exposición al sol y presencia de plagas.\n" +
                "Toma medidas según la causa probable.\n',15)")
        db.execSQL("INSERT INTO ENFERMEDADES (COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD) VALUES (440,'Copilot said: El **marchitamiento de la planta** de margarita\n" +
                "El marchitamiento de la planta de margarita de la lluvia (Dimorphotheca/Osteospermum) puede deberse a varias causas. Aquí tienes una guía para identificar el motivo y actuar rápidamente:\n" +
                "\n" +
                "Principales causas de marchitamiento\n" +
                "Falta de riego\n" +
                "\n" +
                "Hojas y tallos caídos, aspecto lacio.\n" +
                "Sustrato seco al tacto.\n" +
                "Solución: Riega abundantemente y revisa la frecuencia de riego.\n" +
                "Exceso de riego/pudrición de raíces\n" +
                "\n" +
                "Hojas amarillas o marrones, tallos blandos, mal olor.\n" +
                "Sustrato constantemente húmedo o encharcado.\n" +
                "Solución: Reduce el riego, mejora el drenaje y elimina partes dañadas.\n" +
                "Ataque de plagas\n" +
                "\n" +
                "Insectos chupadores (pulgón, mosca blanca), ácaros o escarabajos.\n" +
                "Hojas deformadas, manchas, presencia de insectos.\n" +
                "Solución: Revisa y aplica control biológico o insecticidas ecológicos.\n" +
                "Enfermedades fúngicas\n" +
                "\n" +
                "Manchas marrones, pudrición del tallo o raíces.\n" +
                "Hojas marchitas aunque el sustrato esté húmedo.\n" +
                "Solución: Retira partes afectadas y usa fungicida.\n" +
                "Estrés por trasplante o cambios bruscos\n" +
                "\n" +
                "Marchitamiento temporal tras cambiar la planta de lugar o maceta.\n" +
                "Solución: Protege la planta del sol directo y mantén riego moderado.\n" +
                "Deficiencia nutricional\n" +
                "\n" +
                "Hojas pálidas o amarillas, crecimiento lento.\n" +
                "Solución: Aplica fertilizante equilibrado.','Observa el sustrato y el aspecto de raíces, tallos y hojas.\n" +
                "Ajusta el riego según necesidad.\n" +
                "Retira hojas y tallos dañados.\n" +
                "Mejora el drenaje si hay exceso de humedad.\n" +
                "Aplica tratamientos según la causa (fungicida, insecticida o fertilizante).\n',10)")

        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Azaleas', 'azalea')")
        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Begonias', 'begonia')")
        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Cactus','cactus')")
        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Calatheas','calathea')")
        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Claveles','clavel')")
        db.execSQL("INSERT INTO CATEGORIAS (NOMBRE_CATEGORIA, IMAGEN) VALUES ('Dimorphotecas','dimorphoteca')")
        // INSERTS PLANTAS
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea de Fuego','azalea_fuego','Conocida por sus hojas elípticas y el color cambiante de sus flores, la azalea de fuego florece a finales de primavera o principios de verano y requiere poco cuidado, si bien son muy susceptibles a numerosas invasiones de insectos (deben cultivarse en entornos adecuados y saludables). Su uso ornamental más común es como borde de jardín, aunque como espécimen también es eficaz en patios y en sitios en torno al hogar. Es altamente tóxico para humanos, gatos y perros. Su consumo puede llevar a una grave angustia gastrointestinal, posible parálisis e incluso la muerte. Los síntomas incluyen debilidad, vómitos y diarrea. La atención veterinaria inmediata es esencial si los gatos o perros lo ingieren para asegurar un tratamiento rápido y reducir complicaciones. Evite ingerir cualquier parte de la planta o miel hecha de ella.',1,10,10)")
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea Occidental','azalea_occidental','La azalea occidental es una planta de gran atractivo ornamental por sus encantadoras flores que crecen en tupidos racimos y por su colorido follaje. Las fragantes flores atraen principalmente a los colibríes y mariposas. Sin embargo, se debe tener cuidado con su toxicidad, incluso la miel elaborada con su néctar es tóxica. \n Azalea occidental es un arbusto alto con flores grandes, blancas o rosadas, en forma de embudo y con estambres prominentes. Sus hojas brillantes proporcionan un excelente fondo. Su crecimiento exuberante lo hace ideal para plantar como espécimen, en grupo, o incluso en masa dentro de setos de arbustos o bordes mixtos, y también puede utilizarse como seto o pantalla. Los tipos de jardines que mejor se adaptan a azalea occidental son los de bosque, cabaña, informales y jardines de sombra. También puede funcionar muy bien en jardines urbanos y patios.',1,20,20)")
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea Chino', 'azalea_chino','También conocido como: Azalea Mollis, Azalea kinesisk\n" +
                "El azalea Chino tiene flores de un amarillo intenso y que desprenden una dulce fragancia. Sus flores pueden aparecer antes o durante la salida de sus hojas y a veces pueden darse en otros colores, como el blanco o el rosado. Como lo indica uno de sus nombres comunes en inglés (Chinese azalea), esta planta es originaria de China. \n El azalea Chino es nativo de regiones templadas de Asia Oriental y ha sido cultivado en varias partes del mundo. En su rango nativo, el azalea Chino prospera en bosques de hojas anchas y áreas montañosas, a menudo adaptado a climas más frescos. Su atractivo hortícola ha llevado a su introducción en jardines y paisajes en continentes diferentes, especialmente en áreas con condiciones templadas similares, aunque las ubicaciones específicas están más allá del alcance de este resumen según las instrucciones.',1,30,30)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea Temprana','azalea_temprana','Este arbusto es muy apreciado con fines ornamentales por sus flores, así mismo es extremadamente resistente y con buenos cuidados se reduce el riesgo de que la afecten enfermedades y plagas. Las flores del azalea temprana atraen a varias a mariposas y colibríes, además son una valiosa fuente de alimento para los abejorros.',1,40,40)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea Alpina','azalea_alpina','Es un arbusto rastrero de crecimiento lento. Ramas con la corteza grisácea. Las hojas opuestas, ovaladas y enteras, casi sésiles, persistentes y coriáceas. Las flores pentámeras con la corola rosada acampanada de color púrpura. El fruto es subgloboso. \n" +
                "Azalea alpina tiene una distribución natural en una variedad de regiones templadas en ambos hemisferios oriental y occidental. Su rango tradicional abarca las partes septentrionales de Europa y Asia, extendiéndose también a áreas del ártico y subártico. Más específicamente, es originario del norte de Eurasia y se ha propagado naturalmente a las regiones boreales, donde prospera en las condiciones climáticas particulares de estos territorios.',1,50,50)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Azalea de Andalucía','azalea_andalucia','También conocido como: Revientamulas, Jaranzo\n" +
                "El rododendro es un arbusto muy utilizado y valorado por los amantes de la jardinería por sus grandes y bellas flores. Sin embargo, se utiliza únicamente de forma ornamental porque la planta posee toxinas venenosas, especialmente nocivas para las ovejas y el ganado. En adición, las toxinas también se encuentran en el néctar y pueden intoxicar a humanos si su miel es ingerida.',1,60,60)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia rex','begonia_rex','También conocido como: Begonia de hoja pintada\n" +
                "Las hojas de la begonia rex destacan por su forma y principalmente por su gran diversidad de colores. Por eso es una de las favoritas de los amantes de la jardinería. Sin embargo, hay que prestar atención con las mascotas, pues es venenosa para los gatos y perros, especialmente las raíces si las comen. \n" +
                "La begonia rex es originaria de los suelos sombríos de la India y gran parte de China. Esta planta también ha sido introducida en Bangladesh y varias islas del Caribe.',2,70,70)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia alas de ángel','begonia_angel','La begonia alas de ángel (Begonia coccinea) es todo un espectáculo natural para los sentidos. Es la más exótica de la familia de las Begonias, no solo por los lunares que abundan en sus hojas o por la forma en que estas caen a partir del tronco central, sino también por las espectaculares flores en forma de campana que aparecen durante la primavera. \n" +
                "Begonia alas de ángel, de origen natural en Sudamérica, se ha expandido más allá de su rango nativo a través de su cultivo. Ahora se encuentra en partes del Caribe, el sudeste de Asia, el este de Asia y las Islas del Pacífico, así como en las regiones del norte de Sudamérica y en África. Cultivada por su valor ornamental, begonia alas de ángel prospera en una variedad de climas, mostrando su adaptabilidad fuera de su hábitat original.',2,80,80)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia tuberosa','begonia_tuberosa','La begonia tuberosa (Begonia tuberhybrida) es una especie creada por agricultores especializados en esta familia. Su nombre proviene de la curiosa raíz tubular que posee. En la simbología de las plantas, cuando regalas una begonia tuberosa estás diciendo que eres capaz de soportar situaciones adversas, por lo que es gran regalo para tu pareja.\n" +
                "Simbolismo\n" +
                "Cuando se regala, la Begonia significa \"ten cuidado\" o \"estate atento\". También simboliza una naturaleza caprichosa.\n" +
                "Datos de Interés\n" +
                "Un gran consumidor, el begonia tuberosa requiere más nutrientes que la begonia promedio para mantener su abundante hábito de floración. Los fertilizantes de liberación lenta permiten a la planta absorber los nutrientes a través de un suelo bien drenado. Es vital para la salud de la planta que se le permita secarse entre riegos para prevenir la pudrición de las raíces. Las cestas colgantes forradas de musgo proporcionan la cantidad perfecta de drenaje y resaltan la naturaleza llorona de la planta.',2,90,90)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia flaviflora','begonia_flaviflora','Begonia flaviflora es una especie de Begonia con hojas características similares a alas de ángel. Sus delicadas flores amarillas florecen en las estaciones de los hombros. Ninguna es una planta de interior perfecta por su facilidad de cuidado.\n" +
                "El begonia flaviflora se origina en las regiones subtropicales del sur y este de Asia. Se encuentra de forma natural en una amplia extensión que va desde el este del Himalaya hasta las zonas cercanas al Mar de China Meridional. Como especie cultivada, el begonia flaviflora ha sido introducido en varias regiones templadas y tropicales de todo el mundo con fines ornamentales.',2,100,100)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia flor de azúcar','begonia_azucar','También conocido como: Agrial\n" +
                "La flor de nácar se ha ganado el aprecio de los jardineros por sus encantadoras flores de colores intensos e interesante follaje. Curiosamente, la palabra cucullata en su nombre científico Begonia cucullata var. cucullata quiere decir \"con capucha\".\n" +
                "La flor de nácar es originaria de regiones tropicales y subtropicales de Sudamérica. Ha sido introducida en regiones de América Central, el Caribe, partes de África y Asia Meridional. En su rango de introducción, la flor de nácar se ha adaptado a varios climas y se puede encontrar en jardines cultivados, prosperando en condiciones cálidas y húmedas.',2,110,110)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Begonia robusta','begonia_robusta','La begonia robusta es una de las plantas mejor comercializadas en España por su belleza y utilidad en decoración. Las hojas verde oscuro y acorazonadas, entre las cuales se asoman sus pequeñas flores rosas, son la mejor pista visual para reconocerla. Difícilmente la veremos en espacios extremadamente soleados, ya que prefiere la compañía de la sombra de los árboles vecinos.\n" +
                "Begonia robusta es una especie de planta nativa de las zonas templadas de Asia Oriental. Esta especie prospera en su hábitat natural, pero también ha sido introducida y ahora crece en partes de Asia Oriental. Además de su presencia en la naturaleza, begonia robusta se cultiva ampliamente, lo que indica su adaptabilidad y popularidad más allá de su área nativa.',2,120,120)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus de navidad','cactus_navidad','Aunque es originario de Brasil, el cactus de Navidad recibe su nombre dado que florece en otoño e invierno, siendo el invierno la época que coincide con Navidad en el hemisferio norte del planeta. El cactus de navidad es una planta epífita porque se aloja en el tronco de los árboles. Por sus bellas flores, es muy indicado para decoración, sobre todo de interior.\n" +
                "El cactus de navidad proviene de los bosques húmedos tropicales y subtropicales del sureste de Brasil. También se encuentra ahora en Vietnam. Se han desarrollado muchas variedades para ser vendidas como plantas de interior en todo el mundo, y algunas se han reintroducido en estado silvestre en otras áreas de Brasil.',3,130,130)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus quebradizo','cactus_quebradizo','También conocido como: Tuna frágil, Brujita pinchuda\n" +
                "El flores de nopal se usa en jardinería por sus coloridas flores además crece bien en maceta. Aunque hay que tener precaución con sus espinas que parecen pelos, son difícil de ver y de quitar. Las flores de este cactus resultan muy atractivas para los insectos que la polinizan. En adición, sus frutos deleitan a los pájaros y pequeños mamíferos.\n" +
                "Flores de nopal es un cactus nativo de áreas templadas y subtropicales en el hemisferio norte. Es particularmente resistente y ha sido introducido en regiones adicionales del hemisferio sur. Flores de nopal prospera tanto en su rango nativo como en ambientes cultivados, habiéndose naturalizado fuera de su hábitat original.',3,140,140)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus colgante','cactus_colgante','La subvariedad de este cactus colgante es exclusiva de Madagascar, pero esa no es su única peculiaridad. El cactus colgante no requiere tierra para vivir, pues crece sujeto a las ramas de los árboles y, además, hace honor a su nombre con sus frutos esféricos blancos y sus tallos carnosos y largos con pilosidades blancas.\n" +
                "Uso en el Jardín\n" +
                "El llamativo cactus colgante, con sus tallos colgantes y pequeñas flores blancas, se planta generalmente en jardines tropicales y desérticos por su apariencia, aunque su facilidad de cuidado y resistencia también son cualidades atractivas. Debido a sus tallos colgantes, se planta más comúnmente en macetas colgantes, pero las macetas altas y ciertos estilos de jardines de roca también son adecuados para este cactus de viejo mundo.',3,150,150)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus catedral','cactus_catedral','También conocido como: Planta de la leche, Árbol de la leche\n" +
                "El cactus catedral es un exótico cáctus de crecimiento erecto y follaje geométrico que le da la apariencia de las naves y campanales de una catedral gótica, derivando por esta razón su nombre Cactus catedral. De crecimiento rápido, en vida silvestre puede alcanzar hasta los 4 m de altura, pero si se cultiva en maceta, mantendrá su tamaño.\n" +
                "Cactus catedral presenta toxicidad moderada tanto para gatos como para perros. Los gatos y perros expuestos a la savia tóxica al tragar partes de la planta pueden mostrar síntomas como letargo, vómitos y diarrea. Es crucial contar con atención veterinaria inmediata si las mascotas muestran signos de envenenamiento para evitar complicaciones y asegurar un tratamiento adecuado. Proteja a las mascotas impidiendo el acceso a cactus catedral.',3,160,160)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus cacahuete','cactus_cacahuete','El cactus cacahuete es un cactus que habita en lugares montañosos. Se considera un cactus ornamental y el nombre común de cactus cacahuete le viene otorgado debido a que los brotes que produce tienen forma de cacahuete. Además, también produce flores vistosas muy apreciadas por ser de gran belleza.\n" +
                "Uso en el Jardín\n" +
                "Cactus cacahuete es una suculenta muy popular y comúnmente cultivada debido a sus racimos de tallos en forma de dedo y grandes y dramáticas flores de primavera. Sus raíces poco profundas la hacen perfecta como planta en maceta o colgante, que se puede colocar en cualquier lugar con luz solar directa para crear un hermoso contraste espinoso. A menudo se cultiva como planta de interior, pero también se ve bien en un jardín desértico o de cactus. Los agaves, áloes y yucas son todas excelentes opciones para plantar junto a cactus cacahuete.\n" +
                "Datos de Interés\n" +
                "Las pequeñas articulaciones similares a cacahuetes son numerosas y fáciles de propagar. Todo lo que tienes que hacer es desprender el vástago y plantarlo inmediatamente. No es necesario aclimatar el extremo como se requiere con muchos cacti. De hecho, cualquier trozo que caiga al suelo es probable que enraíce fácilmente y comience una nueva planta.',3,170,170)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus orquídea','cactus_orquidea','El cactus orquídea es un cactus endémico de Oaxaca y Veracruz que se ha extendido por todo el mundo. Su frágiles flores, que a veces tardan en aparecer 10 o 15 años, poseen un característico dulce aroma y una belleza muy apreciada en jardinería.\n" +
                "Cactus orquídea, una especie de cactus tropical, es nativa de los bosques lluviosos de América Central. En la horticultura contemporánea, ha sido introducida y se cultiva en varias regiones, adaptándose particularmente bien a climas subtropicales en todo el mundo. No se detalla el rango nativo exacto, pero su éxito en el cultivo atestigua una amplia adaptabilidad ecológica.\n" +
                "Uso en el Jardín\n" +
                "Una de las suculentas más hermosas que puede embellecer un jardín en un clima cálido y seco, el cactus orquídea es elegido por los jardineros por sus hermosas flores escarlatas, su follaje interesante y su tolerancia a la sequía. Puede añadir belleza a cualquier lugar soleado en el clima adecuado, pero se planta más comúnmente en contenedores al aire libre. También sería una excelente planta de patio o parte de un jardín de suculentas.\n" +
                "Simbolismo\n" +
                "Admiración, paciencia, buen ánimo',3,180,180)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Cactus Mammillaria','cactus_mammillaria','También conocido como: Mammillaria, Cactácea Mammillaria\n" +
                "La biznaga elongada (Mammillaria elongata) es uno de los cactus más cultivados del mundo. De tamaño pequeño, la biznaga elongada crece de manera apiñada y produce hijuelos de manera constante, por lo que es muy probable que, en poco tiempo, desarrolle una pequeña colonia de cactus mexicanos a su alrededor. Cada tubo está recubierto por dieciséis espinas de color blanco y dos de color rojo-amarillo.\n" +
                "Biznaga elongada, una especie de cactus, es originaria de regiones centrales de América del Norte. Prospera en climas áridos y también ha sido introducida en partes de Europa. Conocida por su adaptabilidad, biznaga elongada ha extendido más allá de su rango nativo a través de la cultivación, lo que le ha permitido establecerse en diversas regiones áridas y semiáridas alrededor del mundo.\n" +
                "Uso en el Jardín\n" +
                "Los tonos dorado-marrones de biznaga elongada lo convierten en una adición interesante a cualquier jardín de pradera o de rocas. Los tonos terrosos realzan las flores rosas o amarillas que esta suculenta produce en primavera. A menudo se cultiva en macetas.\n" +
                "Simbolismo\n" +
                "Resistencia\n" +
                "Datos de Interés\n" +
                "Mammillaria elongata es uno de los cáctus más populares, cultivado en todo el mundo, con docenas de cultivares lanzados. Algunas de las variedades más populares son (Estrellas Doradas, Rey de Cobre, Julio, Cristata) (Cáctus Cerebral), entre otras. Este encantador cáctus incluso ha ganado el Premio de Mérito de Jardín de la Sociedad Real de Jardinería, un premio anual establecido desde hace mucho tiempo para plantas.',3,190,190)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Calathea majestica','calathea_majestica','La Calathea majestica, también conocida como Goeppertia majestica, es una planta tropical originaria de América del Sur, muy apreciada por su follaje decorativo. Sus hojas son grandes, alargadas y de un verde oscuro con vetas plateadas o blancas en la parte superior, mientras que el reverso suele presentar un tono púrpura intenso. Esta planta pertenece a la familia Marantaceae y es ideal para interiores, ya que prefiere luz indirecta, alta humedad y temperaturas cálidas. Es una planta no tóxica para mascotas y personas, lo que la hace perfecta para hogares.',4,200,200)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Calathea bella','calathea_bella','El extraordinario entramado de las hojas de la calatea Network la diferencia notoriamente de otras del mismo género. Por otro lado, es la Calathea con el cuidado más fácil. Aunque fue documentada por primera vez en 1875 por William Bull, es una variedad rara perseguida por los amantes de estas plantas.',4,210,210)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Calathea roseopicta','calathea_roseopicta','Las calathea roseopicta (Little Princess) son un género de plantas florales especialmente apreciadas por sus hojas de tonos brillantes y patrones llamativos. Su uso es ornamental y algunas especies producen bellas flores, aunque el follaje es, por sí solo, muy hermoso. Son plantas de cultivo delicado, crecen en sombra, requieren riego constante y suelos bien drenados.',4,220,220)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Bijao (Calathea lutea)','bijao','También conocido como: Hoja blanca, Bijahua, Platanillo, Pampano, Chisgua, Hoja de tó\n" +
                "La bijao es característica de climas tropicales, cuenta con hojas perennes y grandes, entre las que surgen cálices que, escasamente como una piña, dejan entrever flores amarillas con pequeños pétalos blancos dentro. En algunos países, su hoja es utilizada con envoltorio de algunos platos o presentación de comidas, sin ser consumida directamente.\n" +
                "Bijao es nativo de las regiones tropicales de Centro y Sudamérica. Su hábitat natural abarca selvas tropicales y zonas húmedas y boscosas. Con el tiempo, bijao ha sido cultivado e introducido en otras regiones tropicales alrededor del mundo, prosperando en ambientes similares a su rango nativo.',4,230,230)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Calathea makoyana','calathea_makoyana','También conocido como: Planta de pavo real\\n\" +\n" +
                "                \"Cuando apenas aparecen en la planta, las hojas de la calatea (Goeppertia makoyana) parecen un cigarrillo color púrpura. Después se abren y es entonces cuando atraen todas las miradas con un asombroso juego de colores, manchas, ribetes y pinceladas de todos los tonos del verde, blanco-crema y magenta. Si se observa atentamente, se podrá ver que durante el día, las hojas de la calatea se mueven buscando la luz.\\n\" +\n" +
                "                \"La calatea es originaria de las regiones tropicales de América del Sur, prosperando en las partes nororientales del continente. Ha sido introducida con éxito en áreas subtropicales y templadas cálidas en todo el mundo, donde se cultiva ampliamente con fines ornamentales.\\n\" +\n" +
                "                \"Uso en el Jardín\\n\" +\n" +
                "                \"Calatea es bueno para plantar en patios y bajo la sombra a lo largo del camino. Las plantas en macetas pueden decorar salas de exposiciones y vestíbulos.\n" +
                "                \"Simbolismo\\n\" +\n" +
                "                \"Gloria de la belleza\\n\" +\n" +
                "                \"Datos de Interés\\n\" +\n" +
                "                \"Como calatea hojas de calatea crecen lentamente, los patrones en las hojas también crecen, por lo que mantienen su belleza.',4,240,240)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Calathea Insignis','calathea_insignis','También conocido como: Marantha\n" +
                "El verde para siempre es una planta muy popular para el cultivo doméstico de interior que, gracias a su exótico y llamativo follaje, resulta destacable dentro del hogar. Incluso a simple vista y gracias al patrón de rayas que tiene, pudiera parecer el sonajero de una serpiente de cascabel. A su vez, su temporada de floración la cual se da en verano.\n" +
                "La verde para siempre es una planta originaria de las regiones tropicales de Sudamérica. Se ha introducido con éxito en varias áreas en todo el mundo y se cultiva en numerosas regiones debido a su atractivo ornamental.\n" +
                "Uso en el Jardín\n" +
                "A pesar de que la verde para siempre es una planta delicada, es muy valorada con fines decorativos para su uso en interiores. Principalmente por su follaje siempreverde de gran tamaño y hermosas manchas que le dan un atractivo extraordinario.\n" +
                "Simbolismo\n" +
                "Un nuevo comienzo\n" +
                "Datos de Interés\n" +
                "Goeppertia lancifolia es un arbusto tropical perenne que requiere humedad y pleno sol o sombra ligera para crecer de la mejor manera.',4,250,250)");

        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Boca de dragon','boca_dragon','También conocido como: Tilo, Ivan-da-Marya, Hierba triple\n" +
                "La boca del dragón es una hierba anual especialmente fascinante dada la rareza de sus flores amarillas y de corona púrpura que brotan en pleno verano, que poseen la forma de un \"dragón\". Se puede hallar particularmente naturalizado en valles y praderas de países del Báltico, como Suecia, donde podrían también conocerla como “día y noche” gracias a los colores de su flor.\n" +
                "Boca del dragón es una planta con un rango natural que se extiende por regiones templadas de Europa y Asia. También ha sido introducida en partes del norte de Europa, lo que refleja su adaptabilidad a diferentes climas templados. La especie no solo está presente en la naturaleza, sino que también ha sido cultivada, lo que indica su amplio rango potencial y éxito en hábitats variados.',6,320,320)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Clavel lanudo','clavel_lanudo','También conocido como: Rosa de grecia, Claveles lanudos, Agrostema, Coronaria\n" +
                "La clavel lanudo (Silene coronaria) es una hierba perenne de crecimiento muy ramificado, notable por sus hojas que, aunque son de color verde, están recubiertas de pelitos que le dan un aspecto y textura aterciopelados. Sus lindas flores magenta y su facilidad de cultivo hacen que se utilice extensamente como planta ornamental.\n" +
                "El clavel lanudo es nativo de Europa y Asia Central, donde se encuentra en praderas y ecosistemas perturbados. Ha sido introducido en varios países europeos y partes de América del Norte. Se utiliza ampliamente como planta ornamental, pero a veces escapa del cultivo e invade áreas perturbadas. Está clasificado como invasivo en los estados de Oregón y Alaska (EE. UU.).\n" +
                "Uso en el Jardín\n" +
                "Con una flor de intenso color magenta y aspecto contundente, la clavel lanudo es una planta ornamental por excelencia. Gracias a su rápido crecimiento y vasta tolerancia en suelos no muy fértiles, puede ser cultivada en casi cualquier espacio, desde zonas costeras, cottage y jardines urbanos, hasta praderas y contenedores.\n" +
                "Simbolismo\n" +
                "Nunca cambies hasta la muerte\n" +
                "Datos de Interés\n" +
                "Clavel lanudo flores de clavel lanudo son hermosas y coloridas. Siempre ha sido considerada como una flor famosa. En las obras de los poetas chinos, es un símbolo de nobleza. Se dice que el creador usó el viento para cortar su flor en una forma tan única.',6,330,330)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Neguilla','neguilla','También conocido como: Neguillón de los trigos, Negrilla, Aneguilla, Rosa de grecia, Hierba mala del trigo, Hierba de git, Clavel de asno, Guantes de reina, Neguilla de sembrados, Hierba gineta, Hierba negrera, Claveles lanudos, Yetón, Coronaria purpúrea, Veludillos, Neguillón cultivado, Neguillón\n" +
                "La neguilla se encuentra presente en muchos cultivos de maíz y otros cereales como una maleza nociva. Además, todas las partes verdes de la planta son venenosas para humanos, ganado y aves de corral. Sus flores atraen a las abejas, polillas y mariposas, quienes las polinizan.\n" +
                "Neguilla se encuentra de forma natural en regiones templadas de Europa, África del Norte, Asia Occidental y Asia Central. Se ha introducido en muchas partes del mundo, incluyendo regiones de América del Sur, África, Asia Oriental y algunas islas del Atlántico Norte. Aunque ya no se cultiva ampliamente, neguilla tiene presencia en muchas áreas fuera de su rango nativo.\n" +
                "Uso en el Jardín\n" +
                "Aunque el neguilla es una planta de corta vida, brota en primavera y florece gloriosamente en verano, añadiendo valor ornamental a cualquier jardín. Las brillantes flores moradas no solo son llamativas, sino que también atraen abejas, aves y mariposas. Esta planta se recomienda plantar en los parterres y bordes de jardines de vida silvestre y flores silvestres.\n" +
                "Datos de Interés\n" +
                "Hasta el siglo XX, la mayoría del trigo probablemente contenía las semillas del neguilla, que debían ser cuidadosamente clasificadas después de la cosecha. Agrostemma githago es en realidad venenoso, aunque hay relatos de su uso en la medicina popular.',6,340,340)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Conejito de los muros','conejito_muros','También conocido como: Claveles\n" +
                "Los conejito de los muros son una planta trepadora que crece en cultivos, muros y cunetas. En el tiempo de los antiguos romanos, se utilizaba por sus propiedades. Su nombre en latín Fumaria muralis proviene del latín humo por el color ahumado de sus flores.\n" +
                "La conejito de los muros se encuentra naturalmente en Europa Occidental y Meridional, así como en partes del Norte de África. Fuera de su región nativa, la conejito de los muros ha sido introducida en varias áreas a lo largo de diferentes continentes, incluyendo América del Sur, África, Asia y Oceanía, así como en Europa del Norte, adaptándose a una variedad de climas templados.\n" +
                "Conejito de los muros contiene alcaloides tóxicos que afectan principalmente a humanos, gatos y perros a través de la ingestión. Los síntomas incluyen malestar gastrointestinal (náuseas, vómitos, diarrea) y problemas neurológicos (dolores de cabeza, mareos, letargo). La atención veterinaria inmediata es crucial para las mascotas que muestren signos de envenenamiento.',6,350,350)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Clavel de poeta','clavel_poeta','También conocido como: Clavelino, Ramilletes\n" +
                "Es de fácil mantenimiento, solo hace falta retirar las flores secas para que otras nuevas puedan crecer, además de mantener el suelo húmedo, pero no en exceso. Se adapta a diferentes climas y puede soportar heladas. En general, las flores del clavel de poeta son ramos de colores vivos; a veces una misma flor puede tener dos colores.\n" +
                "El clavel de poeta crece de forma nativa en dos partes dispares del mundo: las montañas de Europa y el norte de Asia Oriental. Crece en bosques y praderas. El clavel de poeta ha sido ampliamente introducido como planta de jardín en todo el mundo, y ha escapado de la cultivación para crecer de forma silvestre en gran parte de América del Norte y Asia Central.\n" +
                "Uso en el Jardín\n" +
                "Clavel de poeta es una flor bianual o perenne que ha ganado popularidad en los jardines de casa. Sus racimos de coloridas flores y largos tallos la hacen ideal como flor cortada. Apreciada por sus brillantes flores, clavel de poeta es perfectamente adecuada para jardines de rocas, jardines de estilo campestre y natural, y jardines de bordes, y puede combinarse con una amplia variedad de otras flores que aman el sol, como las rosas.\n" +
                "Simbolismo\n" +
                "Admiración, pasión, capricho, afecto, amor, gratitud, galantería\n" +
                "Historia del nombre\n" +
                "Clavel del poeta: Clavel ramillete, Clavelina, clavel de poeta, son solo algunos de los nombres que sirven para identificar esta planta. Su nombre científico, Dianthus barbatus, es la conjunción de las palabras griegas que significan «dios», «flor» y «con barbas», por lo que podríamos decir que el clavel de poeta es la «flor de los dioses con barba».',6,360,360)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Clavel del aire','clavel_aire','El clavel del aire (Tillandsia stricta) es una planta aérea que se ha adaptado para crecer igual de bien en una playa de arena fina, en la copa de un árbol o en medio de la Cordillera de los Andes. Sus particulares flores pueden llegar a durar, como máximo, un día (antes de marchitarse). Al igual que otras plantas aéreas, el clavel del aire absorbe el agua y los nutrientes que necesita para sobrevivir a través de sus hojas.\n" +
                "El margarita del Cabo es nativo del sur de África y desde entonces se ha introducido en otras regiones, incluidas islas en el Pacífico Sur. Prospera en climas templados y se puede encontrar cultivado en varias partes del mundo, apreciado por sus cualidades ornamentales.\n" +
                "Uso en el Jardín\n" +
                "Margarita del Cabo es un arbusto pequeño y floreciente popular, adecuado para muchos tipos de jardines y plantaciones en contenedores para exhibición en terrazas, patios y balcones. La brillante flor y las numerosas variedades comienzan a florecer en primavera, añadiendo un color vibrante a los parterres y áreas de bordes.\n" +
                "Simbolismo\n" +
                "Pureza e inocencia\n" +
                "Datos de Interés\n" +
                "La margarita simboliza el verdadero amor, no solo en sentimiento, sino también por la forma en que se disponen las flores. La cabezuela de la margarita no es una sola, sino un grupo de muchas florecitas. La cabezuela de la margarita consta de flores masculinas y femeninas. En la margarita del Cabo, los pétalos de rayo son femeninos y las flores del disco en el centro de la flor son, en realidad, flores individuales pseudo-bisexuales.',6,370,370)");

        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Margarita del Cabo','margarita_cabo','También conocido como: Dimorfoteca\n" +
                "La margarita del Cabo (Osteospermum fruticosum) pertenece a una pequeña familia de apenas 100 especies provenientes de África, por lo que no es muy entusiasta del frío europeo. De hecho, cuando las temperaturas bajan, se podrá ver cómo los pétalos de la margarita del Cabo se enrollan desde sus laterales hacia adentro, dándole una curiosa forma.',8,430,430)");
        db.execSQL("INSERT INTO PLANTAS (NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD) VALUES('Margarita de la lluvia','margarita lluvia','También conocido como: Caléndula del Cabo, Profeta del tiempo\n" +
                "La margarita de la lluvia (Dimorphotheca pluvialis) es un tipo de margarita de pétalos largos, que suele florecer en regiones áridas y se le reconoce como la “Margarita de África” al ser una especie nativa exclusiva de Namibia. Actualmente se utiliza como planta ornamental, pero también sus semillas podrían ser utilizadas para el desarrollo de resinas, puesto que están compuestas en buena parte de ácido dimorfecólico.\n" +
                "Margarita de la lluvia es originario de las partes del sur de África, donde prospera en su hábitat natural. Más allá de su origen, margarita de la lluvia se ha extendido y ahora se ha introducido en varias regiones de Europa, Asia y América. Además, margarita de la lluvia ha sido cultivado, lo que indica su adaptabilidad y amplio rango de crecimiento fuera de su área indígena.',8,440,440)");
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Este método se llama cuando la versión de la base de datos cambia y debemos actualizar la estructura de la base de datos.
    }

}