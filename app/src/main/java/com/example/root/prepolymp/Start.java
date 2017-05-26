package com.example.root.prepolymp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.root.prepolymp.fragments.Stats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.root.prepolymp.DBManager.DB_NAME;
import static com.example.root.prepolymp.Storage.firstname;
import static com.example.root.prepolymp.Storage.lastname;
import static com.example.root.prepolymp.Storage.problems;
import static com.example.root.prepolymp.fragments.Favourites.adapterFavourites;
import static com.example.root.prepolymp.fragments.ProblemList.adapterAllProblems;
import static com.example.root.prepolymp.fragments.SolveLater.adapterMarked;
import static com.example.root.prepolymp.fragments.Solved.adapterSolved;

public class Start extends AppCompatActivity {

    public static final String NAMES_FILE_NAME = "PrepOlympNames";
    public static final String ATTEMPTS_FILE_NAME = "PrepOlympAttempts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DBManager dbProblems = new DBManager(this);
        dbProblems.getWritableDatabase();

        //Московская математическая олимпиада 2016

        if (dbProblems.size() == 0) {
            this.deleteDatabase(DB_NAME);
            dbProblems = new DBManager(this);
            dbProblems.getWritableDatabase();
            dbProblems.addProblem(new Problem(5, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.",
                    "228888", "алгебра", 8, 5, "MMO"));

            dbProblems.addProblem(new Problem(10, "Уравнение с целыми коэффициентами  x4 + ax3 + bx2 + cx + d = 0  имеет четыре положительных корня с учетом кратности. \n" +
                    "Найдите наименьшее возможное значение коэффициента b при этих условиях.", "6", "алгебра", 10, 5, "Московская математическая олимпиада 2016"));

            dbProblems.addProblem(new Problem(3, "Васе задали на дом уравнение  x2 + p1x + q1 = 0,  где p1 и q1 – целые числа. Он нашел его корни p2 и q2 и написал новое уравнение  x2 + p2x + q2 = 0.  Повторив операцию еще трижды, Вася заметил, что он решал четыре квадратных уравнения и каждое имело два различных целых корня (если из двух возможных уравнений два различных корня имело ровно одно, то Вася всегда выбирал его, а если оба – любое). Однако, как ни старался Вася, у него не получилось составить пятое уравнение так, чтобы оно имело два различных вещественных корня, и Вася сильно расстроился. Какое уравнение Васе задали на дом? Введите через запятую два целых числа p1 и q1 без пробелов.",
                    "1,-30", "алгебра", 9, 5, "Московская математическая олимпиада 2016"));

            dbProblems.addProblem(new Problem(9, "Найдите наименьшее натуральное число, десятичная запись квадрата которого оканчивается на 2016.",
                    "996", "алгебра", 11, 5, "ММО"));

            dbProblems.addProblem(new Problem(4, "Дан выпуклый пятиугольник ABCDE, все стороны которого равны между собой. Известно, что угол A равен 120°, угол C равен 135°, а угол D равен n°. Найдите все возможные целые значения n. Введите одно или несколько целых чисел, разделённых запятой (без пробелов).",
                    "90", "геометрия", 8, 6, "ММО"));

            dbProblems.addProblem(new Problem(7, "В остроугольном треугольнике ABC, в котором ∠A = 45°,  проведены высоты AA1, BB1, CC1. Биссектриса угла BAA1 пересекает прямую B1A1 в точке D, а биссектриса угла CAA1 пересекает прямую C1A1 в точке E. Найдите угол между прямыми BD и CE. Введите число в десятичной системе счисления. При необходимости округлите ответ до сотых, разделите целую и дробную части точкой без пробелов.",
                    "67.5", "геометрия", 8, 6, "ММО"));

            dbProblems.addProblem(new Problem(8, "День в Анчурии может быть либо ясным, когда весь день солнце, либо дождливым, когда весь день льет дождь. И если сегодня день не такой, как вчера, то анчурийцы говорят, что сегодня погода изменилась. Однажды анчурийские ученые установили, что 1 января день всегда ясный, а каждый следующий день в январе будет ясным, только если ровно год назад в этот день погода изменилась. В 2015 году январь в Анчурии был весьма разнообразным: то солнце, то дожди. В каком году погода в январе впервые будет меняться ровно так же, как в январе 2015 года?)",
                    "2047", "комбинаторика", 11, 5, "ММО"));

            dbProblems.addProblem(new Problem(1, "За круглым столом сидят 100 человек. Разрешается любых двух людей, сидящих рядом, поменять местами. Какое наименьшее число таких перестановок необходимо сделать, чтобы в результате каждые два соседа остались бы соседями, но сидели бы в обратном порядке?",
                    "2450", "комбинаторика", 11, 7, "ММО"));

            dbProblems.addProblem(new Problem(6, "Среди n рыцарей каждые двое – либо друзья, либо враги. У каждого из рыцарей ровно три врага, причём враги его друзей являются его врагами. При каких n такое возможно? Перечислите все значения через запятую без пробелов.",
                    "4,6", "комбинаторика", 10, 5, "Московская математическая регата"));

            dbProblems.addProblem(new Problem(2, "Волейбольная сетка имеет вид прямоугольника размером 50×600 клеток. Какое наибольшее число верёвочек можно перерезать так, чтобы сетка не распалась на куски?",
                    "30000", "комбинаторика", 8, 5, "Фольклор"));

            dbProblems.addProblem(new Problem(11, "На бесконечной шахматной доске проведена замкнутая несамопересекающаяся ломаная, проходящая по сторонам клеток. Внутри ломаной оказалось 50 чёрных клеток. Какую наибольшую площадь может иметь фигура, ограниченная этой ломаной?",
                    "201", "комбинаторика", 9, 5, "MMO"));

            dbProblems.addProblem(new Problem(12, "Точки O и I – центры описанной и вписанной окружностей неравнобедренного треугольника ABC. Две равные окружности касаются сторон AB, BC и AC, BC соответственно; кроме этого, они касаются друг друга в точке K. Оказалось, что K лежит на прямой OI. Найдите ∠BAC. Введите число в десятичной системе счисления. При необходимости округлите ответ до сотых, разделите целую и дробную части запятой без пробелов.",
                    "90", "геометрия", 9, 5, "MMO"));

            dbProblems.addProblem(new Problem(13, "У Пети всего 28 одноклассников. У каждых двух из 28 различное число друзей в этом классе. Сколько друзей у Пети?",
                    "14", "комбинаторика", 8, 4));

            dbProblems.addProblem(new Problem(14, "На плоскости отмечено 100 точек, никакие три из которых не лежат на одной прямой. Некоторые пары точек соединены отрезками. Известно, что никакая тройка отрезков не образует треугольника. Какое наибольшее число отрезков могло быть проведено?",
                    "2500", "комбинаторика", 10, 6));

            dbProblems.addProblem(new Problem(15, "В классе учится 15 мальчиков и 15 девочек. В день 8 Марта некоторые мальчики позвонили некоторым девочкам и поздравили их с праздником. Оказалось, что детей можно единственным образом разбить на 15 пар так, чтобы в каждой паре оказались мальчик с девочкой, которой он звонил. Какое наибольшее число звонков могло быть сделано?",
                    "120", "комбинаторика", 9, 5, "ВОШ"));

            dbProblems.addProblem(new Problem(16, "Четырёхугольник ABCD вписан в окружность. Биссектрисы углов В и С пересекаются в точке, лежащей на отрезке AD.\n" +
                    "Найдите AD, если  АВ = 5,  СD = 3.",
                    "8", "геометрия", 10, 6, "Московская математическая регата"));

            dbProblems.addProblem(new Problem(17, "Найдите наибольшее натуральное число N, для которого уравнение  99x + 100y + 101z = N  имеет единственное решение в натуральных числах x, y, z.",
                    "5251", "алгебра", 10, 6, "Турнир городов"));

            dbProblems.addProblem(new Problem(18, "В выпуклом 100-угольнике проведено несколько ёлей. Проведённая диагональ называется хорошей, если она пересекается (по внутренним точкам) ровно с одной из других проведённых диагоналей. Найдите наибольшее возможное количество хороших диагоналей.",
                    "98", "геометрия", 9, 7, "ВОШ"));

            dbProblems.addProblem(new Problem(19, "В квадрате отметили 20 точек и соединили их непересекающимися отрезками друг с другом и с вершинами квадрата так, что квадрат разбился на треугольники. Сколько получилось треугольников?",
                    "42", "комбинаторика", 8, 6));

            dbProblems.addProblem(new Problem(20, "Дано 29-значное число X. Известно, что для всякого k цифра ak встречается в записи данного числа a[30 - k] раз (например, если a10 = 7, то цифра a20 встречается 7 раз). Найти сумму цифр числа X.",
                    "201", "алгебра", 10, 5, "ММО"));

            dbProblems.addProblem(new Problem(21, "Отрезок AB есть диаметр круга, а точка C лежит вне этого круга. Отрезки AC и BC пересекаются с окружностью в точках D и M соответственно. Найдите угол CBD, если площади треугольников DCM и ACB относятся как 1:4.",
                    "30", "геометрия", 8, 5));

            dbProblems.addProblem(new Problem(22, "На новом сайте зарегистрировалось 2000 человек. Каждый пригласил к себе в друзья по 1000 человек. Два человека объявляются друзьями тогда и только тогда, когда каждый из них пригласил другого в друзья. Какое наименьшее количество пар друзей могло образоваться?",
                    "1000", "комбинаторика", 9, 5, "Турнир городов"));

            dbProblems.addProblem(new Problem(23, "Найти трёхзначное число, являющееся точным квадратом A^2, такое, что произведение его цифр равно A-1.",
                    "361", "алгебра", 9,4, "Беларусские республиканские олимпиады"));

            dbProblems.addProblem(new Problem(24, "Сумма восьми чисел равна 4/3. Оказалось, что сумма каждых семи чисел из этих восьми – положительна. Какое наименьшее целое значение может принимать наименьшее из данных чисел?",
                    "-7", "алгебра", 11, 6, "Московская математическая регата"));

            dbProblems.addProblem(new Problem(25, "Тысяча точек является вершинами выпуклого тысячеугольника, внутри которого расположено ещё пятьсот точек так, что никакие три из пятисот не лежат на одной прямой. Данный тысячеугольник разрезан на треугольники таким образом, что все указанные 1500 точек являются вершинами треугольников и эти треугольники не имеют никаких других вершин. Сколько получится треугольников при таком разрезании?",
                    "1998", "геометрия", 8, 6));

            dbProblems.addProblem(new Problem(26, "В однокруговом хоккейном турнире принимало участие 2016 команд. По регламенту турнира за победу даётся 3 очка, за поражение 0 очков, а в случае ничьей назначается дополнительное время, победитель которого получает 2 очка, а проигравший – 1 очко. По окончании турнира Остапу Бендеру сообщили количество очков, набранных каждой командой, на основании чего он сделал вывод, что не менее N матчей закончились дополнительным временем. Найдите наибольшее возможное значение N.",
                    "1512", "комбинаторика", 11, 8, "ММО"));

            dbProblems.addProblem(new Problem(27, "Какое максимальное число ладей можно расставить в кубе 8×8×8, чтобы они не били друг друга?",
                    "64", "комбинаторика", 8, 4, "Турнир Ломоносова"));

            dbProblems.addProblem(new Problem(28, "Дан квадратный трёхчлен  f(x) = x2 + ax + b.  Известно, что для любого вещественного x существует такое вещественное y, что f(y) = f(x) + y.  Найдите наибольшее возможное значение a.",
                    "0.5", "алгебра", 9, 6, "ВОШ"));

            dbProblems.addProblem(new Problem(29, "На плоскости дан квадрат со стороной 9. Найти объём тела, состоящего из всех точек пространства, расстояние от которых до части плоскости, ограниченной квадратом, не больше 9. Дайте ответ в виде Xpi+Y (pi - число Пи)",
                    "270pi+162", "комбинаторика", 10, 7, "Беларусские республиканские олимпиады"));

            dbProblems.addProblem(new Problem(30, "Окружность касается сторон AB и AD прямоугольника ABCD и пересекает сторону DC в единственной точке F и сторону BC в единственной точке E. Найдите площадь трапеции AFCB, если AB = 32, AD = 40 и BE = 1.",
                    "1180", "геометрия", 9, 5));

            dbProblems.addProblem(new Problem(31, "Найдите все трёхзначные числа, всякая целая степень которого оканчивается на три цифры, составляющие исходное число (в том же порядке). Запишите их через запятую без пробелов.",
                    "376,625", "алгебра", 10, 6, "ММО"));

            dbProblems.addProblem(new Problem(32, "Дан выпуклый n-угольник A1...An. Пусть Pi  (i = 1, ..., n)  – такая точка на его границе, что прямая AiPi делит его площадь пополам. Известно, что все точки Pi не совпадают с вершинами и лежат на k сторонах n-угольника. Каково  а) наименьшее;  б) наибольшее возможное значение k при n = 100? Запишите ответ через запятую без пробелов",
                    "3,99", "геометрия", 9, 7, "Всероссийская олимпиада по геометрии"));

            dbProblems.addProblem(new Problem(33, "Имеется 19 гирек весом 1 г, 2 г, 3 г, ..., 19 г. Девять из них – железные, девять – бронзовые и одна – золотая. Известно, что общий вес всех железных гирек на 90 г больше, чем общий вес бронзовых. Найдите вес золотой гирьки.",
                    "10", "комбинаторика", 10, 4, "Турнир городов"));

            dbProblems.addProblem(new Problem(34, "Все целые числа выписаны подряд, начиная от единицы. Определите, какая цифра стоит на 206788-м месте.",
                    "7", "алгебра", 10, 4, "Турнир городов"));

            dbProblems.addProblem(new Problem(35, "В треугольнике ABC на сторонах AC и BC взяты такие точки X и Y, что  ∠ABX = ∠YAC,  ∠AYB = ∠BXC,  XC = YB.  Найдите наименьший угол треугольника ABC.",
                    "60", "геометрия", 8, 6, "ММО"));

        }

