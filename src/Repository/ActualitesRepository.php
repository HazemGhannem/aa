<?php

namespace App\Repository;

use App\Entity\Actualites;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Actualites|null find($id, $lockMode = null, $lockVersion = null)
 * @method Actualites|null findOneBy(array $criteria, array $orderBy = null)
 * @method Actualites[]    findAll()
 * @method Actualites[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ActualitesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Actualites::class);
    }
    public function get_by_categorie($cat)
    {
        return $this->createQueryBuilder('A')
            ->andWhere('A.categorie = :val')
            ->setParameter('val',$cat )
            ->orderBy('A.id', 'ASC')
            ->getQuery()
            ->getResult();
    }
    public function search($nom)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.nom LIKE :nom')
            ->setParameter('nom', '%'.$nom.'%')
            ->getQuery()
            ->execute();
    }
    // /**
    //  * @return Actualites[] Returns an array of Actualites objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('a.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Actualites
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
