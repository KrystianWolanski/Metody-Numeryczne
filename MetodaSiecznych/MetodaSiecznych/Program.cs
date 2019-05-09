using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MetodaSiecznych
{
    class Program
    {
        static double F(double x, string wzor)
        {
            
            int i = 0;
            string liczba_przed_x = "";
            string liczba_po_znaku_potegi = "";
            double wartosc_wyrazenia = 0;
            double wartosc_wzoru = 0;


            char znak_operacji;
            if (wzor[0].Equals('-'))
                znak_operacji = '-';
            else
                znak_operacji = '+';

            while (i < wzor.Length)
            {
                if (!wzor[i].Equals('x') && !wzor[i].Equals('+') && !wzor[i].Equals('-'))
                {
                    liczba_przed_x = liczba_przed_x + wzor[i];
                    i++;

                    if (i>=wzor.Length || wzor[i].Equals('+') || wzor[i].Equals('-'))
                    {
                        wartosc_wyrazenia = double.Parse(liczba_przed_x);
                        if (znak_operacji == '+')
                            wartosc_wzoru = wartosc_wzoru + wartosc_wyrazenia;
                        else
                            wartosc_wzoru = wartosc_wzoru - wartosc_wyrazenia;

                        liczba_przed_x = "";

                    }
                }
                else if (i + 1 < wzor.Length && wzor[i].Equals('x') && wzor[i + 1].Equals('^'))
                {
                    i = i + 2;
                    for (int j = i; j < wzor.Length; j++)
                    {

                        if (!wzor[j].Equals('+') && !wzor[j].Equals('-'))
                        {
                            liczba_po_znaku_potegi = liczba_po_znaku_potegi + wzor[j];
                            i++;
                        }
                        else
                            break;

                    }
                    wartosc_wyrazenia = double.Parse(liczba_przed_x) * (int)(Math.Pow(x, Int32.Parse(liczba_po_znaku_potegi)));
                    if (znak_operacji == '+')
                        wartosc_wzoru = wartosc_wzoru + wartosc_wyrazenia;
                    else
                        wartosc_wzoru = wartosc_wzoru - wartosc_wyrazenia;

                    liczba_przed_x = "";
                    liczba_po_znaku_potegi = "";

                }
                else if (wzor[i].Equals('x'))
                {
                    wartosc_wyrazenia = double.Parse(liczba_przed_x) * x;
                    if (znak_operacji == '+')
                        wartosc_wzoru = wartosc_wzoru + wartosc_wyrazenia;
                    else
                        wartosc_wzoru = wartosc_wzoru - wartosc_wyrazenia;
                    i++;

                    liczba_przed_x = "";
                    liczba_po_znaku_potegi = "";
                }
                else if (wzor[i].Equals('+'))
                {
                    znak_operacji = '+';
                    i++;
                }
                else if (wzor[i].Equals('-'))
                {
                    znak_operacji = '-';
                    i++;
                }
            }
            return wartosc_wzoru;
        }

        static double Start(string wzor, double epsilon)
        {
            
            Console.WriteLine("Podaj krańce przedziału: ");
            double[] x = new double[3];
            x[0] = double.Parse(Console.ReadLine());
            x[1] = double.Parse(Console.ReadLine());

            if (F(x[0], wzor) * F(x[1], wzor) < 0)
            {
                do
                {
                    try
                    {
                        x[2] = (x[1] - F(x[1], wzor) * (x[1] - x[0]) / (F(x[1], wzor) - F(x[0], wzor)));
                    }
                    catch (System.DivideByZeroException e)
                    {
                        Console.WriteLine("Wystąpiło dzielenie przez zero");
                        break;
                    }

                    if (F(x[2], wzor) <= epsilon)
                    {
                        return (double)x[2];
                    }
                    else
                    {
                        x[0] = x[1];
                        x[1] = x[2];
                    }
                } while (!(Math.Abs((F(x[2], wzor))) <= epsilon));
            }
            else
            {
                Console.WriteLine("F(x0) * F(x1) < 0");
                Start(wzor, epsilon);
            }
                

            return 0;
        }
        static void Main(string[] args)
        {

            Console.WriteLine("Podaj wzór: ");
            string wzor = Console.ReadLine();
            Console.WriteLine("Podaj epsilon: ");
            double epsilon = Double.Parse(Console.ReadLine());
            

            Console.WriteLine("Szukane miejsce zerowe to: " + Start(wzor, epsilon));

            Console.ReadLine();
        }
    }
}