        problems = dbProblems.getAllProblems();
        for (Problem problem : problems) {
            if (!problem.solved) {
                continue;
            }
            switch(problem.topic) {
                case "алгебра": ++Stats.cor_alg; break;
                case "комбинаторика": ++Stats.cor_comb; break;
                case "геометрия": ++Stats.cor_geom; break;
            }
        }
        Log.d("problem1", problems.get(0).topic);

        fixArrayAdapterBug();
        getUserName();
        getAttemptsNums();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void fixArrayAdapterBug() {
        // The following paragraph fixes a bug (nullPointerException with arrayAdapters)
        // It should not be minded
        ArrayList<String> bugFix = new ArrayList<>();
        adapterAllProblems = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterFavourites = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterMarked = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);
        adapterSolved = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_list_item_1,bugFix);

    }

    public void getUserName() {
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(NAMES_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder1 = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder1.append(bufferedReader.readLine());  // firstname
                stringBuilder2.append(bufferedReader.readLine());  // lastname
                firstname = stringBuilder1.toString();
                lastname = stringBuilder2.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAttemptsNums() {
        InputStream inputStream = null;
        try {
            inputStream = openFileInput(ATTEMPTS_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder1 = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder1.append(bufferedReader.readLine());  // alg
                stringBuilder2.append(bufferedReader.readLine());  // comb
                stringBuilder3.append(bufferedReader.readLine());  // geom
                Stats.att_alg = Integer.parseInt(stringBuilder1.toString());
                Stats.att_comb = Integer.parseInt(stringBuilder2.toString());
                Stats.att_geom = Integer.parseInt(stringBuilder3.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
